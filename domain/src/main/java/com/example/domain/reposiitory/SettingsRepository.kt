package com.example.domain.reposiitory

import com.example.domain.model.Theme
import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    val theme: Flow<Theme>

    suspend fun updateTheme(theme: Theme)
}