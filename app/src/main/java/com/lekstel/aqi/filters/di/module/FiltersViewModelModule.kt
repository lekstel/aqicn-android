package com.lekstel.aqi.filters.di.module

import androidx.lifecycle.ViewModel
import com.lekstel.aqi.base.di.qualifier.ViewModelKey
import com.lekstel.aqi.filters.presentation.view_model.FiltersViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class FiltersViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(FiltersViewModel::class)
    abstract fun bindFiltersViewModel(viewModel: FiltersViewModel): ViewModel
}