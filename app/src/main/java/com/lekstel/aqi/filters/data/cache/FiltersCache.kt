package com.lekstel.aqi.filters.data.cache

import com.lekstel.aqi.filters.data.cache.entity.FilterRadiusEntity
import com.lekstel.aqi.main.data.cache.AppDatabase
import javax.inject.Inject

class FiltersCache @Inject constructor(
        private val database: AppDatabase
) : IFiltersCache {

    override fun save(list: List<FilterRadiusEntity>) = database.filtersDao().insert(list)

    override fun flow() = database.filtersDao().flow()
}