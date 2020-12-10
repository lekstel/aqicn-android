package com.lekstel.aqi.stations.domain.interactor

import com.google.android.gms.maps.model.LatLng
import com.lekstel.aqi.base.domain.interactor.BaseCompleteUseCase
import com.lekstel.aqi.stations.domain.repository.IStationsRepository
import javax.inject.Inject

class ReloadStationsUseCase @Inject constructor(
    private val repository: IStationsRepository
) : BaseCompleteUseCase<ReloadStationsUseCase.Params>() {

    override suspend fun onLaunch(params: Params) {
        val stations = params.run { repository.fetchStations(latLng, radius, minQuality) }
        repository.saveStations(stations)
    }

    data class Params(val latLng: LatLng, val radius: Int, val minQuality: Int)
}