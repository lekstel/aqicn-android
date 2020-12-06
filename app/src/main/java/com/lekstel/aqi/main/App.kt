package com.lekstel.aqi.main

import android.app.Application
import com.facebook.stetho.Stetho

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        ComponentCreator.createAppComponent(applicationContext)
        Stetho.initializeWithDefaults(this);
    }
}