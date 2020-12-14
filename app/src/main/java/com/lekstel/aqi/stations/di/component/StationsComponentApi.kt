package com.lekstel.aqi.stations.di.component

import com.lekstel.aqi.stations.presentation.view.StationDetailsFragment
import com.lekstel.aqi.stations.presentation.view.StationsListFragment

interface StationsComponentApi {
    fun inject(fragment: StationsListFragment)
    fun inject(fragment: StationDetailsFragment)
}