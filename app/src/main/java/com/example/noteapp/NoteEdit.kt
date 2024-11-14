package com.example.noteapp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditScreen(noteId: Int, notesViewModel: NotesViewModel, navToDetail: () -> Unit, modifier: Modifier = Modifier){
    val note = notesViewModel.getNote(noteId)
    var noteTitle by remember { mutableStateOf(note?.noteTitle ?: "") }
    var noteBody by remember { mutableStateOf(note?.noteBody ?: "") }

    val originalNoteTitle = note?.noteTitle ?: ""
    val originalNoteBody = note?.noteBody ?: ""

    var titleError by remember { mutableStateOf<String?>(null) }
    var bodyError by remember { mutableStateOf<String?>(null) }

    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    Box(modifier = Modifier.fillMaxWidth(0.95f)) {
                        Text(text = "Editing Note", modifier = Modifier.align(Alignment.Center), textAlign = TextAlign.Center,fontWeight = FontWeight(800))
                    }
                },

                )
        }
    ) { paddingValues ->
        Column (horizontalAlignment = Alignment.CenterHorizontally) {
            TextField(
                singleLine = true,
                value = noteTitle,
                onValueChange = {
                    noteTitle = it
                    titleError = null
                },
                placeholder = { Text(text = "Enter Title...") },
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .padding(paddingValues)
            )
            if (titleError != null) {
                Text(text = titleError!!, color = Color.Red)
            }


            TextField(
                modifier = Modifier
                    .fillMaxHeight(0.6f)
                    .fillMaxWidth(0.9f)
                    .padding(top = 16.dp),
                singleLine = false,
                value = noteBody,
                onValueChange = {
                    noteBody = it
                    bodyError = null
                },
                placeholder = { Text(text = "Type your note here...") }
            )
            if (bodyError != null) {
                Text(text = bodyError!!, color = Color.Red)
            }
            Spacer(modifier = Modifier.height(32.dp))
            PairOfButtons(
                {
                    // Reset the values to the original ones
                    noteTitle = originalNoteTitle
                    noteBody = originalNoteBody
                    notesViewModel.updateNote(noteId, Note(originalNoteTitle, originalNoteBody, noteId))
                    navToDetail()
                },
                {
                    val titleValid = noteTitle.isNotBlank() && noteTitle.length <= 25
                    val bodyValid =  noteBody.length <= 200

                    if (titleValid && bodyValid) {
                        notesViewModel.updateNote(noteId, Note(noteTitle, noteBody, noteId)) // Make sure the new values are saved
                        navToDetail()
                    } else {
                        if (noteTitle.isBlank()) {
                            titleError = "Title cannot be empty"
                        }
                        else if (noteTitle.length > 20) {
                            titleError = "Title cannot be longer than 20 characters"
                        }
                        if (noteBody.length > 200) {
                            bodyError = "Body cannot be longer than 200 characters"
                        }
                    }
                },
                "Cancel",
                "Save"
            )
        }
    }
}