package com.example.noteease.ui.feature_search

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.use_case.SearchNoteUseCase
import com.example.noteease.model.NoteUiModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlin.collections.map

class SearchViewModel(
    searchNoteUseCase: SearchNoteUseCase,
) : ViewModel() {

    var searchQuery = mutableStateOf("")
        private set

    val notesFlow = searchNoteUseCase.noteList.map { note ->
        note.map {
            NoteUiModel(
                title = it.title,
                body = it.body,
                id = it.id
            )
        }
    }

    val searchResults: StateFlow<List<NoteUiModel>> =
        snapshotFlow { searchQuery.value }
            .combine(notesFlow) { searchQuery, notes ->
                when {
                    searchQuery.isNotEmpty() -> notes.filter { note ->
                        note.title.contains(searchQuery, ignoreCase = true) || note.body.contains(
                            searchQuery,
                            ignoreCase = true
                        )
                    }

                    else -> notes
                }
            }.stateIn(
                scope = viewModelScope,
                initialValue = emptyList(),
                started = SharingStarted.WhileSubscribed(5_000)
            )

    fun onSearchTextChange(text: String) {
        searchQuery.value = text
    }
}

