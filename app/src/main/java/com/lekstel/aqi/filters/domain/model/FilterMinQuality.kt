package com.lekstel.aqi.filters.domain.model

data class FilterMinQuality(
        val id: Int,
        val minQuality: Int,
        val selected: Boolean
)