package com.lekstel.aqi.main.di.component

import android.content.Context
import com.lekstel.aqi.base.di.BaseComponentHandler
import com.lekstel.aqi.main.di.module.AppModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent : AppComponentApi {

    companion object : BaseComponentHandler<AppComponent, AppComponentApi>() {

        fun create(context: Context): AppComponentApi {
            if (component == null) {
                component = DaggerAppComponent
                        .builder()
                        .appModule(AppModule(context))
                        .build()
            }
            return component!!
        }
    }
}