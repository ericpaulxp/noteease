package com.example.data.di

import com.example.data.repository.NoteRepositoryImpl
import com.example.data.repository.SettingsRepositoryImpl
import com.example.domain.reposiitory.NotesRepository
import com.example.domain.reposiitory.SettingsRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory<NotesRepository> { NoteRepositoryImpl(get()) }
    single<SettingsRepository> { SettingsRepositoryImpl(get()) }
}