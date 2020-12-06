package com.lekstel.aqi.filters.domain.interactor

import com.lekstel.aqi.filters.domain.model.FilterRadius
import com.lekstel.aqi.filters.domain.repository.IFiltersRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class FlowFiltersUseCase @Inject constructor(
        private val repository: IFiltersRepository
) {
    private val defaultRadiusFilters = listOf(
            FilterRadius(1, 5, false),
            FilterRadius(2, 10, false),
            FilterRadius(3, 20, true),
            FilterRadius(4, 50, false),
            FilterRadius(5, 100, false)
    )

    fun execute(coroutineScope: CoroutineScope, collect: (next: List<FilterRadius>?, error: Throwable?) -> Unit) {
        coroutineScope.launch {
            try {
                repository.flow().collect {
                    collect(it.takeIf { it.isNotEmpty() } ?: defaultRadiusFilters, null)
                }
            } catch (e: Throwable) {
                collect(null, e)
            }
        }
    }
}