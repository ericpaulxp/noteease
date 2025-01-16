package com.example.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.domain.model.Theme
import com.example.domain.reposiitory.SettingsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class SettingsRepositoryImpl(
    private val storage: DataStore<Preferences>,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : SettingsRepository {

    override val theme: Flow<Theme> = storage.data
        .map { preferences -> preferences[SettingsKey.Theme] }
        .map { if (it != null) Theme.valueOf(it) else Theme.System }
        .flowOn(dispatcher)

    override suspend fun updateTheme(theme: Theme) {
        withContext(dispatcher){
            storage.edit { preferences -> preferences[SettingsKey.Theme] = theme.toString()}
        }
    }
}

object SettingsKey {
    val Theme = stringPreferencesKey("Theme")
}