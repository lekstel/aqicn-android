package com.lekstel.aqi.stations.presentation.view

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ComponentCreator.createFiltersComponent()
        ComponentCreator.createStationsComponent()
        StationsComponent.get().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, factory).get(StationsListViewModel::class.java)

        delegationAdapter = StationsAdapter()
        delegationAdapter.addDelegate(stationAdapterDelegate {
            findNavController().navigate(R.id.stationsListFragment_to_stationDetailsFragment,
                    Bundle().apply { putInt(ID_KEY, it.id) })
        })
        rv_stations.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
        rv_stations.layoutManager = LinearLayoutManager(requireContext())
        rv_stations.adapter = delegationAdapter

        subscribeToStations()
        subscribeToReloadState()
        subscribeToFilterRadiusList()
        subscribeToFilterMinQualityList()
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
            findNavController().navigate(R.id.stationsListFragment_to_filtersFragment)
        }
        return super.onOptionsItemSelected(item)
    }
}