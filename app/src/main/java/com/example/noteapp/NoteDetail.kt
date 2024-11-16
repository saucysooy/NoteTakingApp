package com.example.noteapp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NoteDetails(navToOverview: () -> Unit, navToEdit: () -> Unit, noteId: Int, notesViewModel: NotesViewModel, windowSize: WindowSizeClass, modifier: Modifier = Modifier) {
    val note = remember { notesViewModel.getNote(noteId) }

    when (windowSize.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            CompactNoteDetails(navToOverview, navToEdit, note, modifier)
        }
        WindowWidthSizeClass.Medium -> {
            MediumNoteDetails(navToOverview, navToEdit, note, modifier)
        }
        WindowWidthSizeClass.Expanded -> {
            ExpandedNoteDetails(navToOverview, navToEdit, note, modifier)
        }
        else -> {
            CompactNoteDetails(navToOverview, navToEdit, note, modifier)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CompactNoteDetails ( navToOverview: () -> Unit, navToEdit: () -> Unit, note: Note?, modifier: Modifier = Modifier) {
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
                PairOfButtons(navToOverview, navToEdit ,firstActionString = "Cancel", secondActionString = "Edit", modifier = Modifier.padding(horizontal = 16.dp))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MediumNoteDetails(
    navToOverview: () -> Unit,
    navToEdit: () -> Unit,
    note: Note?,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(modifier = Modifier.fillMaxWidth(0.9f)) {
                        Text(
                            text = "Note Details",
                            modifier = Modifier.align(Alignment.Center),
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight(800)
                        )
                    }
                },
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .align(Alignment.Center)
            ) {
                Text(text = note?.noteTitle ?: "", fontWeight = FontWeight(800), fontSize = 20.sp)
                Spacer(modifier = Modifier.height(20.dp))
                Text(text = note?.noteBody ?: "", fontSize = 16.sp)
                Spacer(modifier = Modifier.height(20.dp))
                PairOfButtons(
                    firstAction = navToOverview,
                    secondAction = navToEdit,
                    firstActionString = "Cancel",
                    secondActionString = "Edit",
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpandedNoteDetails(
    navToOverview: () -> Unit,
    navToEdit: () -> Unit,
    note: Note?,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(modifier = Modifier.fillMaxWidth(0.9f)) {
                        Text(
                            text = "Note Details",
                            modifier = Modifier.align(Alignment.Center),
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight(800)
                        )
                    }
                },
            )
        }
    ) { paddingValues ->
        Row(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 16.dp)
            ) {
                Text(text = note?.noteTitle ?: "", fontWeight = FontWeight(800), fontSize = 24.sp)
                Spacer(modifier = Modifier.height(20.dp))
                PairOfButtons(
                    firstAction = navToOverview,
                    secondAction = navToEdit,
                    firstActionString = "Cancel",
                    secondActionString = "Edit",
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
            Column(
                modifier = Modifier.weight(2f)
            ) {
                Text(text = note?.noteBody ?: "", fontSize = 18.sp)
            }
        }
    }
}