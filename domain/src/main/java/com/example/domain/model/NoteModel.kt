package com.example.domain.model

data class NoteModel(
    val title: String = "",
    val body: String = "",
    val isArchived: Boolean = false,
    val creationDate : Long  = 0L,
    val accessedAt : Long  = 0L,
    val id: Int = 0
)