package com.lekstel.aqi.filters.data.cache

import com.lekstel.aqi.filters.data.cache.entity.FilterMinQualityEntity
import com.lekstel.aqi.filters.data.cache.entity.FilterRadiusEntity
import com.lekstel.aqi.main.data.cache.AppDatabase
import javax.inject.Inject

class FiltersCache @Inject constructor(
        private val database: AppDatabase
) : IFiltersCache {

    override fun saveFilterRadiusList(list: List<FilterRadiusEntity>) = database.filtersDao().insertFilterRadiusList(list)

    override fun flowFilterRadiusList() = database.filtersDao().flowFilterRadiusList()

    override fun saveFilterMinQualityList(list: List<FilterMinQualityEntity>) = database.filtersDao().insertFilterMinQualityList(list)

    override fun flowFilterMinQualityList() = database.filtersDao().flowFilterMinQualityList()
}