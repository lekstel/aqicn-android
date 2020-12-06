package com.lekstel.aqi.filters.domain.interactor

import com.lekstel.aqi.filters.domain.model.FilterRadius
import com.lekstel.aqi.filters.domain.repository.IFiltersRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class SaveFiltersUseCase @Inject constructor(
        private val repository: IFiltersRepository
) {
    fun execute(filters: List<FilterRadius>, coroutineScope: CoroutineScope) {
        coroutineScope.launch(Dispatchers.IO) {
            repository.save(filters)
        }
    }
}