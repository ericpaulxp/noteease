package com.example.noteease.ui.feature_note

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.use_case.GetAllNotesUseCase
import com.example.noteease.model.NoteUiModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class NotesViewModel(
    private val getAllNotesUseCase: GetAllNotesUseCase
) : ViewModel() {

    private val _notesUiState = mutableStateOf(NotesState())
    val notesState = _notesUiState

    private var getNotesJob: Job? = null

    init {
        getNotes()
    }

    private fun getNotes() {
        getNotesJob?.cancel()
        getNotesJob = getAllNotesUseCase.execute().onEach { notes ->
            _notesUiState.value = notesState.value.copy(
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

