package com.lekstel.aqi.stations.di.module

import androidx.lifecycle.ViewModel
import com.lekstel.aqi.base.di.qualifier.ViewModelKey
import com.lekstel.aqi.stations.presentation.view_model.StationsListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class StationsViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(StationsListViewModel::class)
    abstract fun bindStationsListViewModel(viewModel: StationsListViewModel): ViewModel
}