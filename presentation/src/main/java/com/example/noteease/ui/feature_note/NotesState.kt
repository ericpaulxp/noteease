package com.example.noteease.ui.feature_note

import com.example.noteease.model.NoteUiModel

data class NotesState(
    val notes: List<NoteUiModel> = emptyList()
)
