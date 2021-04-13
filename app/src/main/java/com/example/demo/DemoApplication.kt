package com.example.demo

import android.app.Application
import com.example.demo.di.CuteModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class DemoApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@DemoApplication)
            modules(CuteModule.module)
        }
    }
}