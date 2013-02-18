/**
 * Location.java
 *
 * Copyright (C) 2004 MaxMind LLC.  All Rights Reserved.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Lesser Public
 * License as published by the Free Software Foundation; either
 * version 2 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

package com.maxmind.geoip;

public class Location {
    private String countryCode;
    private String countryName;
    private String region;
    private String regionName;
    private String city;
    private String postalCode;
    private String timezone;
    private float latitude;
    private float longitude;
    private int dmaCode;
    private int areaCode;
    private int metroCode;

    private final static double EARTH_DIAMETER = 2 * 6378.2;
    private final static double PI = 3.14159265;
    private final static double RAD_CONVERT = PI / 180;

    public double distance(Location loc) {
        double delta_lat, delta_lon;
        double temp;

        float lat1 = latitude;
        float lon1 = longitude;
        float lat2 = loc.latitude;
        float lon2 = loc.longitude;

        // convert degrees to radians
        lat1 *= RAD_CONVERT;
        lat2 *= RAD_CONVERT;

        // find the deltas
        delta_lat = lat2 - lat1;
        delta_lon = (lon2 - lon1) * RAD_CONVERT;

        // Find the great circle distance
        temp = Math.pow(Math.sin(delta_lat / 2), 2) + Math.cos(lat1)
            * Math.cos(lat2) * Math.pow(Math.sin(delta_lon / 2), 2);
        return EARTH_DIAMETER
            * Math.atan2(Math.sqrt(temp), Math.sqrt(1 - temp));
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public int getDmaCode() {
        return dmaCode;
    }

    public void setDmaCode(int dmaCode) {
        this.dmaCode = dmaCode;
    }

    public int getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(int areaCode) {
        this.areaCode = areaCode;
    }

    public int getMetroCode() {
        return metroCode;
    }

    public void setMetroCode(int metroCode) {
        this.metroCode = metroCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Location location = (Location) o;

        if (Float.compare(location.latitude, latitude) != 0) return false;
        if (Float.compare(location.longitude, longitude) != 0) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (latitude != +0.0f ? Float.floatToIntBits(latitude) : 0);
        result = 31 * result + (longitude != +0.0f ? Float.floatToIntBits(longitude) : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Location{" +
            "countryCode='" + countryCode + '\'' +
            ", countryName='" + countryName + '\'' +
            ", region='" + region + '\'' +
            ", regionName='" + regionName + '\'' +
            ", city='" + city + '\'' +
            ", postalCode='" + postalCode + '\'' +
            ", timezone='" + timezone + '\'' +
            ", latitude=" + latitude +
            ", longitude=" + longitude +
            ", dmaCode=" + dmaCode +
            ", areaCode=" + areaCode +
            ", metroCode=" + metroCode +
            '}';
    }
}
