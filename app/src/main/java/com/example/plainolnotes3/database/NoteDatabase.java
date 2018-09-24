package com.example.plainolnotes3.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {NoteEntity.class}, version = 1)
public abstract class NoteDatabase extends RoomDatabase {
    public static final String DATABASE_NAME = "NoteDatabase.db";
    private static volatile NoteDatabase instance;
    private static final Object LOCK = new Object();

    public abstract NoteDao noteDao();

    public static NoteDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (LOCK) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext()
                        ,NoteDatabase.class, DATABASE_NAME).build();
                }
            }
        }
        return instance;
    }
}
