package com.example.shorthandtime;

import android.annotation.SuppressLint;
import android.database.Cursor;

import java.util.List;

public class NoteController extends Controller{
    List<Note> noteList;

    public List<Note> getNoteList() {
        updateList();
        return noteList;
    }

    public void updateNote(Note note) {
        db.execSQL("UPDATE NOTE SET last_updated_time = ?, content = ?, tag = ? WHERE created_time = ?",
            new String[]{String.valueOf(note.getLastUpdateTime()), note.getContent(), note.getTag(), String.valueOf(note.getCreatedTime())});
    }

    public Note createNote() {
        long time = System.currentTimeMillis();
        Note note = new Note(time, time, "", Tag.temporary);
        db.execSQL("INSERT INTO NOTE VALUES(?, ?, ?, ?)",
            new String[]{String.valueOf(note.getCreatedTime()), String.valueOf(note.getLastUpdateTime()), note.getContent(), note.getTag()});
        return note;
    }

    public void deleteNote(Note note) {
        db.execSQL("DELETE FROM NOTE WHERE created_time = ?", new String[]{String.valueOf(note.getCreatedTime())});
    }

    @Override
    protected void updateList() {
        noteList.clear();
        Cursor cursor = db.rawQuery("SELECT * FROM NOTE", null);
        while (cursor.moveToNext()) {
            long createdTime = cursor.getLong(cursor.getColumnIndexOrThrow("created_time"));
            long lastUpdatedTime = cursor.getLong(cursor.getColumnIndexOrThrow("last_updated_time"));
            String content = cursor.getString(cursor.getColumnIndexOrThrow("content"));
            Tag tag = Tag.valueOf(cursor.getString(cursor.getColumnIndexOrThrow("tag")));
            noteList.add(new Note(createdTime, lastUpdatedTime, content, tag));
        }
        cursor.close();
    }
}
