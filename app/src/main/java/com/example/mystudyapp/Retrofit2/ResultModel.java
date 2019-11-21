package com.example.mystudyapp.Retrofit2;

import java.util.ArrayList;

public class ResultModel {
    String result;
    String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "ResultModel{" +
                "result='" + result + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
