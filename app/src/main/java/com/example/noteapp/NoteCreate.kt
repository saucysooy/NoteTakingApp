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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.padding

@Composable
fun NewNoteScreen(notesViewModel: NotesViewModel, navToOverview: () -> Unit,modifier: Modifier = Modifier){
    var noteTitle = remember { mutableStateOf("") }
    var noteBody = remember { mutableStateOf("") }

    var titleError by remember { mutableStateOf<String?>(null) }
    var bodyError by remember { mutableStateOf<String?>(null) }

    Surface(modifier = Modifier.fillMaxSize(),) {
        Column (horizontalAlignment = Alignment.CenterHorizontally){
            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Create New Note", modifier = Modifier.padding(top = 48.dp), fontWeight = FontWeight(800), fontSize = 25.sp)
            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                singleLine = true,
                value = noteTitle.value,
                onValueChange = {noteTitle.value = it},
                placeholder = { Text(text = "Enter Title...") }
            )

            if (titleError != null) {
                Text(text = titleError!!, color = Color.Red)
            }

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

            if (bodyError != null) {
                Text(text = bodyError!!, color = Color.Red)
            }

            Box (modifier = Modifier.fillMaxHeight(0.55f), contentAlignment = Alignment.BottomCenter) {
                PairOfButtons({navToOverview()},
                    {
                        val titleValid = noteTitle.value.isNotBlank() && noteTitle.value.length <= 25
                        val bodyValid =  noteBody.value.length <= 200

                        if (titleValid && bodyValid) {
                            notesViewModel.addNote(noteTitle.value, noteBody.value)
                            navToOverview()
                        } else {
                            if (noteTitle.value.isBlank()) {
                                titleError = "Title cannot be empty"
                            }
                            else if (noteTitle.value.length > 20) {
                                titleError = "Title cannot be longer than 20 characters"
                            }
                            if (noteBody.value.length > 200) {
                                bodyError = "Body cannot be longer than 200 characters"
                            }
                        }

                    } ,
                    "Cancel",
                    "Create")
            }
        }
    }
}