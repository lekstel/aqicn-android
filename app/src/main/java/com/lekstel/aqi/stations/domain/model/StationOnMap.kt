package com.lekstel.aqi.stations.domain.model

data class StationOnMap(
    val uid: Long,
    val lat: Double,
    val lon: Double,
    val aqi: String,
    val name: String,
    val time: String
)