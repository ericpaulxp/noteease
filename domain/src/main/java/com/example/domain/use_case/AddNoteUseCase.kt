package com.example.domain.use_case

import com.example.domain.model.NoteModel
import com.example.domain.reposiitory.NotesRepository

class AddNoteUseCase(private val repository: NotesRepository) {
    suspend fun execute(note: NoteModel) = repository.insertNote(note = note)
}