package com.hienthai.notesapp.database;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.hienthai.notesapp.dao.NoteDAO;
import com.hienthai.notesapp.entities.Note;

@Database(entities = Note.class, version = 1, exportSchema = false)
public abstract class NotesDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "notes.db";

    private static NotesDatabase instance;

    public static synchronized NotesDatabase getInstance(Context context){
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), NotesDatabase.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;

    }

    public abstract NoteDAO noteDAO();
}
