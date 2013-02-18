/*
 * Copyright 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the 'License');
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an 'AS IS' BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package griffon.plugins.geoipresolve

import com.maxmind.geoip.Location
import com.maxmind.geoip.LookupService
import com.maxmind.geoip.RegionName
import com.maxmind.geoip.TimeZone
import griffon.exceptions.GriffonException

/**
 * @author Andres Almiray
 */
class GeoIpResolveService {
    private LookupService lookupService
    def dataPath
    int cacheOption = 0

    void serviceInit() {
        if (!dataPath) {
            throw new GriffonException("Configuration setting for 'geoIpResolve.dataPath' is missing!")
        }
        if (dataPath instanceof String) {
            dataPath = app.getResourceAsURL(dataPath)
            dataPath = new File(dataPath.toURI())
        }
        if (!(dataPath instanceof File)) {
            throw new GriffonException("Invalid configuration setting for 'geoIpResolve.dataPath', value must be a String or a File.")
        }

        this.lookupService = new LookupService(dataPath, cacheOption)
    }

    Location getLocation(String ip) {
        Location location = lookupService.getLocation(ip)
        if (location) {
            location.regionName = RegionName.regionNameByCode(location.countryCode, location.region)
            location.timezone = TimeZone.timeZoneByCountryAndRegion(location.countryCode, location.region)
        }
        return location
    }
}
