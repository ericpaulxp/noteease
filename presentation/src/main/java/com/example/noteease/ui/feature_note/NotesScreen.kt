package com.example.noteease.ui.feature_note

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.noteease.R
import com.example.noteease.model.NoteUiModel
import com.example.noteease.navigation.ArchivedRoute
import com.example.noteease.navigation.EditNoteRoute
import com.example.noteease.navigation.SearchRoute
import com.example.noteease.navigation.SettingsRoute
import com.example.noteease.ui.common.CustomTopAppBar
import com.example.noteease.ui.common.EmptyScreen
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun NotesScreen(
    modifier: Modifier = Modifier,
    viewModel: NotesViewModel = koinViewModel(),
    navController: NavController
) {
    val notesState = viewModel.notesState.value

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CustomTopAppBar(
                headerText = stringResource(R.string.note)
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.background,
                actions = {
                    IconButton(onClick = {
                        navController.navigate(SettingsRoute) {
                        }
                    }) {
                        Icon(
                            modifier = Modifier.size(24.dp),
                            painter = painterResource(R.drawable.ic_settings),
                            contentDescription = null
                        )
                    }
                    IconButton(onClick = {
                        navController.navigate(ArchivedRoute)
                    }) {
                        Icon(
                            modifier = Modifier.size(24.dp),
                            painter = painterResource(R.drawable.ic_archive),
                            contentDescription = null
                        )
                    }
                    IconButton(onClick = {
                        navController.navigate(SearchRoute)
                    }) {
                        Icon(
                            modifier = Modifier.size(24.dp),
                            painter = painterResource(R.drawable.ic_search),
                            contentDescription = null
                        )
                    }
                },
                floatingActionButton = {
                    FloatingActionButton(
                        containerColor = MaterialTheme.colorScheme.primary,
                        onClick = {
                            navController.navigate(EditNoteRoute(0))
                        },
                    ) {
                        Icon(
                            modifier = Modifier.size(28.dp),
                            painter = painterResource(R.drawable.ic_plus),
                            contentDescription = null,
                        )
                    }
                },
            )
        }
    ) {
        Surface(
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(it)
        ) {
            if (notesState.notes.isEmpty()) {
                EmptyScreen(
                    text = stringResource(R.string.empty_notes_text)
                )
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background),
                    contentPadding = PaddingValues(vertical = 8.dp)
                ) {
                    items(
                        count = notesState.notes.size,
                        key = { index -> notesState.notes[index].id },
                        itemContent = { index ->
                            val notes = notesState.notes[index]
                            NotesCard(
                                note = notes,
                                onClicked = {
                                    navController.navigate(EditNoteRoute(notes.id))
                                }
                            )
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun NotesCard(
    note: NoteUiModel,
    onClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp),
        onClick = onClicked,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.4f)
        ),
        shape = RoundedCornerShape(8.dp),
    ) {

        if (note.title.isNotBlank())
            Text(
                modifier = Modifier.padding(start = 12.dp, end = 12.dp, top = 8.dp, bottom = 8.dp),
                text = note.title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary,
                maxLines = 2
            )
        if (note.body.isNotBlank())
            Text(
                modifier = Modifier.padding(start = 12.dp, end = 12.dp, top = 8.dp, bottom = 8.dp),
                text = note.body,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.secondary,
                maxLines = 5,
                overflow = TextOverflow.Ellipsis
            )
    }
}