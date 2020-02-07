package com.example.mystudyapp.Retrofit2;

import java.util.ArrayList;

public class ResultModel {
    int seq;
    String result;
    String user_id;
    String title;
    String content;
    String date;
    String path;
    String Lasted_Date;
    String Nick_Name;


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

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getLasted_Date() {
        return Lasted_Date;
    }

    public void setLasted_Date(String lasted_Date) {
        Lasted_Date = lasted_Date;
    }

    public String getNick_Name() {
        return Nick_Name;
    }

    public void setNick_Name(String nick_Name) {
        Nick_Name = nick_Name;
    }

    @Override
    public String toString() {
        return "ResultModel{" +
                "seq=" + seq +
                ", result='" + result + '\'' +
                ", user_id='" + user_id + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", date='" + date + '\'' +
                ", path='" + path + '\'' +
                ", Lasted_Date='" + Lasted_Date + '\'' +
                ", Nick_Name='" + Nick_Name + '\'' +
                '}';
    }
}
