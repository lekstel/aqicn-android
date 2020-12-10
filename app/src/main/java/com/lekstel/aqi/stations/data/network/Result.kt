package com.lekstel.aqi.stations.data.network

data class Result<T>(
    val status: String,
    val data: T
)