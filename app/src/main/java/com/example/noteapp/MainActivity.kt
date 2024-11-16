package com.example.noteapp

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.noteapp.ui.theme.NoteAppTheme
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass


data class Note (var noteTitle: String, var noteBody: String, val noteId: Int)

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val notesViewmodel by viewModels<NotesViewModel>()

        setContent {
            // Sample data
            // Since this activity gets recreated when screen is rotated, Added condition to prevent the notes getting recreated for testing purposes
            if (notesViewmodel.getNotesSize() == 0) {
                notesViewmodel.addNote("Note 1", "This is the first note.")
                notesViewmodel.addNote("Note 2", "This is the second note.")
                notesViewmodel.addNote("Note 3", "This is the third note.")

            }
            NoteAppTheme {
                val windowSize = calculateWindowSizeClass(this)
                val navController = rememberNavController()
                // Setting up start screen with navigation
                NavHost(navController = navController, startDestination = "overview") {
                    composable(
                        route = "overview"
                    ) {
                        NoteOverview(notesViewmodel ,
                            navToDetail = { noteId -> // Takes an Int parameter and passes it to the lambda to then define the unique route
                            navController.navigate("details/$noteId")
                            },
                            navToCreate = {
                                navController.navigate("create")
                            },
                            windowSize
                        )
                    }
                    // Setting up detail screen for unique routes with navigation
                    composable (
                        route = "details/{noteId}"
                    ) { backStackEntry -> // The passed id of note is stored within the stack and sent along through route
                        val noteId = backStackEntry.arguments?.getString("noteId")?.toIntOrNull() ?: -1 // Get the noteId from the route that was passed along
                        NoteDetails(navToOverview = { navController.popBackStack() },
                            navToEdit = { navController.navigate("edit/$noteId")},
                            noteId,
                            notesViewmodel)
                    }

                    composable (
                        route = "edit/{noteId}"
                    ) { backStackEntry ->
                        val noteId = backStackEntry.arguments?.getString("noteId")?.toIntOrNull() ?: -1
                        EditScreen(noteId, notesViewmodel, navToDetail = {
                            navController.popBackStack()
                        })
                    }

                    composable(
                        route = "create"
                    ) {
                        NewNoteScreen(notesViewmodel, navToOverview = { navController.popBackStack() })
                    }
                }
            }
        }
    }
}

@Composable
fun PairOfButtons(firstAction: () -> Unit, secondAction: () -> Unit ,firstActionString: String, secondActionString: String, modifier: Modifier = Modifier) {
    Row (modifier = Modifier.fillMaxWidth()) {
        Button (onClick =  firstAction , modifier = Modifier
            .weight(1f)
            .padding(horizontal = 16.dp)) {
            Text(text = firstActionString)
        }
        Button (onClick =  secondAction , modifier = Modifier
            .weight(1f)
            .padding(horizontal = 16.dp)) {
            Text(text = secondActionString)
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
        NoteDetails({}, {},0, NotesViewModel())
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
        EditScreen(0, notesViewModel = NotesViewModel(), navToDetail = {})
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
        PairOfButtons({},{},"Cancel", "Save")
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
        NewNoteScreen(notesViewModel = NotesViewModel(), navToOverview = {})
    }
}
/*
@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
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
        Surface {
            NoteOverview(NotesViewModel(), {}, {}, calculateWindowSizeClass())
        }
    }
}

 */