package com.example.noteease

import android.app.Application
import com.example.data.di.dataModule
import com.example.domain.di.domainModule
import com.example.noteease.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class NoteEaseApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@NoteEaseApp)
            modules(
                listOf(
                    presentationModule,
                    dataModule,
                    domainModule
                )
            )
        }
    }
}