package edu.anbu.libraryofalexandria

import android.app.Application
import android.content.Context

class LibraryOfAlexandria : Application() {
    override fun onCreate() {
        super.onCreate()
        appContext = this
    }

    companion object {
        lateinit var appContext: Context
            private set
    }
}