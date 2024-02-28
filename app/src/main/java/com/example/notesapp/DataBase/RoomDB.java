package com.example.notesapp.DataBase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.notesapp.Models.Notes;

@Database(entities = Notes.class,version = 1,exportSchema = false)
public abstract class RoomDB extends RoomDatabase {

    private static RoomDB dataBase;
    private static String DATABASE_NAME = "NotesApp";


    public synchronized static RoomDB getInstance(Context context){
        if(dataBase == null){
            dataBase = Room.databaseBuilder(context.getApplicationContext(),RoomDB.class,DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return dataBase;
    }

    public abstract MainDAO mainDAO();

}
