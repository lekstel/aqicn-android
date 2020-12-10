package com.lekstel.aqi.filters.presentation.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lekstel.aqi.base.presentation.view_model.DataModelViewState
import com.lekstel.aqi.filters.domain.interactor.FlowFilterMinQualityListUseCase
import com.lekstel.aqi.filters.domain.interactor.FlowFilterRadiusListUseCase
import com.lekstel.aqi.filters.domain.interactor.SaveFilterMinQualityListUseCase
import com.lekstel.aqi.filters.domain.interactor.SaveFilterRadiusListUseCase
import com.lekstel.aqi.filters.domain.model.FilterMinQuality
import com.lekstel.aqi.filters.domain.model.FilterRadius
import javax.inject.Inject

class FiltersViewModel @Inject constructor(
    private val saveFilterRadiusListUseCase: SaveFilterRadiusListUseCase,
    private val flowFilterRadiusListUseCase: FlowFilterRadiusListUseCase,
    private val saveFilterMinQualityListUseCase: SaveFilterMinQualityListUseCase,
    private val flowFilterMinQualityListUseCase: FlowFilterMinQualityListUseCase
) : ViewModel() {

    val radiusFiltersViewState by lazy {
        MutableLiveData<DataModelViewState<List<FilterRadius>>>().also { getRadiusFilterList() }
    }
    val minQualityFiltersViewState by lazy {
        MutableLiveData<DataModelViewState<List<FilterMinQuality>>>().also { getFilterMinQualityList() }
    }

    private fun getRadiusFilterList() {
        flowFilterRadiusListUseCase.execute(viewModelScope, Unit) { data, error ->
            radiusFiltersViewState.postValue(DataModelViewState(false, error, data))
            if (data != null) { // stop once get data
                flowFilterRadiusListUseCase.cancel()
            }
        }
    }

    private fun getFilterMinQualityList() {
        flowFilterMinQualityListUseCase.execute(viewModelScope, Unit) { data, error ->
            minQualityFiltersViewState.postValue(DataModelViewState(false, error, data))
            if (data != null) { // stop once get data
                flowFilterMinQualityListUseCase.cancel()
            }
        }
    }

    fun saveRadiusFilterList(list: List<FilterRadius>) {
        saveFilterRadiusListUseCase.execute(
            viewModelScope,
            SaveFilterRadiusListUseCase.Params(list)
        ) {
            radiusFiltersViewState.postValue(DataModelViewState(false, null, list))
        }
    }

    fun saveFilterMinQualityList(list: List<FilterMinQuality>) {
        saveFilterMinQualityListUseCase.execute(
            viewModelScope,
            SaveFilterMinQualityListUseCase.Params(list)
        ) {
            minQualityFiltersViewState.postValue(DataModelViewState(false, null, list))
        }
    }

    override fun onCleared() {
        super.onCleared()
        saveFilterRadiusListUseCase.cancel()
        flowFilterRadiusListUseCase.cancel()
        saveFilterMinQualityListUseCase.cancel()
        flowFilterMinQualityListUseCase.cancel()
    }
}