package com.example.domain.use_case

import com.example.domain.model.NoteModel
import com.example.domain.reposiitory.NotesRepository
import kotlinx.coroutines.flow.Flow

class GetAllNotesUseCase(private val repository: NotesRepository) {
    fun execute(): Flow<List<NoteModel>> = repository.getAllNotes()
}