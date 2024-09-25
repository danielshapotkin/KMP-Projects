package com.example.dagger

import android.app.Application

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        val appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this, 123))
            .build()
    }


}