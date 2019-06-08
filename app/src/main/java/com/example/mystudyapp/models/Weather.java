package com.example.mystudyapp.models;

public class Weather {

    private int imageRes;
    private String weather;
    private String location;
    private String temp;


    public Weather(int imageRes, String location, String temp) {

        this.imageRes = imageRes;
       // this.weather = weather;
        this.location = location;
        this.temp = temp;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public int getImageRes() {
        return imageRes;
    }

    public void setImageRes(int imageRes) {
        this.imageRes = imageRes;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "imageRes=" + imageRes +
                ", weather='" + weather + '\'' +
                ", location='" + location + '\'' +
                ", temp='" + temp + '\'' +
                '}';
    }
}
