package com.lekstel.aqi.filters.presentation.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lekstel.aqi.filters.domain.interactor.FlowFiltersUseCase
import com.lekstel.aqi.filters.domain.interactor.SaveFiltersUseCase
import com.lekstel.aqi.filters.domain.model.FilterRadius
import javax.inject.Inject

class FiltersViewModel @Inject constructor(
        private val saveFiltersUseCase: SaveFiltersUseCase,
        private val flowFiltersUseCase: FlowFiltersUseCase
) : ViewModel() {

    fun save(filters: List<FilterRadius>) {
        saveFiltersUseCase.execute(filters, viewModelScope)
    }

    fun flow() {
        flowFiltersUseCase.execute(viewModelScope) { data, error -> }
    }
}