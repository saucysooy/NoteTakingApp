package com.example.noteapp

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class NotesViewModel : ViewModel() {
    val notes = mutableStateListOf<Note>()

    fun addNote(note: Note) {
        notes.add(note)
    }

    fun getNote(noteId: Int): Note? {
        return notes.find { it.noteId == noteId }
    }

    fun getNotesSize(): Int {
        return notes.size
    }
}