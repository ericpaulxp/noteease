package com.example.noteease.di

import com.example.noteease.MainViewModel
import com.example.noteease.ui.feature_edit_note.EditNoteViewModel
import com.example.noteease.ui.feature_note.ArchivedDetailsViewModel
import com.example.noteease.ui.feature_note.ArchivedViewModel
import com.example.noteease.ui.feature_note.NotesViewModel
import com.example.noteease.ui.feature_search.SearchViewModel
import com.example.noteease.ui.feature_settings.SettingsViewmodel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        NotesViewModel(get())
    }
    viewModel {
        EditNoteViewModel(get(), get(), get(), get())
    }
    viewModel {
        SearchViewModel(get())
    }
    viewModel {
        SettingsViewmodel(get())
    }
    viewModel {
        ArchivedViewModel(get())
    }
    viewModel {
        ArchivedDetailsViewModel(get(), get())
    }
    viewModel {
        MainViewModel()
    }
}