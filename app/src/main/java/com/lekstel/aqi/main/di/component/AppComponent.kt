package com.lekstel.aqi.main.di.component

import com.lekstel.aqi.base.di.BaseComponentHandler
import com.lekstel.aqi.main.di.module.AppModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent : AppComponentApi {

    companion object : BaseComponentHandler<AppComponent, AppComponentApi>() {

        override fun init(): AppComponentApi {
            if (component == null) {
                component = DaggerAppComponent.builder().build()
            }
            return component!!
        }
    }
}