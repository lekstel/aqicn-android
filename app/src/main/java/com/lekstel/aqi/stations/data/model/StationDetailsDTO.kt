package com.lekstel.aqi.stations.data.model

data class StationDetailsDTO(
        val idx: Int,
        val city: CityDTO? = null,
        val iaqi: IaqiDTO? = null,
        val time: TimeDTO? = null
) {
    data class CityDTO(
            val geo: List<Double?>?,
            val name: String?
    )

    data class IaqiDTO(
            val pm10: Pm10DTO? = null,
            val pm25: Pm25DTO? = null
    ) {
        data class Pm25DTO(val v: Double? = null)

        data class Pm10DTO(val v: Double? = null)
    }

    data class TimeDTO(
            val iso: String? = null
    )
}