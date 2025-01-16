package com.example.noteease.model

import android.os.Parcelable
import com.example.domain.model.NoteModel
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class NoteUiModel(
    val title: String = "",
    val body: String = "",
    val id: Int = 0,
    val isArchived: Boolean = false,
    val creationDate: Long = 0L,
    val accessedAt: Long = 0L,
) : Parcelable {

    fun toNoteModel() = NoteModel(
        title = title,
        body = body,
        id = id,
        isArchived = isArchived,
        creationDate = creationDate,
        accessedAt = accessedAt
    )
}