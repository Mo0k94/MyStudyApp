package com.example.mystudyapp.Room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface TodoDao {


    @Query("SELECT * FROM `Plan`")
    List<Plan> getAll();


    @Insert
    void insertTodo(Plan plan);


    @Query("UPDATE `Plan` SET title =:check, checkGb=:checkGb WHERE id = :id")
    void updateTodo2(String check,Integer checkGb,int id);

    @Query("DELETE FROM `Plan` WHERE id = :id" )
    void deletePlan(int id);


    @Query("DELETE FROM `Plan`" )
    void deleteTodo();

    @Update
    void updateTodo(Plan plan);

    @Query("SELECT COUNT(id) FROM `Plan`")
    int getCount();

}
