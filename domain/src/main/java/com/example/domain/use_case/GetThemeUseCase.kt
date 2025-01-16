package com.example.domain.use_case

import com.example.domain.model.Theme
import com.example.domain.reposiitory.SettingsRepository

class GetThemeUseCase(
    private val repository: SettingsRepository
) {
    val theme = repository.theme

    suspend fun execute(theme: Theme) = repository.updateTheme(theme)
}