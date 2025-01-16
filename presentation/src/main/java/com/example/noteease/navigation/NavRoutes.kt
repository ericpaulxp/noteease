package com.example.noteease.navigation

import kotlinx.serialization.Serializable

@Serializable
data object HomeRoute

@Serializable
data class EditNoteRoute(val id: Int)

@Serializable
data object SearchRoute

@Serializable
data object ArchivedRoute

@Serializable
data class ArchivedDetailsRoute(val id: Int)

@Serializable
data object SettingsRoute

@Serializable
data object WhatsNewRoute

@Serializable
data object AboutRoute

