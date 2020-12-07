package com.lekstel.aqi.base.presentation.view_model

data class DataModelViewState<T>(
        val isLoading: Boolean,
        val error: Throwable?,
        val data: T?
)