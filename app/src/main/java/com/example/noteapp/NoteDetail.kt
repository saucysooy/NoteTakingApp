package com.example.noteapp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteDetails(navToOverview: () -> Unit, navToEdit: () -> Unit, noteId: Int, notesViewModel: NotesViewModel ,modifier: Modifier = Modifier) {
    val note = notesViewModel.getNote(noteId)
    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    Box(modifier = Modifier.fillMaxWidth(0.95f)) {
                        Text(text = "Note Details", modifier = Modifier.align(Alignment.Center),
                            textAlign = TextAlign.Center,fontWeight = FontWeight(800)
                        )
                    }
                },
            )
        },
        bottomBar = {
            BottomAppBar {
                Button (onClick = { /*TODO*/ }, modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp)) {
                    Text("Delete")
                }
            }
        }
    ){ paddingValues ->
        Box(modifier = Modifier.padding(paddingValues).fillMaxSize()) {
            Column (modifier = Modifier.fillMaxWidth(0.9f).padding(start = 16.dp)) {
                Text(text = note?.noteTitle ?: "", fontWeight = FontWeight(800))
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = note?.noteBody ?: "")
                Spacer(modifier = Modifier.height(16.dp))
                PairOfButtons(navToOverview, navToEdit ,firstActionString = "Cancel", secondActionString = "Edit")
            }
        }
    }
}