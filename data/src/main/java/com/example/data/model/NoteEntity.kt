package com.example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.model.NoteModel

@Entity(tableName = "notes")
data class NoteEntity(
    val title: String = "",
    val body: String = "",
    val isArchived: Boolean = false,
    val creationDate: Long = 0L,
    val accessedAt: Long = 0L,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
) {
    fun toNoteModel() = NoteModel(
        title = title,
        body = body,
        id = id,
        isArchived = isArchived,
        creationDate = creationDate,
        accessedAt = accessedAt
    )
}