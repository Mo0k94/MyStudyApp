package com.example.mystudyapp.models;

import java.io.Serializable;

/**
 * Created by user on 2018-01-01.
 */

public class ListItem implements Serializable {

    private String title;
    private String title2;
    private Class cls;
    public ListItem(String title, String title2,Class cls) {
        this.title = title;
        this.title2= title2;
        this.cls = cls;

    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle2() {
        return title2;
    }

    public void setTitle2(String title2) {
        this.title2 = title2;
    }

    public Class getCls() {
        return cls;
    }

    public void setCls(Class cls) {
        this.cls = cls;
    }

    @Override
    public String toString() {
        return "ListItem{" +
                "title='" + title + '\'' +
                ", title2='" + title2 + '\'' +
                ", cls=" + cls +
                '}';
    }
}

