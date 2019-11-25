package com.example.mystudyapp.models;

public class getServerImage {
    private String user_id;
    private String Title;
    private String content;
    private String date;
    private String path;

    public getServerImage(String user_id, String title, String content, String date, String path) {
        this.user_id = user_id;
        this.Title = title;
        this.content = content;
        this.date = date;
        this.path = path;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
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

    @Override
    public String toString() {
        return "getServerImage{" +
                "user_id='" + user_id + '\'' +
                ", Title='" + Title + '\'' +
                ", content='" + content + '\'' +
                ", date='" + date + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
