package com.example.plainolnotes3;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.example.plainolnotes3.database.NoteDao;
import com.example.plainolnotes3.database.NoteDatabase;
import com.example.plainolnotes3.database.NoteEntity;
import com.example.plainolnotes3.utilities.SampleData;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class DatabaseTest {
    public static final String TAG = "DatabaseTest";

    private NoteDatabase mDb;
    private NoteDao mDao;

    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getTargetContext();
        mDb = Room.inMemoryDatabaseBuilder(context, NoteDatabase.class)
                .build();
        mDao = mDb.noteDao();
        Log.i(TAG, "createDb");
    }

    @After
    public void destroyDb() {
        mDb.close();
        Log.i(TAG, "destroyDb");
    }

    @Test
    public void createAndRetreiveNotes() {
        mDao.insertNoteAll(SampleData.getNotes());
        int count = mDao.getCount();
        Log.i(TAG, "createAndRetreiveNotes: count =" + count);
        assertEquals(SampleData.getNotes().size(), count);
    }

    @Test
    public void compareString() {
        mDao.insertNoteAll(SampleData.getNotes());
        NoteEntity source = SampleData.getNotes().get(0);
        NoteEntity fromDb = mDao.getNoteById(1);
        assertEquals(source.getText(), fromDb.getText());
        assertEquals(1, fromDb.getId());
    }

}
