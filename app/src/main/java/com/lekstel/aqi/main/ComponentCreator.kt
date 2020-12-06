package com.lekstel.aqi.main

import android.content.Context
import com.lekstel.aqi.filters.di.component.FiltersComponent
import com.lekstel.aqi.main.di.component.AppComponent

object ComponentCreator {

    fun createAppComponent(context: Context) = AppComponent.create(context)

    fun createFiltersComponent() = FiltersComponent.create(AppComponent.get())
}