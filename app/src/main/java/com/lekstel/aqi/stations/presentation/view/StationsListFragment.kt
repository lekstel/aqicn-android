package com.lekstel.aqi.stations.presentation.view

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.lekstel.aqi.R
import com.lekstel.aqi.base.presentation.view.BaseFragment
import com.lekstel.aqi.base.presentation.view.adapter.DelegationAdapter
import com.lekstel.aqi.base.presentation.view_model.DataModelViewState
import com.lekstel.aqi.filters.domain.model.FilterMinQuality
import com.lekstel.aqi.filters.domain.model.FilterRadius
import com.lekstel.aqi.main.ComponentCreator
import com.lekstel.aqi.stations.di.component.StationsComponent
import com.lekstel.aqi.stations.domain.model.StationOnMap
import com.lekstel.aqi.stations.presentation.view.StationDetailsFragment.Companion.ID_KEY
import com.lekstel.aqi.stations.presentation.view.adapter.StationsAdapter
import com.lekstel.aqi.stations.presentation.view.adapter.stationAdapterDelegate
import com.lekstel.aqi.stations.presentation.view_model.StationsListViewModel
import com.lekstel.aqi.stations.presentation.view_model.StationsListViewModelFactory
import kotlinx.android.synthetic.main.fragment_stations_list.*
import javax.inject.Inject

class StationsListFragment : BaseFragment() {

    override val layoutResId = R.layout.fragment_stations_list
    override var menuResId: Int? = R.menu.menu

    @Inject
    lateinit var factory: StationsListViewModelFactory
    private lateinit var viewModel: StationsListViewModel

    private lateinit var delegationAdapter: DelegationAdapter

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ComponentCreator.createFiltersComponent()
        ComponentCreator.createStationsComponent()
        StationsComponent.get().inject(this)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, factory).get(StationsListViewModel::class.java)

        delegationAdapter = StationsAdapter()
        delegationAdapter.addDelegate(stationAdapterDelegate {
            findNavController().navigate(R.id.stationsListFragment_to_stationDetailsFragment,
                    Bundle().apply { putInt(ID_KEY, it.id) })
        })
        rv_stations.apply {
            addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
            layoutManager = LinearLayoutManager(requireContext())
            adapter = delegationAdapter
        }

        subscribeToStations()
        subscribeToReloadState()
        subscribeToFilterRadiusList()
        subscribeToFilterMinQualityList()

        handlePermissions()
    }

    private fun subscribeToReloadState() {
        viewModel.reloadViewState.observe(this, Observer<DataModelViewState<Unit>> { state ->
            tv_sync.isVisible = state.isLoading
        })
    }

    private fun subscribeToStations() {
        viewModel.stationsViewState.observe(this, Observer<DataModelViewState<List<StationOnMap>>> { state ->
            state.data?.also {
                delegationAdapter.items = it
            }
        })
    }

    private fun subscribeToFilterRadiusList() {
        viewModel.radiusFiltersViewState.observe(this, Observer<DataModelViewState<List<FilterRadius>>> { state ->
            state.data?.also {
                viewModel.flowAndReload()
            }
        })
    }

    private fun subscribeToFilterMinQualityList() {
        viewModel.minQualityFiltersViewState.observe(this, Observer<DataModelViewState<List<FilterMinQuality>>> { state ->
            state.data?.also {
                viewModel.flowAndReload()
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.filters_item) {
            if (permissionGranted()) {
                findNavController().navigate(R.id.stationsListFragment_to_filtersFragment)
            } else {
                handlePermissions()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    @SuppressLint("MissingPermission")
    private fun handleLocation() {
        fusedLocationClient.lastLocation.addOnSuccessListener { viewModel.location = it }
    }

    private fun permissionGranted() = ContextCompat.checkSelfPermission(requireContext(),
            Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED

    private fun handlePermissions() {
        when {
            permissionGranted() -> handleLocation()
            else -> requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), PERMISSION_CODE)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            PERMISSION_CODE -> {
                if (grantResults.isNotEmpty() &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(requireContext(), R.string.toast_permission_granted, Toast.LENGTH_SHORT).show()
                    handleLocation()
                } else {
                    Toast.makeText(requireContext(), R.string.toast_permission_denied, Toast.LENGTH_SHORT).show()
                }
                return
            }
        }
    }

    companion object {
        val PERMISSION_CODE = 101
    }
}