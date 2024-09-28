package com.jkl.nitrixtesttask.main

import android.app.Application
import com.jkl.nitrixtesttask.list.di.listModule
import com.jkl.nitrixtesttask.player.di.videoPlayerModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listModule, videoPlayerModule)
        }
    }
}