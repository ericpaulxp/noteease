package com.example.noteease.ui.feature_note

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.noteease.navigation.ArchivedDetailsRoute
import com.example.noteease.ui.common.CustomTopAppBar
import org.koin.androidx.compose.koinViewModel
import com.example.noteease.R
import com.example.noteease.ui.common.EmptyScreen

@Composable
fun ArchivedScreen(
    modifier: Modifier = Modifier,
    archivedViewModel: ArchivedViewModel = koinViewModel(),
    navController: NavController
) {
    val archivedNoteState = archivedViewModel.uiState.value
    Scaffold(
        topBar = {
            CustomTopAppBar(
                headerText = stringResource(R.string.archive),
                onBackButtonClicked = {
                    navController.navigateUp()
                }
            )
        }
    ) {
        Surface(
            modifier = modifier
                .fillMaxSize()
                .padding(it)
        ) {
            if (archivedNoteState.notes.isEmpty()) {
                EmptyScreen(
                    text = stringResource(R.string.empty_archive_text)
                )
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background),
                    contentPadding = PaddingValues(vertical = 8.dp)
                ) {
                    items(archivedNoteState.notes) { note ->
                        NotesCard(
                            note,
                            onClicked = {
                                navController.navigate(ArchivedDetailsRoute(note.id))
                            },
                        )
                    }
                }
            }
        }
    }
}