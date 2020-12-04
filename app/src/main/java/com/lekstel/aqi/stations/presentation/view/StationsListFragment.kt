package com.lekstel.aqi.stations.presentation.view

import android.view.MenuItem
import androidx.navigation.fragment.findNavController
import com.lekstel.aqi.R
import com.lekstel.aqi.base.presentation.view.BaseFragment

class StationsListFragment : BaseFragment() {

    override val layoutResId = R.layout.fragment_stations_list
    override var menuResId: Int? = R.menu.menu

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.filters_item) {
            findNavController().navigate(R.id.stationsListFragment_to_filtersFragment)
        }
        return super.onOptionsItemSelected(item)
    }
}