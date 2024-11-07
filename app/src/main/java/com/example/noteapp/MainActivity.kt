package com.example.noteapp

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.noteapp.ui.theme.NoteAppTheme
import androidx.compose.ui.text.input.TextFieldValue


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NoteAppTheme {
                Surface() {
                    NoteOverview()
                }
            }
        }
    }
}

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
fun NoteOverviewContent(modifier: Modifier = Modifier) {
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        Row {
            NoteCard()
            Spacer(modifier = Modifier.width(16.dp))
            NoteCard()
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun NoteOverview(modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Box(modifier = Modifier.weight(1f)) {
            NoteOverviewHeader(5)
        }
        Box(modifier = Modifier.weight(2f)) {
            NoteOverviewContent()
        }
        CreateButton(onClick = {})
    }

}

@Composable
fun NoteCardContent(modifier: Modifier = Modifier) {
    Surface (modifier = Modifier
        .width(150.dp)
        .height(150.dp), color = Color(red = 0f, green = 0f, blue = 0f, alpha = 0.05f)) {
        Box (contentAlignment = Alignment.Center, modifier = Modifier.padding(horizontal = 15.dp)) {
            Text("SAMPLE CONTENT TEXT, " +
                    "LAMBDA LOREM IPSUM LAMBDA LOREM IPSUM " +
                    "LAMBDA LOREM IPSUM LAMBDA LOREM IPSUM " +
                    "LAMBDA LOREM IPSUM LAMBDA LOREM IPSUM",
                     maxLines = 5,
                     overflow = TextOverflow.Ellipsis)
        }
    }
}

@Composable
fun NoteCard (modifier: Modifier = Modifier) {
    Column (horizontalAlignment = Alignment.CenterHorizontally) {
        Text("NOTE TITLE", fontWeight = FontWeight(800))
        Surface(shadowElevation = 5.dp, modifier = Modifier
            .clip(RoundedCornerShape(2.dp))
            .border(2.dp, MaterialTheme.colorScheme.secondary)) {
            NoteCardContent()
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

@Composable
fun NewNoteScreen(modifier: Modifier = Modifier){
    Surface(modifier = Modifier.fillMaxSize(),) {
        Column (horizontalAlignment = Alignment.CenterHorizontally){
            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Create New Note", fontWeight = FontWeight(800), fontSize = 25.sp)
            Text(text = "")
            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                singleLine = true,
                value = "",
                onValueChange = {},
                placeholder = { Text(text = "Enter Title...") }
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                modifier = Modifier
                    .fillMaxHeight(0.6f)
                    .fillMaxWidth(0.9f),
                singleLine = false,
                value = "",
                onValueChange = {},
                placeholder = { Text(text = "Type your note here...") }
            )
            Box (modifier = Modifier.fillMaxHeight(0.55f), contentAlignment = Alignment.BottomCenter) {
                PairOfButtons("Create")
            }
        }
    }
}

@Composable
fun PairOfButtons(action: String, modifier: Modifier = Modifier) {
    Row (modifier = Modifier.fillMaxWidth()) {
        Button (onClick = { /*TODO*/ }, modifier = Modifier
            .weight(1f)
            .padding(horizontal = 16.dp)) {
            Text(text = "Cancel")
        }
        Button (onClick = { /*TODO*/ }, modifier = Modifier
            .weight(1f)
            .padding(horizontal = 16.dp)) {
            Text(text = action)
        }
    }
}

@Preview(
    name = "Light Mode",
    showBackground = true,
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "Dark Mode",
    showBackground = true,
)
@Composable
fun PairOfButtonsPreview() {
    NoteAppTheme {
        PairOfButtons("Save")
    }
}

@Preview(
    name = "Light Mode",
    showBackground = true,
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "Dark Mode",
    showBackground = true,
)
@Composable
fun CreateScreenPreview() {
    NoteAppTheme {
        NewNoteScreen()
    }
}

@Preview(
    name = "Light Mode",
    showBackground = true,
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "Dark Mode",
    showBackground = true,
)
@Composable
fun NoteOverviewPreview() {
    NoteAppTheme {
        Surface() {
            NoteOverview()
        }
    }
}