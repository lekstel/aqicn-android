package com.lekstel.aqi.main.di.component

import com.lekstel.aqi.main.data.cache.AppDatabase
import com.lekstel.aqi.stations.data.network.AqiService

interface AppComponentApi {
    fun appDatabase(): AppDatabase
    fun api(): AqiService
}