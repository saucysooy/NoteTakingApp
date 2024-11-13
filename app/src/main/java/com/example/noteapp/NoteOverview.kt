package com.example.noteapp

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NoteOverviewHeader(notes: Int, modifier: Modifier = Modifier) {
    Box ( modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(text = "Notes Overview", fontSize = 25.sp)
            Text(text = "$notes notes")
        }
    }
}

@Composable
fun NoteOverviewContent(notesViewModel: NotesViewModel, navToDetail: (Int) -> Unit, modifier: Modifier = Modifier) {
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        notesViewModel.notes.chunked(2).forEach { rowNotes ->
            Row {
                rowNotes.forEach{ note ->
                    NoteCard(note, navToDetail)

                    if ( note != rowNotes.last()) { Spacer(modifier = Modifier.width(16.dp)) }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

/* ====================================== */

@Composable
fun NoteOverview(notesViewModel: NotesViewModel, navToDetail: (Int) -> Unit, navToCreate: () -> Unit ,modifier: Modifier = Modifier) {
    Surface() {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Box(modifier = Modifier.weight(1f)) {
                NoteOverviewHeader(notesViewModel.getNotesSize())
            }
            Box(modifier = Modifier.weight(2f)) {
                NoteOverviewContent(notesViewModel, navToDetail)
            }
            CreateButton(onClick = {navToCreate()})
        }
    }

}

/* ====================================== */

@Composable
fun NoteCardContent(note: Note , modifier: Modifier = Modifier) {
    Surface (modifier = Modifier
        .width(150.dp)
        .height(150.dp), color = Color(red = 0f, green = 0f, blue = 0f, alpha = 0.05f)
    ) {
        Box (contentAlignment = Alignment.Center, modifier = Modifier.padding(horizontal = 15.dp)) {
            Text(note.noteBody,
                maxLines = 5,
                overflow = TextOverflow.Ellipsis)
        }
    }
}

@Composable
fun NoteCard (note: Note, navToDetail: (Int) -> Unit, modifier: Modifier = Modifier) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable {
            navToDetail(note.noteId)
        }
    ) {
        Text(note.noteTitle, fontWeight = FontWeight(800))
        Surface(
            shadowElevation = 5.dp, modifier = Modifier
                .clip(RoundedCornerShape(2.dp))
                .border(2.dp, MaterialTheme.colorScheme.secondary)
        ) {
            NoteCardContent(note)
        }
    }
}

@Composable
fun CreateButton(onClick: () -> Unit) {
    Box (modifier = Modifier.fillMaxWidth()) {
        FloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            onClick = { onClick() },
        ) {
            Icon(Icons.Filled.Add, "Floating action button.")
        }
    }
}