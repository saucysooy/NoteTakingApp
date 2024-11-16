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
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
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

@Composable
fun EditScreen(noteId: Int, notesViewModel: NotesViewModel, navToDetail: () -> Unit, windowSize: WindowSizeClass, modifier: Modifier = Modifier){
    val note = notesViewModel.getNote(noteId)
    var noteTitle = remember { mutableStateOf(note?.noteTitle ?: "") }
    var noteBody = remember { mutableStateOf(note?.noteBody ?: "") }

    val originalNoteTitle = note?.noteTitle ?: ""
    val originalNoteBody = note?.noteBody ?: ""

    var titleError by remember { mutableStateOf("") }
    var bodyError by remember { mutableStateOf("") }

    when (windowSize.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            CompactEditScreen(
                noteId,
                notesViewModel,
                noteTitle,
                noteBody,
                remember { mutableStateOf(titleError) },
                remember { mutableStateOf(bodyError) },
                originalNoteTitle,
                originalNoteBody,
                navToDetail)
        }
        WindowWidthSizeClass.Medium -> {
            MediumEditScreen(
                noteId,
                notesViewModel,
                noteTitle,
                noteBody,
                remember { mutableStateOf(titleError) },
                remember { mutableStateOf(bodyError) },
                originalNoteTitle,
                originalNoteBody,
                navToDetail)
        }
        WindowWidthSizeClass.Expanded -> {
            ExpandedEditScreen(
                noteId,
                notesViewModel,
                noteTitle,
                noteBody,
                remember { mutableStateOf(titleError) },
                remember { mutableStateOf(bodyError) },
                originalNoteTitle,
                originalNoteBody,
                navToDetail)
        }
        else -> {

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MediumEditScreen(
    noteId: Int,
    notesViewModel: NotesViewModel,
    noteTitle: MutableState<String>,
    noteBody: MutableState<String>,
    titleError: MutableState<String>,
    bodyError: MutableState<String>,
    originalNoteTitle: String,
    originalNoteBody: String,
    navToDetail: () -> Unit
) {
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
                value = noteTitle.value,
                onValueChange = {
                    noteTitle.value = it
                    titleError.value = ""
                },
                placeholder = { Text(text = "Enter Title...") },
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .padding(paddingValues)
            )
            if (titleError.value != "") {
                Text(text = titleError.value, color = Color.Red)
            }


            TextField(
                modifier = Modifier
                    .fillMaxHeight(0.6f)
                    .fillMaxWidth(0.9f)
                    .padding(top = 16.dp),
                singleLine = false,
                value = noteBody.value,
                onValueChange = {
                    noteBody.value = it
                    bodyError.value = ""
                },
                placeholder = { Text(text = "Type your note here...") }
            )
            if (bodyError.value != "") {
                Text(text = bodyError.value, color = Color.Red)
            }
            Spacer(modifier = Modifier.height(32.dp))
            PairOfButtons(
                {
                    // Reset the values to the original ones
                    noteTitle.value = originalNoteTitle
                    noteBody.value = originalNoteBody
                    notesViewModel.updateNote(noteId, Note(originalNoteTitle, originalNoteBody, noteId))
                    navToDetail()
                },
                {
                    val titleValid = noteTitle.value.isNotBlank() && noteTitle.value.length <= 25
                    val bodyValid =  noteBody.value.length <= 200

                    if (titleValid && bodyValid) {
                        notesViewModel.updateNote(noteId, Note(noteTitle.value, noteBody.value, noteId)) // Make sure the new values are saved
                        navToDetail()
                    } else {
                        if (noteTitle.value.isBlank()) {
                            titleError.value = "Title cannot be empty"
                        }
                        else if (noteTitle.value.length > 20) {
                            titleError.value = "Title cannot be longer than 20 characters"
                        }
                        if (noteBody.value.length > 200) {
                            bodyError.value = "Body cannot be longer than 200 characters"
                        }
                    }
                },
                "Cancel",
                "Save",
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpandedEditScreen(
    noteId: Int,
    notesViewModel: NotesViewModel,
    noteTitle: MutableState<String>,
    noteBody: MutableState<String>,
    titleError: MutableState<String>,
    bodyError: MutableState<String>,
    originalNoteTitle: String,
    originalNoteBody: String,
    navToDetail: () -> Unit
) {
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
                value = noteTitle.value,
                onValueChange = {
                    noteTitle.value = it
                    titleError.value = ""
                },
                placeholder = { Text(text = "Enter Title...") },
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .padding(paddingValues)
            )
            if (titleError.value != "") {
                Text(text = titleError.value, color = Color.Red)
            }


            TextField(
                modifier = Modifier
                    .fillMaxHeight(0.6f)
                    .fillMaxWidth(0.9f)
                    .padding(top = 16.dp),
                singleLine = false,
                value = noteBody.value,
                onValueChange = {
                    noteBody.value = it
                    bodyError.value = ""
                },
                placeholder = { Text(text = "Type your note here...") }
            )
            if (bodyError.value != "") {
                Text(text = bodyError.value , color = Color.Red)
            }
            Spacer(modifier = Modifier.height(32.dp))
            PairOfButtons(
                {
                    // Reset the values to the original ones
                    noteTitle.value = originalNoteTitle
                    noteBody.value = originalNoteBody
                    notesViewModel.updateNote(noteId, Note(originalNoteTitle, originalNoteBody, noteId))
                    navToDetail()
                },
                {
                    val titleValid = noteTitle.value.isNotBlank() && noteTitle.value.length <= 25
                    val bodyValid =  noteBody.value.length <= 200

                    if (titleValid && bodyValid) {
                        notesViewModel.updateNote(noteId, Note(noteTitle.value, noteBody.value, noteId)) // Make sure the new values are saved
                        navToDetail()
                    } else {
                        if (noteTitle.value.isBlank()) {
                            titleError.value = "Title cannot be empty"
                        }
                        else if (noteTitle.value.length > 20) {
                            titleError.value = "Title cannot be longer than 20 characters"
                        }
                        if (noteBody.value.length > 200) {
                            bodyError.value = "Body cannot be longer than 200 characters"
                        }
                    }
                },
                "Cancel",
                "Save",
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CompactEditScreen(
    noteId: Int,
    notesViewModel: NotesViewModel,
    noteTitle: MutableState<String>,
    noteBody: MutableState<String>,
    titleError: MutableState<String?>,
    bodyError: MutableState<String?>,
    originalNoteTitle: String,
    originalNoteBody: String,
    navToDetail: () -> Unit) {
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
                value = noteTitle.value,
                onValueChange = {
                    noteTitle.value = it
                    titleError.value = ""
                },
                placeholder = { Text(text = "Enter Title...") },
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .padding(paddingValues)
            )
            if (titleError.value != "") {
                Text(text = titleError.value!!, color = Color.Red) // title will never be null here
                                                                   // which is why the !! is used since it tells the compiler it will never be null
            }


            TextField(
                modifier = Modifier
                    .fillMaxHeight(0.6f)
                    .fillMaxWidth(0.9f)
                    .padding(top = 16.dp),
                singleLine = false,
                value = noteBody.value,
                onValueChange = {
                    noteBody.value = it
                    bodyError.value = ""
                },
                placeholder = { Text(text = "Type your note here...") }
            )
            if (bodyError.value != "") {
                Text(text = bodyError.value!! , color = Color.Red) // body will never be null here
                                                                   // which is why the !! is used since it tells the compiler it will never be null
            }
            Spacer(modifier = Modifier.height(32.dp))
            PairOfButtons(
                {
                    // Reset the values to the original ones
                    noteTitle.value = originalNoteTitle
                    noteBody.value = originalNoteBody
                    notesViewModel.updateNote(noteId, Note(originalNoteTitle, originalNoteBody, noteId))
                    navToDetail()
                },
                {
                    val titleValid = noteTitle.value.isNotBlank() && noteTitle.value.length <= 25
                    val bodyValid =  noteBody.value.length <= 200

                    if (titleValid && bodyValid) {
                        notesViewModel.updateNote(noteId, Note(noteTitle.value, noteBody.value, noteId)) // Make sure the new values are saved
                        navToDetail()
                    } else {
                        if (noteTitle.value.isBlank()) {
                            titleError.value = "Title cannot be empty"
                        }
                        else if (noteTitle.value.length > 20) {
                            titleError.value = "Title cannot be longer than 20 characters"
                        }
                        if (noteBody.value.length > 200) {
                            bodyError.value = "Body cannot be longer than 200 characters"
                        }
                    }
                },
                "Cancel",
                "Save",
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}

