package com.example.data.repository

import com.example.data.model.NoteEntity
import com.example.data.source.NoteDao
import com.example.domain.model.NoteModel
import com.example.domain.reposiitory.NotesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NoteRepositoryImpl(
    private val dao: NoteDao
) : NotesRepository {
    override fun getAllNotes(): Flow<List<NoteModel>> {
        return dao.getAllNotes().map { notes ->
            notes.map { it.toNoteModel() }
        }
    }

    override fun getArchivedNotes(): Flow<List<NoteModel>> {
        return dao.getArchivedNotes().map { notes ->
            notes.map { it.toNoteModel() }
        }
    }

    override suspend fun getNoteByID(id: Int): NoteModel? {
        return dao.getNoteById(id)?.toNoteModel()
    }

    override suspend fun insertNote(note: NoteModel) {
        return dao.insertNote(
            NoteEntity(
                title = note.title,
                body = note.body,
                id = note.id,
                isArchived = note.isArchived,
                creationDate = note.creationDate,
                accessedAt = note.accessedAt
            )
        )
    }

    override suspend fun deleteNote(note: NoteModel) {
        return dao.deleteNote(
            NoteEntity(
                title = note.title,
                body = note.body,
                id = note.id,
                isArchived = note.isArchived,
                creationDate = note.creationDate,
                accessedAt = note.accessedAt
            )
        )
    }

    override fun searchNote(query: String?): Flow<List<NoteModel>> {
        return dao.searchNotes(query).map { notes ->
            notes.map { it.toNoteModel() }
        }
    }

    override suspend fun getNotesList(): List<NoteModel> {
        return dao.getNotesList().map { notes ->
            notes.toNoteModel()
        }
    }
}