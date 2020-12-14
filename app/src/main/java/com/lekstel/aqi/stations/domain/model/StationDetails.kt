package com.lekstel.aqi.stations.domain.model

data class StationDetails(
        val id: Int,
        val lat: Double?,
        val lon: Double?,
        val name: String,
        val updateTime: String,
        val pm25: Double?,
        val pm10: Double?
)