package com.lekstel.aqi.stations.domain.model

import com.lekstel.aqi.base.presentation.view.adapter.BaseListItem

data class StationOnMap(
    override val id: Long,
    val lat: Double,
    val lon: Double,
    val aqi: String,
    val name: String,
    val time: String
) : BaseListItem