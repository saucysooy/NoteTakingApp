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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditScreen(noteId: Int, notesViewModel: NotesViewModel, modifier: Modifier = Modifier){
    val note = notesViewModel.getNote(noteId)
    var noteTitle by remember { mutableStateOf(note?.noteTitle ?: "") }
    var noteBody by remember { mutableStateOf(note?.noteBody ?: "") }
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
                    notesViewModel.updateNote(noteId, Note(noteTitle, noteBody, noteId))
                },
                placeholder = { Text(text = "Enter Title...") },
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .padding(paddingValues)
            )
            TextField(
                modifier = Modifier
                    .fillMaxHeight(0.6f)
                    .fillMaxWidth(0.9f)
                    .padding(top = 16.dp),
                singleLine = false,
                value = noteBody,
                onValueChange = {
                    noteBody = it
                    notesViewModel.updateNote(noteId, Note(noteTitle, noteBody, noteId))
                },
                placeholder = { Text(text = "Type your note here...") }
            )
            Spacer(modifier = Modifier.height(32.dp))
            PairOfButtons({},{},"Cancel", "Save")
        }
    }
}