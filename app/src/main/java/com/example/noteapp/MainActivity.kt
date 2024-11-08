package com.example.noteapp

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.noteapp.ui.theme.NoteAppTheme


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
fun PairOfButtons(firstAction: String, secondAction: String, modifier: Modifier = Modifier) {
    Row (modifier = Modifier.fillMaxWidth()) {
        Button (onClick = { /*TODO*/ }, modifier = Modifier
            .weight(1f)
            .padding(horizontal = 16.dp)) {
            Text(text = firstAction)
        }
        Button (onClick = { /*TODO*/ }, modifier = Modifier
            .weight(1f)
            .padding(horizontal = 16.dp)) {
            Text(text = secondAction)
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
fun NoteDetailsPreview() {
    NoteAppTheme {
        NoteDetails()
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
fun EditScreenPreview() {
    NoteAppTheme {
        EditScreen("Placeholder Title", "Placeholder Body")
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
        PairOfButtons("Cancel", "Save")
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