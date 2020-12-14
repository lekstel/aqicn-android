package com.lekstel.aqi.stations.data.cache

import com.google.android.gms.maps.model.LatLng
import com.lekstel.aqi.main.data.cache.AppDatabase
import com.lekstel.aqi.stations.data.cache.entity.StationDetailsEntity
import com.lekstel.aqi.stations.data.cache.entity.StationOnMapEntity
import com.lekstel.aqi.utils.bounds
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class StationsCache @Inject constructor(
        private val database: AppDatabase
) : IStationsCache {

    override fun flowStations(latLng: LatLng, radius: Int, minQuality: Int) =
            database.stationsDao().flowStations().map { list ->
                list.filter { latLng.bounds(radius).contains(it.latLng()) && it.filterByMinQuality(minQuality) }
            }

    override suspend fun saveStations(stations: List<StationOnMapEntity>) =
            database.stationsDao().insertStations(stations)

    override suspend fun getStationDetails(id: Int) =
            database.stationsDao().getStationDetails(id)

    override suspend fun saveStationsDetails(details: StationDetailsEntity) =
            database.stationsDao().saveStationsDetails(details)

    private fun StationOnMapEntity.latLng() = LatLng(lat, lon)

    private fun StationOnMapEntity.filterByMinQuality(minQuality: Int) = aqi.none { it.isDigit() } || aqi.toInt() >= minQuality
}