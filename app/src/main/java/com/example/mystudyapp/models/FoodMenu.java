package com.example.mystudyapp.models;

public class FoodMenu {

    private String date;
    private String day;
    private String rice;
    private String soup;
    private String ban1;
    private String ban2;
    private String ban3;
    private String ban4;


    public FoodMenu(String date, String day, String rice, String soup, String ban1, String ban2, String ban3, String ban4) {
        this.date = date;
        this.day = day;
        this.rice = rice;
        this.soup = soup;
        this.ban1 = ban1;
        this.ban2 = ban2;
        this.ban3 = ban3;
        this.ban4 = ban4;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getRice() {
        return rice;
    }

    public void setRice(String rice) {
        this.rice = rice;
    }

    public String getSoup() {
        return soup;
    }

    public void setSoup(String soup) {
        this.soup = soup;
    }

    public String getBan1() {
        return ban1;
    }

    public void setBan1(String ban1) {
        this.ban1 = ban1;
    }

    public String getBan2() {
        return ban2;
    }

    public void setBan2(String ban2) {
        this.ban2 = ban2;
    }

    public String getBan3() {
        return ban3;
    }

    public void setBan3(String ban3) {
        this.ban3 = ban3;
    }

    public String getBan4() {
        return ban4;
    }

    public void setBan4(String ban4) {
        this.ban4 = ban4;
    }

    @Override
    public String toString() {
        return "FoodMenu{" +
                "date='" + date + '\'' +
                ", day='" + day + '\'' +
                ", rice='" + rice + '\'' +
                ", soup='" + soup + '\'' +
                ", ban1='" + ban1 + '\'' +
                ", ban2='" + ban2 + '\'' +
                ", ban3='" + ban3 + '\'' +
                ", ban4='" + ban4 + '\'' +
                '}';
    }
}
