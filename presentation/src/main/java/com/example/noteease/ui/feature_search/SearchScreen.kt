package com.example.noteease.ui.feature_search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.noteease.model.NoteUiModel
import com.example.noteease.navigation.EditNoteRoute
import com.example.noteease.ui.common.CustomSearchBar
import com.example.noteease.ui.feature_note.NotesCard
import org.koin.androidx.compose.koinViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = koinViewModel(),
    navController: NavController
) {
    val searchResult = viewModel.searchResults.collectAsState().value
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(key1 = Unit) {
        focusRequester.requestFocus()
    }

    SearchScreenContent(
        searchQuery = viewModel.searchQuery.value,
        searchResult = searchResult,
        onSearchQueryChange = { viewModel.onSearchTextChange(it) },
        onNoteClicked = { navController.navigate(EditNoteRoute(it)) },
        focusRequester = focusRequester,
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreenContent(
    searchQuery: String,
    searchResult: List<NoteUiModel>,
    onSearchQueryChange: (String) -> Unit,
    onNoteClicked: (Int) -> Unit,
    focusRequester: FocusRequester,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Spacer(modifier = Modifier.size(8.dp))
        CustomSearchBar(
            focusRequester = focusRequester,
            query = searchQuery,
            onSearchQuery = onSearchQueryChange
        )
        LazyColumn(
            contentPadding = PaddingValues(vertical = 8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            items(
                count = searchResult.size,
                key = { index -> searchResult[index].id },
                itemContent = { index ->
                    val note = searchResult[index]
                    NotesCard(
                        note,
                        onClicked = {
                            onNoteClicked(note.id)
                        },
                    )
                }
            )
        }
    }
}