package com.example.noteease.ui.feature_note

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.use_case.GetArchivedNotesUseCase
import com.example.noteease.model.NoteUiModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class ArchivedViewModel(
    private val getArchivedNotesUseCase: GetArchivedNotesUseCase
) : ViewModel() {

    private val _uiState = mutableStateOf(NotesState())
    val uiState = _uiState

    private var getNotesJob: Job? = null

    init {
        getArchivedNotes()
    }

    private fun getArchivedNotes() {
        getNotesJob?.cancel()
        getNotesJob = getArchivedNotesUseCase.execute().onEach { notes ->
            _uiState.value = uiState.value.copy(
                notes = notes.map {
                    NoteUiModel(
                        title = it.title,
                        body = it.body,
                        id = it.id,
                        isArchived = it.isArchived,
                        creationDate = it.creationDate,
                        accessedAt = it.accessedAt
                    )
                }
            )
        }.launchIn(viewModelScope)
    }
}


