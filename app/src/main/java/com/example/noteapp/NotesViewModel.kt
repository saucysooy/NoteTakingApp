package com.example.noteapp

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class NotesViewModel : ViewModel() {
    val notes = mutableStateListOf<Note>()

    // Simple counter to assign unique IDs to notes since we are not dealing with databases
    private var noteIdCounter = 1

    fun addNote(noteTitle: String, noteBody: String) {
        val note = Note(
            noteTitle = noteTitle,
            noteBody = noteBody,
            noteId = noteIdCounter
        )
        notes.add(note)
        noteIdCounter++
    }

    fun getNote(noteId: Int): Note? {
        return notes.find { it.noteId == noteId }
    }

    fun getNotesSize(): Int {
        return notes.size
    }

    fun updateNote(noteId: Int, newNote: Note) {
        val index = notes.indexOfFirst { it.noteId == noteId }
        if (index >= 0) {
            notes[index] = newNote
        }
    }
}