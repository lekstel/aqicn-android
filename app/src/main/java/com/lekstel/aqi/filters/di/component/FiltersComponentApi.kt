package com.lekstel.aqi.filters.di.component

import com.lekstel.aqi.filters.domain.repository.IFiltersRepository
import com.lekstel.aqi.filters.presentation.view.FiltersFragment

interface FiltersComponentApi {
    fun inject(fragment: FiltersFragment)
    fun repository(): IFiltersRepository
}