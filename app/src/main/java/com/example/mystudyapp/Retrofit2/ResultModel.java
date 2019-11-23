package com.example.mystudyapp.Retrofit2;

import java.util.ArrayList;

public class ResultModel {
    String result;
    String title;
    String path;


    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }


    @Override
    public String toString() {
        return "ResultModel{" +
                "result='" + result + '\'' +
                ", title='" + title + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
