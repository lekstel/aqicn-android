package com.lekstel.aqi.stations.domain.interactor

import com.google.android.gms.maps.model.LatLng
import com.lekstel.aqi.base.domain.interactor.BaseFlowUseCase
import com.lekstel.aqi.stations.domain.model.StationOnMap
import com.lekstel.aqi.stations.domain.repository.IStationsRepository
import javax.inject.Inject

class FlowStationsUseCase @Inject constructor(
        private val repository: IStationsRepository
): BaseFlowUseCase<List<StationOnMap>, FlowStationsUseCase.Params>() {

    override suspend fun getFlow(params: Params) = params.run {
        repository.flowStations(latLng, radius, minQuality)
    }

    data class Params(val latLng: LatLng, val radius: Int, val minQuality: Int)
}