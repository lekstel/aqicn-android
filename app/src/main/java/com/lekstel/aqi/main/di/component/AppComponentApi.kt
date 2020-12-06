package com.lekstel.aqi.main.di.component

import com.lekstel.aqi.main.data.cache.AppDatabase

interface AppComponentApi {
    fun appDatabase(): AppDatabase
}