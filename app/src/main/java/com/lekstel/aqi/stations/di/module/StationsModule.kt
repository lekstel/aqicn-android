package com.lekstel.aqi.stations.di.module

import com.lekstel.aqi.base.di.qualifier.LocalDataStore
import com.lekstel.aqi.base.di.qualifier.RemoteDataStore
import com.lekstel.aqi.stations.data.StationsRepository
import com.lekstel.aqi.stations.data.cache.IStationsCache
import com.lekstel.aqi.stations.data.cache.StationsCache
import com.lekstel.aqi.stations.data.source.IStationsDataStore
import com.lekstel.aqi.stations.data.source.StationsLocalDataStore
import com.lekstel.aqi.stations.data.source.StationsRemoteDataStore
import com.lekstel.aqi.stations.domain.repository.IStationsRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class StationsModule {

    @Binds
    @Singleton
    abstract fun provideStationsCache(cache: StationsCache): IStationsCache

    @Binds
    @Singleton
    @RemoteDataStore
    abstract fun bindStationsRemoteDataStore(remoteDataStore: StationsRemoteDataStore): IStationsDataStore

    @Binds
    @Singleton
    @LocalDataStore
    abstract fun bindStationsLocalDataStore(localDataStore: StationsLocalDataStore): IStationsDataStore

    @Binds
    @Singleton
    abstract fun bindStationsRepository(repository: StationsRepository): IStationsRepository
}