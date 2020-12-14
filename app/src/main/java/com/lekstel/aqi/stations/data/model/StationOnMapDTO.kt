package com.lekstel.aqi.stations.data.model

data class StationOnMapDTO(
    val lat: Double,
    val lon: Double,
    val uid: Int,
    val aqi: String,
    val station: StationShortInfoDTO
) {
    data class StationShortInfoDTO(
        val name: String,
        val time: String
    )
}