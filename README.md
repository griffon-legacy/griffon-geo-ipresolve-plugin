
GeoIP (MaxMind) Integration
---------------------------

Plugin page: [http://artifacts.griffon-framework.org/plugin/geo-ipresolve](http://artifacts.griffon-framework.org/plugin/geo-ipresolve)


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

