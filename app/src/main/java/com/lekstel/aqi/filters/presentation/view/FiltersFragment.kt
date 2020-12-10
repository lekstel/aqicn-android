package com.lekstel.aqi.filters.presentation.view

import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import androidx.core.view.ViewCompat
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.lekstel.aqi.R
import com.lekstel.aqi.base.presentation.view.BaseBottomDialog
import com.lekstel.aqi.base.presentation.view_model.DataModelViewState
import com.lekstel.aqi.filters.di.component.FiltersComponent
import com.lekstel.aqi.filters.domain.model.FilterMinQuality
import com.lekstel.aqi.filters.domain.model.FilterRadius
import com.lekstel.aqi.filters.presentation.view_model.FiltersViewModel
import com.lekstel.aqi.filters.presentation.view_model.FiltersViewModelFactory
import kotlinx.android.synthetic.main.fragment_filters.*
import javax.inject.Inject

class FiltersFragment : BaseBottomDialog() {

    override val layoutResId = R.layout.fragment_filters

    @Inject
    lateinit var factory: FiltersViewModelFactory
    private lateinit var viewModel: FiltersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FiltersComponent.get().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, factory).get(FiltersViewModel::class.java)
        subscribeToFilterRadiusList()
        subscribeToFilterMinQualityList()
    }

    private fun subscribeToFilterRadiusList() {
        viewModel.radiusFiltersViewState.observe(this, Observer<DataModelViewState<List<FilterRadius>>> { state ->
            state.data?.also {
                viewModel.radiusFiltersViewState.removeObservers(this)
                buildRadiusFilters(it)
            }
        })
    }

    private fun subscribeToFilterMinQualityList() {
        viewModel.minQualityFiltersViewState.observe(this, Observer<DataModelViewState<List<FilterMinQuality>>> { state ->
            state.data?.also {
                viewModel.minQualityFiltersViewState.removeObservers(this)
                buildMinQualityFilters(it)
            }
        })
    }

    private fun listenRadiusRadioButtons() {
        radius_radio_group.setOnCheckedChangeListener { group, checkedId ->
            val selectedIndex = group.run { indexOfChild(findViewById(checkedId)) }
            val stateData = viewModel.radiusFiltersViewState.value?.data
            val dataToSave = stateData?.mapIndexed { i, radius -> radius.copy(selected = i == selectedIndex) }
            dataToSave?.also { viewModel.saveRadiusFilterList(it) }
        }
    }

    private fun listenMinQualityRadioButtons() {
        aqi_radio_group.setOnCheckedChangeListener { group, checkedId ->
            val selectedIndex = group.run { indexOfChild(findViewById(checkedId)) }
            val stateData = viewModel.minQualityFiltersViewState.value?.data
            val dataToSave = stateData?.mapIndexed { i, radius -> radius.copy(selected = i == selectedIndex) }
            dataToSave?.also { viewModel.saveFilterMinQualityList(it) }
        }
    }

    private fun buildRadiusFilters(list: List<FilterRadius>) {
        radius_radio_group.setOnCheckedChangeListener(null)
        radius_radio_group.removeAllViews()
        radius_tv.isVisible = list.isNotEmpty()

        list.forEach {
            RadioButton(requireContext()).apply {
                text = it.radius.toString()
                id = ViewCompat.generateViewId()
                isChecked = it.selected
                radius_radio_group.addView(this)
            }
        }

        listenRadiusRadioButtons()
    }

    private fun buildMinQualityFilters(list: List<FilterMinQuality>) {
        aqi_radio_group.setOnCheckedChangeListener(null)
        aqi_radio_group.removeAllViews()
        aqi_tv.isVisible = list.isNotEmpty()

        list.forEach {
            RadioButton(requireContext()).apply {
                text = it.minQuality.toString()
                id = ViewCompat.generateViewId()
                isChecked = it.selected
                aqi_radio_group.addView(this)
            }
        }

        listenMinQualityRadioButtons()
    }
}