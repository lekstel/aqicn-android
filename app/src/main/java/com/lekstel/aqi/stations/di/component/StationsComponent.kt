package com.lekstel.aqi.stations.di.component

import com.lekstel.aqi.base.di.BaseComponentHandler
import com.lekstel.aqi.filters.di.component.FiltersComponentApi
import com.lekstel.aqi.main.di.component.AppComponentApi
import com.lekstel.aqi.stations.di.module.StationsModule
import com.lekstel.aqi.stations.di.module.StationsViewModelModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(dependencies = [AppComponentApi::class, FiltersComponentApi::class], modules = [StationsModule::class, StationsViewModelModule::class])
interface StationsComponent : StationsComponentApi {

    companion object : BaseComponentHandler<StationsComponent, StationsComponentApi>() {

        fun create(appComponentApi: AppComponentApi, filtersComponentApi: FiltersComponentApi): StationsComponentApi {
            if (component == null) {
                component = DaggerStationsComponent
                        .builder()
                        .appComponentApi(appComponentApi)
                        .filtersComponentApi(filtersComponentApi)
                        .build()
            }
            return component!!
        }
    }
}