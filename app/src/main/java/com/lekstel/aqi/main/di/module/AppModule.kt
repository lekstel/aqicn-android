package com.lekstel.aqi.main.di.module

import android.content.Context
import androidx.room.Room
import com.lekstel.aqi.main.data.cache.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val context: Context) {

    @Provides
    @Singleton
    fun provideAppDatabase(): AppDatabase =
            Room.databaseBuilder(context, AppDatabase::class.java, AppDatabase.DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build()
}