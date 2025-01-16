package com.example.domain.di

import com.example.domain.use_case.AddNoteUseCase
import com.example.domain.use_case.DeleteNoteUseCase
import com.example.domain.use_case.GetAllNotesUseCase
import com.example.domain.use_case.GetArchivedNotesUseCase
import com.example.domain.use_case.GetNotesByIdUseCase
import com.example.domain.use_case.GetThemeUseCase
import com.example.domain.use_case.SearchNoteUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetAllNotesUseCase(get()) }
    factory { GetArchivedNotesUseCase(get()) }
    factory { AddNoteUseCase(get()) }
    factory { DeleteNoteUseCase(get()) }
    factory { GetNotesByIdUseCase(get()) }
    factory { SearchNoteUseCase(get()) }
    factory { GetThemeUseCase(get()) }
}