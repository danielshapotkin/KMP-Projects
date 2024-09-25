package com.example.dagger

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val context: Context, private val id: Int) {

    @Provides
    @Singleton
    fun provideContext(): Context {
        return context
    }

    @Provides
    @Singleton
    fun provideId(): Int {
        return id
    }

    @Provides
    @Singleton
    fun provideMyService(context: Context, id: Int): MyService {
        return MyService(context, id)
    }
}