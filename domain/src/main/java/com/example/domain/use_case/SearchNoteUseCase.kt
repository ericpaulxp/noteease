package com.example.domain.use_case

import com.example.domain.model.NoteModel
import com.example.domain.reposiitory.NotesRepository
import kotlinx.coroutines.flow.Flow

class SearchNoteUseCase(
    private val repository: NotesRepository
) {
    val noteList = repository.getAllNotes()
    fun execute(query: String?): Flow<List<NoteModel>> {
        repository.searchNote(query)
        return noteList
    }
}