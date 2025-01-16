package com.example.domain.use_case

import com.example.domain.model.NoteModel
import com.example.domain.reposiitory.NotesRepository

class GetNotesByIdUseCase(
    private val notesRepository: NotesRepository
) {
    suspend fun execute(id: Int): NoteModel? = notesRepository.getNoteByID(id)
}