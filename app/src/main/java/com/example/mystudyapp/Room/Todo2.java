package com.example.mystudyapp.Room;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Todo2 {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private Integer checkGb;

    public Todo2(String title, Integer checkGb) {
        this.title = title;
        this.checkGb = checkGb;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getCheckGb() {
        return checkGb;
    }

    public void setCheckGb(Integer checkGb) {
        this.checkGb = checkGb;
    }



    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", checkGb=" + checkGb +
                '}';
    }
}
