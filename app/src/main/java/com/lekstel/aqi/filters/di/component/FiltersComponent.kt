package com.lekstel.aqi.filters.di.component

import com.lekstel.aqi.base.di.BaseComponentHandler
import com.lekstel.aqi.filters.di.module.FiltersModule
import com.lekstel.aqi.filters.di.module.FiltersViewModelModule
import com.lekstel.aqi.main.di.component.AppComponentApi
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(dependencies = [AppComponentApi::class], modules = [FiltersModule::class, FiltersViewModelModule::class])
interface FiltersComponent : FiltersComponentApi {

    companion object : BaseComponentHandler<FiltersComponent, FiltersComponentApi>() {

        fun create(appComponentApi: AppComponentApi): FiltersComponentApi {
            if (component == null) {
                component = DaggerFiltersComponent
                        .builder()
                        .appComponentApi(appComponentApi)
                        .build()
            }
            return component!!
        }
    }
}