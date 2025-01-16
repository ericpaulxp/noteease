package com.example.noteease.ui.feature_note

import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.domain.use_case.GetNotesByIdUseCase
import com.example.noteease.navigation.EditNoteRoute
import kotlinx.coroutines.launch

class ArchivedDetailsViewModel(
    private val getNotesByIdUseCase: GetNotesByIdUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _titleState = mutableStateOf("")
    val titleState = _titleState

    private val _bodyState = mutableStateOf("")
    val bodyState = _bodyState

    private val _dateState = mutableLongStateOf(0L)
    val dateState = _dateState

    private var currentNoteId = savedStateHandle.toRoute<EditNoteRoute>().id

    init {
        getNotesById(currentNoteId)
    }

    fun getNotesById(id: Int) {
        viewModelScope.launch {
            getNotesByIdUseCase.execute(id)?.also { note ->
                _titleState.value = note.title
                _bodyState.value = note.body
                _dateState.value = note.accessedAt
            }
        }
    }
}