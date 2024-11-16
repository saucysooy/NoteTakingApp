package com.example.noteapp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NewNoteScreen(
    notesViewModel: NotesViewModel,
    navToOverview: () -> Unit,
    windowSize: WindowSizeClass,
    modifier: Modifier = Modifier
) {
    val noteTitle = remember { mutableStateOf("") }
    val noteBody = remember { mutableStateOf("") }
    val titleError = remember { mutableStateOf("") }
    val bodyError = remember { mutableStateOf("") }

    when (windowSize.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            CompactNewNoteScreen(
                noteTitle,
                noteBody,
                titleError,
                bodyError,
                notesViewModel,
                navToOverview,
                modifier
            )
        }
        WindowWidthSizeClass.Medium -> {
            MediumNewNoteScreen(
                noteTitle,
                noteBody,
                titleError,
                bodyError,
                notesViewModel,
                navToOverview,
                modifier
            )
        }
        WindowWidthSizeClass.Expanded -> {
            ExpandedNewNoteScreen(
                noteTitle,
                noteBody,
                titleError,
                bodyError,
                notesViewModel,
                navToOverview,
                modifier
            )
        }
        else -> {
            CompactNewNoteScreen(
                noteTitle,
                noteBody,
                titleError,
                bodyError,
                notesViewModel,
                navToOverview,
                modifier
            )
        }
    }
}

@Composable
fun ExpandedNewNoteScreen(
    noteTitle: MutableState<String>,
    noteBody: MutableState<String>,
    titleError: MutableState<String>,
    bodyError: MutableState<String>,
    notesViewModel: NotesViewModel,
    navToOverview: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Create New Note",
            modifier = Modifier.padding(top = 48.dp),
            fontWeight = FontWeight(800),
            fontSize = 25.sp
        )
        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            singleLine = true,
            value = noteTitle.value,
            onValueChange = {
                noteTitle.value = it
                titleError.value = ""
            },
            placeholder = { Text(text = "Enter Title...") }
        )

        if (titleError.value.isNotEmpty()) {
            Text(text = titleError.value, color = Color.Red)
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            modifier = Modifier
                .fillMaxHeight(0.6f)
                .fillMaxWidth(0.9f),
            singleLine = false,
            value = noteBody.value,
            onValueChange = {
                noteBody.value = it
                bodyError.value = ""
            },
            placeholder = { Text(text = "Type your note here...") }
        )

        if (bodyError.value.isNotEmpty()) {
            Text(text = bodyError.value, color = Color.Red)
        }

        Box(
            modifier = Modifier.fillMaxHeight(0.55f),
            contentAlignment = Alignment.BottomCenter
        ) {
            PairOfButtons(
                { navToOverview() },
                {
                    val titleValid = noteTitle.value.isNotBlank() && noteTitle.value.length <= 20
                    val bodyValid = noteBody.value.length <= 200

                    if (titleValid && bodyValid) {
                        notesViewModel.addNote(noteTitle.value, noteBody.value)
                        navToOverview()
                    } else {
                        if (noteTitle.value.isBlank()) {
                            titleError.value = "Title cannot be blank"
                        } else if (noteTitle.value.length > 20) {
                            titleError.value = "Title cannot be longer than 20 characters"
                        }
                        if (noteBody.value.length > 200) {
                            bodyError.value = "Body cannot be longer than 200 characters"
                        }
                    }
                },
                "Cancel",
                "Create",
                modifier = modifier.padding(horizontal = 128.dp)
            )
        }
    }
}

@Composable
fun MediumNewNoteScreen(
    noteTitle: MutableState<String>,
    noteBody: MutableState<String>,
    titleError: MutableState<String>,
    bodyError: MutableState<String>,
    notesViewModel: NotesViewModel,
    navToOverview: () -> Unit,
    modifier: Modifier = Modifier) {

    Column (horizontalAlignment = Alignment.CenterHorizontally){
        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Create New Note", modifier = Modifier.padding(top = 48.dp), fontWeight = FontWeight(800), fontSize = 25.sp)
        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            singleLine = true,
            value = noteTitle.value,
            onValueChange =
            {
                noteTitle.value = it
                titleError.value = ""
            },
            placeholder = { Text(text = "Enter Title...") }
        )

        if (titleError.value.isNotEmpty()) {
            Text(text = titleError.value, color = Color.Red)
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            modifier = Modifier
                .fillMaxHeight(0.6f)
                .fillMaxWidth(0.9f),
            singleLine = false,
            value = noteBody.value,
            onValueChange = {
                noteBody.value = it
                bodyError.value = ""
            },
            placeholder = { Text(text = "Type your note here...") }
        )

        if (bodyError.value.isNotEmpty()) {
            Text(text = bodyError.value, color = Color.Red)
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
                            titleError.value = "Title cannot be blank"
                        }
                        else if (noteTitle.value.length > 20) {
                            titleError.value = "Title cannot be longer than 20 characters"
                        }
                        if (noteBody.value.length > 200) {
                            bodyError.value = "Body cannot be longer than 200 characters"
                        }
                    }
                } , "Cancel", "Create", modifier = modifier.padding(horizontal = 32.dp))
        }
    }
}

@Composable
fun CompactNewNoteScreen(
    noteTitle: MutableState<String>,
    noteBody: MutableState<String>,
    titleError: MutableState<String>,
    bodyError: MutableState<String>,
    notesViewModel: NotesViewModel,
    navToOverview: () -> Unit,
    modifier: Modifier) {
    Column (horizontalAlignment = Alignment.CenterHorizontally){
        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Create New Note", modifier = Modifier.padding(top = 48.dp), fontWeight = FontWeight(800), fontSize = 25.sp)
        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            singleLine = true,
            value = noteTitle.value,
            onValueChange = {
                noteTitle.value = it
                titleError.value = ""
            },
            placeholder = { Text(text = "Enter Title...") }
        )

        if (titleError.value.isNotEmpty()) { // Access value from MutableState
            Text(text = titleError.value, color = Color.Red)
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            modifier = Modifier
                .fillMaxHeight(0.6f)
                .fillMaxWidth(0.9f),
            singleLine = false,
            value = noteBody.value,
            onValueChange = {
                noteBody.value = it
                bodyError.value = ""
            },
            placeholder = { Text(text = "Type your note here...") }
        )

        if (bodyError.value.isNotEmpty()) {
            Text(text = bodyError.value, color = Color.Red)
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
                            titleError.value = "Title cannot be blank"
                        }
                        else if (noteTitle.value.length > 20) {
                            titleError.value = "Title cannot be longer than 20 characters"
                        }
                        if (noteBody.value.length > 200) {
                            bodyError.value = "Body cannot be longer than 200 characters"
                        }
                    }
                } , "Cancel", "Create", modifier = modifier.padding(horizontal = 16.dp))
        }
    }
}
