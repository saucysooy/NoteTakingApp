package com.example.noteapp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NewNoteScreen(notesViewModel: NotesViewModel, navToOverview: () -> Unit,modifier: Modifier = Modifier){
    var noteTitle = remember { mutableStateOf("") }
    var noteBody = remember { mutableStateOf("") }
    Surface(modifier = Modifier.fillMaxSize(),) {
        Column (horizontalAlignment = Alignment.CenterHorizontally){
            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Create New Note", fontWeight = FontWeight(800), fontSize = 25.sp)
            Text(text = "")
            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                singleLine = true,
                value = noteTitle.value,
                onValueChange = {noteTitle.value = it},
                placeholder = { Text(text = "Enter Title...") }
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                modifier = Modifier
                    .fillMaxHeight(0.6f)
                    .fillMaxWidth(0.9f),
                singleLine = false,
                value = noteBody.value,
                onValueChange = {noteBody.value = it},
                placeholder = { Text(text = "Type your note here...") }
            )
            Box (modifier = Modifier.fillMaxHeight(0.55f), contentAlignment = Alignment.BottomCenter) {
                PairOfButtons({navToOverview()},
                    {
                        notesViewModel.addNote(noteTitle.value, noteBody.value)
                        navToOverview()
                    } ,
                    "Cancel",
                    "Create")
            }
        }
    }
}