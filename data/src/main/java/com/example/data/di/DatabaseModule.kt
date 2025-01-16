package com.example.data.di

import android.app.Application
import androidx.room.Room
import com.example.data.source.NoteDao
import com.example.data.source.NoteDatabase
import com.example.data.source.NoteDatabase.Companion.DATABASE_NAME
import org.koin.dsl.module

fun providesDatabase(application: Application): NoteDatabase =
    Room.databaseBuilder(
        application,
        NoteDatabase::class.java,
        DATABASE_NAME
    ).build()

fun providesDao(noteDatabase: NoteDatabase): NoteDao =
    noteDatabase.noteDao()

val databaseModule = module {
    single { providesDatabase(get()) }
    single { providesDao(get()) }
}