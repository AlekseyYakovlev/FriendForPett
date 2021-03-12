package ru.friendforpet

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        initLogger()
    }

    private fun initLogger() {
            Timber.plant(Timber.DebugTree())
    }

    companion object {
        internal lateinit var INSTANCE: App
            private set
    }
}