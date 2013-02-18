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

/**
 * @author Andres Almiray
 */
class GeoIpresolveGriffonPlugin {
    // the plugin version
    String version = '0.1'
    // the version or versions of Griffon the plugin is designed for
    String griffonVersion = '1.2.0 > *'
    // the other plugins this plugin depends on
    Map dependsOn = [:]
    // resources that are included in plugin packaging
    List pluginIncludes = []
    // the plugin license
    String license = 'Apache Software License 2.0'
    // Toolkit compatibility. No value means compatible with all
    // Valid values are: swing, javafx, swt, pivot, qt
    List toolkits = []
    // Platform compatibility. No value means compatible with all
    // Valid values are:
    // linux, linux64, windows, windows64, macosx, macosx64, solaris
    List platforms = []
    // URL where documentation can be found
    String documentation = ''
    // URL where source can be found
    String source = 'https://github.com/griffon/griffon-geo-ipresolve-plugin'
    // Map of Bnd directives and/or Manifest entries
    // see http://www.aqute.biz/Bnd/Bnd for reference
    /*Map manifest = [
        'Bundle-Description': 'Plugin summary/headline'
    ]*/

    List authors = [
        [
            name: 'Andres Almiray',
            email: 'aalmiray@yahoo.com'
        ]
    ]
    String title = 'GeoIP (MaxMind) Integration'
    // accepts Markdown syntax. See http://daringfireball.net/projects/markdown/ for details
    String description = '''
The purpose of this very simple plugin is to provide a seemless integration with
MaxMind's geographical location open source API and has been built to be used with
the GeoCity binary database. This is a port of the [geoip][1] Grails plugin
originally by Radu Andrei Tanasa.

Usage
-----

You must configure 2 properties in `Config.groovy` using the [Service configuration DSL][2]:

 * `dataPath`. Path to your Maxmind database file. You can get a free Lite version [here][3].
   The value should point to a classpath resource, for example `GeoLiteCity.dat`;
   this resource can be placed inside `griffon-app/resources`.
 * `cacheOption`. There are 4 possible values for this:
 
    * 0 - GEOIP_STANDARD (this is the default)
    * 1 - GEOIP_MEMORY_CACHE
    * 2 - GEOIP_CHECK_CACHE
    * 4 - GEOIP_INDEX_CACHE

Here's an example configuration

    services {
        geoIpResolve {
            dataPath = 'GeoLiteCity.dat'
        }
    }

Note that when you're using the cached versions it will load the database in memory.
Its size is usually around 45 MB. 

At the moment the service exposes only one method. Here is how you determine the
location of an IP:

    class MyController {
        def geoIpResolveService
        def doSomething = {
            def location = geoIpService.getLocation('88.158.9.150')
            println location
        }
    }

Here is a list of the location's fields.

 * countryCode
 * countryName
 * region
 * regionName
 * city
 * postalCode
 * timezone
 * latitude
 * longitude
 * dmaCode
 * areaCode
 * metroCode

Note that some may not be properly populated if the information is missing.


[1]: http://grails.org/plugin/geoip
[2]: http://griffon.codehaus.org/guide/latest/guide/controllersAndServices.html#serviceConfiguration
[3]: http://www.maxmind.com/app/geolitecity
'''
}
