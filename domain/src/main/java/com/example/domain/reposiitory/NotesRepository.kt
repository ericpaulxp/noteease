package com.example.domain.reposiitory

import com.example.domain.model.NoteModel
import kotlinx.coroutines.flow.Flow

interface NotesRepository {
    fun getAllNotes(): Flow<List<NoteModel>>
    fun getArchivedNotes(): Flow<List<NoteModel>>
    suspend fun getNoteByID(id: Int): NoteModel?
    suspend fun insertNote(note: NoteModel)
    suspend fun deleteNote(note: NoteModel)
    fun searchNote(query: String?): Flow<List<NoteModel>>
    suspend fun getNotesList(): List<NoteModel>
}