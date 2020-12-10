package com.lekstel.aqi.utils

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.maps.android.SphericalUtil
import kotlin.math.sqrt

fun LatLng.bounds(radius: Int): LatLngBounds {
    val distanceFromCenterToCorner = radius * sqrt(2.0)
    val southwestCorner = SphericalUtil.computeOffset(this, distanceFromCenterToCorner, 225.0)
    val northeastCorner = SphericalUtil.computeOffset(this, distanceFromCenterToCorner, 45.0)
    return LatLngBounds(southwestCorner, northeastCorner)
}

fun LatLng.boundsString(radius: Int) =
    bounds(radius).run { "${southwest.latitude},${southwest.longitude},${northeast.latitude},${northeast.longitude}" }