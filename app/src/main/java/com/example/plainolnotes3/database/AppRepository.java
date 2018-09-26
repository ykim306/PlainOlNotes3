package com.example.plainolnotes3.database;

import com.example.plainolnotes3.utilities.SampleData;

import java.util.List;

public class AppRepository {
    private static final AppRepository ourInstance = new AppRepository();

    public static AppRepository getInstance() {
        return ourInstance;
    }

    public List<NoteEntity> mNotes;

    private AppRepository() {
        mNotes = SampleData.getNotes();
    }
}
