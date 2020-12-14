package com.lekstel.aqi.stations.presentation.view

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.lekstel.aqi.R
import com.lekstel.aqi.base.presentation.view.BaseFragment
import com.lekstel.aqi.base.presentation.view_model.DataModelViewState
import com.lekstel.aqi.stations.di.component.StationsComponent
import com.lekstel.aqi.stations.domain.model.StationDetails
import com.lekstel.aqi.stations.presentation.view_model.StationDetailsViewModel
import com.lekstel.aqi.stations.presentation.view_model.StationsListViewModelFactory
import kotlinx.android.synthetic.main.fragment_station_details.*
import javax.inject.Inject

class StationDetailsFragment : BaseFragment() {

    override val layoutResId = R.layout.fragment_station_details

    @Inject
    lateinit var factory: StationsListViewModelFactory
    private lateinit var viewModel: StationDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StationsComponent.get().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, factory).get(StationDetailsViewModel::class.java)
        arguments?.getInt(ID_KEY)?.let { viewModel.id = it }
        subscribeToDetailsState()
    }

    private fun subscribeToDetailsState() {
        viewModel.detailsViewState.observe(this, Observer<DataModelViewState<StationDetails>> { state ->
            state.data?.apply {
                tv_name.text = getString(R.string.details_name, name)
                tv_latlng.text = getString(R.string.details_coordinates,
                        if (lat == null || lon == null) "" else "$lat, $lon")
                tv_last_update.text = getString(R.string.details_time, updateTime)
                tv_pm25.text = getString(R.string.details_pm25, pm25?.toString() ?: "")
                tv_pm10.text = getString(R.string.details_pm10, pm10?.toString() ?: "")
            }
        })
    }

    companion object {
        val ID_KEY = "id_key"
    }
}