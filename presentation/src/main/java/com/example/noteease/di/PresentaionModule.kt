package com.example.noteease.di

import org.koin.dsl.module

val presentationModule = module {
    includes(viewModelModule)
}