package com.example.noteease.ui.feature_settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Theme
import com.example.domain.use_case.GetThemeUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SettingsViewmodel(
    private val getThemeUseCase: GetThemeUseCase,
) : ViewModel() {
    val theme = getThemeUseCase.theme
        .stateIn(viewModelScope, SharingStarted.Lazily, Theme.System)

    fun updateTheme(theme: Theme) = viewModelScope.launch{
        getThemeUseCase.execute(theme)
    }
}