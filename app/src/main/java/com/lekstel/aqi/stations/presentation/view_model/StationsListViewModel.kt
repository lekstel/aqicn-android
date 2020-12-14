package com.lekstel.aqi.stations.presentation.view_model

import android.location.Location
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import com.lekstel.aqi.base.presentation.view_model.DataModelViewState
import com.lekstel.aqi.filters.domain.interactor.FlowFilterMinQualityListUseCase
import com.lekstel.aqi.filters.domain.interactor.FlowFilterRadiusListUseCase
import com.lekstel.aqi.filters.domain.model.FilterMinQuality
import com.lekstel.aqi.filters.domain.model.FilterRadius
import com.lekstel.aqi.stations.domain.interactor.FlowStationsUseCase
import com.lekstel.aqi.stations.domain.interactor.ReloadStationsUseCase
import com.lekstel.aqi.stations.domain.model.StationOnMap
import javax.inject.Inject

class StationsListViewModel @Inject constructor(
    private val reloadStationsUseCase: ReloadStationsUseCase,
    private val flowStationsUseCase: FlowStationsUseCase,
    private val flowFilterRadiusListUseCase: FlowFilterRadiusListUseCase,
    private val flowFilterMinQualityListUseCase: FlowFilterMinQualityListUseCase
) : ViewModel() {

    var location: Location? = null
        set(value) {
            field = value
            flowAndReload()
        }

    val radiusFiltersViewState by lazy {
        MutableLiveData<DataModelViewState<List<FilterRadius>>>().also { flowRadiusFilterList() }
    }
    val minQualityFiltersViewState by lazy {
        MutableLiveData<DataModelViewState<List<FilterMinQuality>>>().also { flowFilterMinQualityList() }
    }

    val stationsViewState = MutableLiveData<DataModelViewState<List<StationOnMap>>>()
    val reloadViewState = MutableLiveData<DataModelViewState<Unit>>()

    private fun flowRadiusFilterList() {
        flowFilterRadiusListUseCase.execute(viewModelScope, Unit) { data, error ->
            radiusFiltersViewState.postValue(DataModelViewState(false, error, data))
        }
    }

    private fun flowFilterMinQualityList() {
        flowFilterMinQualityListUseCase.execute(viewModelScope, Unit) { data, error ->
            minQualityFiltersViewState.postValue(DataModelViewState(false, error, data))
        }
    }

    fun flowAndReload() {
        filterRadius()?.let { radius ->
            filterMinQuality()?.let { minQuality ->
                location?.let { location ->
                    reloadStationsUseCase.cancel()
                    flowStationsUseCase.cancel()
                    flowStationsUseCase.execute(
                        viewModelScope,
                        FlowStationsUseCase.Params(location.run { LatLng(latitude, longitude) }, radius * 1000, minQuality)
                    ) { next, error ->
                        stationsViewState.postValue(DataModelViewState(false, error, next))
                    }
                    reloadViewState.postValue(DataModelViewState(true, null, Unit))
                    reloadStationsUseCase.execute(
                        viewModelScope,
                        ReloadStationsUseCase.Params(location.run { LatLng(latitude, longitude) }, radius * 1000, minQuality)
                    ) { error ->
                        reloadViewState.postValue(DataModelViewState(false, error, Unit))
                    }
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        reloadStationsUseCase.cancel()
        flowStationsUseCase.cancel()
        flowFilterRadiusListUseCase.cancel()
        flowFilterMinQualityListUseCase.cancel()
    }

    private fun filterRadiusList() = radiusFiltersViewState.value?.data

    private fun filterMinQualityList() = minQualityFiltersViewState.value?.data

    private fun filterRadius() = filterRadiusList()?.find { it.selected }?.radius

    private fun filterMinQuality() = filterMinQualityList()?.find { it.selected }?.minQuality
}