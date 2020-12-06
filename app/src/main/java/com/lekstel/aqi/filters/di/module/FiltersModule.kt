package com.lekstel.aqi.filters.di.module

import com.lekstel.aqi.filters.data.FiltersRepository
import com.lekstel.aqi.filters.data.cache.FiltersCache
import com.lekstel.aqi.filters.data.cache.IFiltersCache
import com.lekstel.aqi.filters.data.source.FiltersLocalDataStore
import com.lekstel.aqi.filters.data.source.IFiltersDataSource
import com.lekstel.aqi.filters.domain.repository.IFiltersRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class FiltersModule {

    @Binds
    @Singleton
    abstract fun provideFiltersCache(cache: FiltersCache): IFiltersCache

    @Binds
    @Singleton
    abstract fun provideFiltersLocalDataStore(localDataStore: FiltersLocalDataStore): IFiltersDataSource

    @Binds
    @Singleton
    abstract fun provideFiltersRepository(repository: FiltersRepository): IFiltersRepository
}