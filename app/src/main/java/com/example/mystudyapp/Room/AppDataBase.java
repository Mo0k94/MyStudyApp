package com.example.mystudyapp.Room;

import androidx.room.Database;
import androidx.room.RoomDatabase;


@Database(entities = {Plan.class}, version = 1, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {

    public abstract TodoDao todoDao();


}
