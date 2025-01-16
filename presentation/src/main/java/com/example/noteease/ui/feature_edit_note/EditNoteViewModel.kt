package com.example.noteease.ui.feature_edit_note

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.domain.use_case.AddNoteUseCase
import com.example.domain.use_case.DeleteNoteUseCase
import com.example.domain.use_case.GetNotesByIdUseCase
import com.example.noteease.model.NoteUiModel
import com.example.noteease.navigation.EditNoteRoute
import kotlinx.coroutines.launch

class EditNoteViewModel(
    private val addNoteUseCase: AddNoteUseCase,
    private val getNotesByIdUseCase: GetNotesByIdUseCase,
    private val deleteNotesUseCases: DeleteNoteUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _titleState = mutableStateOf(NoteTextFieldState())
    val titleState = _titleState

    private val _bodyState = mutableStateOf(NoteTextFieldState())
    val bodyState = _bodyState

    private val _creationDate = mutableStateOf(System.currentTimeMillis())
    val creationDate = _creationDate

    var _accessedAt = mutableStateOf(System.currentTimeMillis())
    val accessedAt = _accessedAt

    private val _isArchived = mutableStateOf(false)
    val isArchived = _isArchived

    private var currentNoteId = savedStateHandle.toRoute<EditNoteRoute>().id

    val doesHaveId = currentNoteId

    init {
        getNotesById(currentNoteId)
    }

    fun getNotesById(id: Int) {
        viewModelScope.launch {
            getNotesByIdUseCase.execute(id)?.also { note ->
                _titleState.value = NoteTextFieldState(
                    text = note.title
                )
                _bodyState.value = NoteTextFieldState(
                    text = note.body
                )
                _creationDate.value = note.creationDate
                _accessedAt.value = note.accessedAt
            }
        }
    }

    fun SaveNote() {
        viewModelScope.launch {
            addNoteUseCase.execute(
                NoteUiModel(
                    title = _titleState.value.text.trim(),
                    body = _bodyState.value.text.trim(),
                    id = currentNoteId,
                    isArchived = _isArchived.value,
                    creationDate = _creationDate.value,
                    accessedAt = System.currentTimeMillis()
                ).toNoteModel()
            )
        }
    }

    fun onEvent(event: EditNoteEvent) {
        when (event) {
            is EditNoteEvent.EnteredBody -> {
                _bodyState.value = _bodyState.value.copy(
                    text = event.value
                )
            }

            is EditNoteEvent.EnteredTitle -> {
                _titleState.value = _titleState.value.copy(
                    text = event.value
                )
            }

            EditNoteEvent.SaveNote -> {
                SaveNote()
            }

            is EditNoteEvent.ArchivedNote -> {
                _isArchived.value = event.isArchived
                SaveNote()
            }

            EditNoteEvent.DeleteNote -> {
                viewModelScope.launch {
                    deleteNotesUseCases.execute(
                        NoteUiModel(
                            title = titleState.value.text,
                            body = bodyState.value.text,
                            id = currentNoteId,
                            isArchived = isArchived.value,
                            creationDate = creationDate.value,
                            accessedAt = accessedAt.value
                        ).toNoteModel()
                    )
                }
            }
        }
    }
}

data class NoteTextFieldState(
    val text: String = "",
)

sealed class EditNoteEvent {
    data class EnteredTitle(val value: String) : EditNoteEvent()
    data class EnteredBody(val value: String) : EditNoteEvent()
    data class ArchivedNote(val isArchived: Boolean) : EditNoteEvent()
    data object DeleteNote : EditNoteEvent()
    object SaveNote : EditNoteEvent()
}