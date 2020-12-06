package com.lekstel.aqi.filters.presentation.view

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.lekstel.aqi.R
import com.lekstel.aqi.base.presentation.view.BaseBottomDialog
import com.lekstel.aqi.filters.di.component.FiltersComponent
import com.lekstel.aqi.filters.presentation.view_model.FiltersViewModel
import com.lekstel.aqi.main.ComponentCreator

class FiltersFragment : BaseBottomDialog() {

    override val layoutResId = R.layout.fragment_filters

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ComponentCreator.createFiltersComponent()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val factory = FiltersComponent.get().factory()
        val viewModel = ViewModelProvider(this, factory).get(FiltersViewModel::class.java)
        viewModel.flow()
    }
}