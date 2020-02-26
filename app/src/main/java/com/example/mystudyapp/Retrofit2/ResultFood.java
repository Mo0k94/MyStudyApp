package com.example.mystudyapp.Retrofit2;

public class ResultFood {
    int seq;
    String result;
    String food_div;
    String food_name;
    String food_price;
    String food_path;

    public ResultFood(int seq, String food_div, String food_name, String food_price, String food_path) {
        this.seq = seq;
        this.result = result;
        this.food_div = food_div;
        this.food_name = food_name;
        this.food_price = food_price;
        this.food_path = food_path;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getFood_div() {
        return food_div;
    }

    public void setFood_div(String food_div) {
        this.food_div = food_div;
    }

    public String getFood_name() {
        return food_name;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }

    public String getFood_price() {
        return food_price;
    }

    public void setFood_price(String food_price) {
        this.food_price = food_price;
    }

    public String getFood_path() {
        return food_path;
    }

    public void setFood_path(String food_path) {
        this.food_path = food_path;
    }


    @Override
    public String toString() {
        return "ResultFood{" +
                "seq=" + seq +
                ", result='" + result + '\'' +
                ", food_div='" + food_div + '\'' +
                ", food_name='" + food_name + '\'' +
                ", food_price='" + food_price + '\'' +
                ", food_path='" + food_path + '\'' +
                '}';
    }
}
