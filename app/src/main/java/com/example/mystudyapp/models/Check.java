package com.example.mystudyapp.models;

public class Check {

    private String checkText;
    private Boolean check;

    public Check(String checkText, Boolean check) {
        this.checkText = checkText;
        this.check = check;
    }

    public String getCheckText() {
        return checkText;
    }

    public void setCheckText(String checkText) {
        this.checkText = checkText;
    }

    public Boolean getCheck() {
        return check;
    }

    public void setCheck(Boolean check) {
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
