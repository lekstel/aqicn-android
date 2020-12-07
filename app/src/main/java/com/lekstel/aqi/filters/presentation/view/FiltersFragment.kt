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
import com.lekstel.aqi.filters.domain.model.FilterRadius
import com.lekstel.aqi.filters.presentation.view_model.FiltersViewModel
import com.lekstel.aqi.filters.presentation.view_model.FiltersViewModelFactory
import com.lekstel.aqi.main.ComponentCreator
import kotlinx.android.synthetic.main.fragment_filters.*
import javax.inject.Inject

class FiltersFragment : BaseBottomDialog() {

    override val layoutResId = R.layout.fragment_filters

    @Inject
    lateinit var factory: FiltersViewModelFactory
    private lateinit var viewModel: FiltersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ComponentCreator.createFiltersComponent()
        FiltersComponent.get().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, factory).get(FiltersViewModel::class.java)
        subscribeToFilters()
        viewModel.flowRadiusFilterList()
    }

    private fun subscribeToFilters() {
        viewModel.radiusFiltersViewState.observe(this, Observer<DataModelViewState<List<FilterRadius>>> { state ->
            state.data?.also {
                buildRadiusFilters(it)
                listenRadioButtons()
            }
        })
    }

    private fun listenRadioButtons() {
        radius_radio_group.setOnCheckedChangeListener { group, checkedId ->
            val selectedIndex = group.run { indexOfChild(findViewById(checkedId)) }
            val stateData = viewModel.radiusFiltersViewState.value?.data
            val dataToSave = stateData?.mapIndexed { i, radius -> radius.copy(selected = i == selectedIndex) }
            dataToSave?.also { viewModel.saveRadiusFilterList(it) }
        }
    }

    private fun buildRadiusFilters(list: List<FilterRadius>) {
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
    }
}