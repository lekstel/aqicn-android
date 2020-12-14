package com.lekstel.aqi.stations.presentation.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lekstel.aqi.base.presentation.view_model.DataModelViewState
import com.lekstel.aqi.stations.domain.interactor.GetStationDetailsUseCase
import com.lekstel.aqi.stations.domain.model.StationDetails
import javax.inject.Inject

class StationDetailsViewModel @Inject constructor(
        private val getStationDetailsUseCase: GetStationDetailsUseCase
) : ViewModel() {

    var id: Int? = null

    val detailsViewState by lazy { MutableLiveData<DataModelViewState<StationDetails>>().also { getStationDetails(id!!) } }

    private fun getStationDetails(id: Int) {
        getStationDetailsUseCase.execute(viewModelScope, GetStationDetailsUseCase.Params(id)) { next, error ->
            detailsViewState.postValue(DataModelViewState(false, error, next))
        }
    }

    override fun onCleared() {
        super.onCleared()
        getStationDetailsUseCase.cancel()
    }
}