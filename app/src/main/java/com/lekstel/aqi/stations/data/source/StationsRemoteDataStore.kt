package com.lekstel.aqi.stations.data.source

import com.google.android.gms.maps.model.LatLng
import com.lekstel.aqi.stations.data.model.StationDetailsDTO
import com.lekstel.aqi.stations.data.model.StationOnMapDTO
import com.lekstel.aqi.stations.data.network.AqiService
import com.lekstel.aqi.utils.boundsString
import javax.inject.Inject

class StationsRemoteDataStore @Inject constructor(
    private val api: AqiService
) : IStationsDataStore {

    override suspend fun fetchStations(latLng: LatLng, radius: Int, minQuality: Int) =
        api.getStationsInBounds(latLng.boundsString(radius)).data

    override fun flowStations(latLng: LatLng, radius: Int, minQuality: Int) = error("not supported")

    override suspend fun saveStations(stations: List<StationOnMapDTO>) = error("not supported")

    override suspend fun getStationDetails(id: Int) = api.getStationDetails(id).data

    override suspend fun saveStationsDetails(details: StationDetailsDTO) = error("not supported")
}