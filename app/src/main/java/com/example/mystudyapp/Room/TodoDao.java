package com.example.mystudyapp.Room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface TodoDao {


    @Query("SELECT * FROM Todo2")
    List<Todo2> getAll();


    @Insert
    void insertTodo(Todo2 todo);


    @Query("UPDATE Todo2 SET title =:check, checkGb=:checkGb WHERE id = :id")
    void updateTodo2(String check,Integer checkGb,int id);

    @Delete
    void deleteTodo(Todo2 todo);

    @Update
    void updateTodo(Todo2 todo2);
}
