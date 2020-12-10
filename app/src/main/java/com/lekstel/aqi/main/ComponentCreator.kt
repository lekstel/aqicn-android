package com.lekstel.aqi.main

import android.content.Context
import com.lekstel.aqi.filters.di.component.FiltersComponent
import com.lekstel.aqi.main.di.component.AppComponent
import com.lekstel.aqi.stations.di.component.StationsComponent

object ComponentCreator {

    fun createAppComponent(context: Context) = AppComponent.create(context)

    fun createFiltersComponent() = FiltersComponent.create(AppComponent.get())

    fun createStationsComponent() = StationsComponent.create(AppComponent.get(), FiltersComponent.get())
}