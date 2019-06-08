package com.example.mystudyapp.models;

public class GeoIp
{
    private String country_code;

    private String metro_code;

    private String city;

    private String ip;

    private String country_name;

    private String region_name;

    private String time_zone;

    private String zip_code;

    private String region_code;

    private String longitude;

    public String getCountry_code ()
    {
        return country_code;
    }

    public void setCountry_code (String country_code)
    {
        this.country_code = country_code;
    }

    public String getMetro_code ()
    {
        return metro_code;
    }

    public void setMetro_code (String metro_code)
    {
        this.metro_code = metro_code;
    }

    public String getCity ()
    {
        return city;
    }

    public void setCity (String city)
    {
        this.city = city;
    }

    public String getIp ()
    {
        return ip;
    }

    public void setIp (String ip)
    {
        this.ip = ip;
    }

    public String getCountry_name ()
    {
        return country_name;
    }

    public void setCountry_name (String country_name)
    {
        this.country_name = country_name;
    }

    public String getRegion_name ()
    {
        return region_name;
    }

    public void setRegion_name (String region_name)
    {
        this.region_name = region_name;
    }

    public String getTime_zone ()
    {
        return time_zone;
    }

    public void setTime_zone (String time_zone)
    {
        this.time_zone = time_zone;
    }

    public String getZip_code ()
    {
        return zip_code;
    }

    public void setZip_code (String zip_code)
    {
        this.zip_code = zip_code;
    }

    public String getRegion_code ()
    {
        return region_code;
    }

    public void setRegion_code (String region_code)
    {
        this.region_code = region_code;
    }

    public String getLongitude ()
    {
        return longitude;
    }

    public void setLongitude (String longitude)
    {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return
                "country_code='" + country_code + '\'' +
                ", metro_code='" + metro_code + '\'' +
                ", city='" + city + '\'' +
                ", ip='" + ip + '\'' +
                ", country_name='" + country_name + '\'' +
                ", region_name='" + region_name + '\'' +
                ", time_zone='" + time_zone + '\'' +
                ", zip_code='" + zip_code + '\'' +
                ", region_code='" + region_code + '\'' +
                ", longitude='" + longitude + '\'' +
                '}';
    }
}