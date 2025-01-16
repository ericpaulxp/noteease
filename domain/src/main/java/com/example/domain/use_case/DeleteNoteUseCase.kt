package com.example.domain.use_case

import com.example.domain.model.NoteModel
import com.example.domain.reposiitory.NotesRepository

class DeleteNoteUseCase(
    private val repository: NotesRepository
) {
    suspend fun execute(note: NoteModel) = repository.deleteNote(note = note)
}