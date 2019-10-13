package com.example.mystudyapp.models;

public class Check {

    private String checkText;
    private Integer check;

    public Check(String checkText, Integer check) {
        this.checkText = checkText;
        this.check = check;
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
                "checkText='" + checkText + '\'' +
                ", check=" + check +
                '}';
    }
}
