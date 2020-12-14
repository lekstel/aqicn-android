package com.lekstel.aqi.stations.data.network

import com.lekstel.aqi.stations.data.model.StationDetailsDTO
import com.lekstel.aqi.stations.data.model.StationOnMapDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AqiService {

    @GET("map/bounds/")
    suspend fun getStationsInBounds(@Query("latlng") latLng: String): Result<List<StationOnMapDTO>>

    @GET("feed/@{id}/")
    suspend fun getStationDetails(@Path("id") id: Int): Result<StationDetailsDTO>
}