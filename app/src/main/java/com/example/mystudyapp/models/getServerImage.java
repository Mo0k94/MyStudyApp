package com.example.mystudyapp.models;

public class getServerImage {
    private String Title;
    private String path;

    public getServerImage(String title, String path){
        super();
      this.Title=title;
      this.path=path;
    }
    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
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
                "Title='" + Title + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
