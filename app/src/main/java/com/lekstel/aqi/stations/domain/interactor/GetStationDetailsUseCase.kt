package com.lekstel.aqi.stations.domain.interactor

import com.lekstel.aqi.base.domain.interactor.BaseGetUseCase
import com.lekstel.aqi.stations.domain.model.StationDetails
import com.lekstel.aqi.stations.domain.repository.IStationsRepository
import javax.inject.Inject

class GetStationDetailsUseCase @Inject constructor(
        private val repository: IStationsRepository
) : BaseGetUseCase<StationDetails, GetStationDetailsUseCase.Params>() {

    override suspend fun onLaunch(params: Params) = repository.getStationDetails(params.id)

    data class Params(val id: Int)
}