package com.hienthai.notesapp.listeners;

import com.hienthai.notesapp.entities.Note;

public interface NotesListener {

    void onNoteClicked(Note note, int position);
}
