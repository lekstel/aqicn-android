package com.lekstel.aqi.filters.presentation.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lekstel.aqi.base.presentation.view_model.DataModelViewState
import com.lekstel.aqi.filters.domain.interactor.FlowFilterRadiusListUseCase
import com.lekstel.aqi.filters.domain.interactor.SaveFilterRadiusListUseCase
import com.lekstel.aqi.filters.domain.model.FilterRadius
import kotlinx.coroutines.cancel
import javax.inject.Inject

class FiltersViewModel @Inject constructor(
        private val saveFilterRadiusListUseCase: SaveFilterRadiusListUseCase,
        private val flowFilterRadiusListUseCase: FlowFilterRadiusListUseCase
) : ViewModel() {

    val radiusFiltersViewState = MutableLiveData<DataModelViewState<List<FilterRadius>>>()

    fun saveRadiusFilterList(list: List<FilterRadius>) {
        saveFilterRadiusListUseCase.execute(viewModelScope, list) { error -> }
    }

    fun flowRadiusFilterList() {
        flowFilterRadiusListUseCase.execute(viewModelScope) { data, error ->
            radiusFiltersViewState.postValue(DataModelViewState(false, error, data))
            if (data != null) { // stop once get data
                flowFilterRadiusListUseCase.cancel()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        flowFilterRadiusListUseCase.cancel()
        saveFilterRadiusListUseCase.cancel()
        viewModelScope.cancel()
    }
}