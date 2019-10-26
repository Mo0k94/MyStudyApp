package com.example.mystudyapp.models;

public class Check {

    private int id;

    private String checkText;
    private Integer check;

    public Check(String checkText, Integer check) {
        this.checkText = checkText;
        this.check = check;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCheckText() {
        return checkText;
    }

    public void setCheckText(String checkText) {
        this.checkText = checkText;
    }

    public Integer getCheck() {
        return check;
    }

    public void setCheck(Integer check) {
        this.check = check;
    }

    @Override
    public String toString() {
        return "Check{" +
                "id=" + id +
                ", checkText='" + checkText + '\'' +
                ", check=" + check +
                '}';
    }
}
