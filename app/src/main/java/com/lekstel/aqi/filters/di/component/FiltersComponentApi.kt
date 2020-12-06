package com.lekstel.aqi.filters.di.component

import com.lekstel.aqi.filters.presentation.view_model.FiltersViewModelFactory

interface FiltersComponentApi {
    fun factory(): FiltersViewModelFactory
}