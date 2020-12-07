package com.lekstel.aqi.filters.domain.interactor

import com.lekstel.aqi.filters.domain.model.FilterRadius
import com.lekstel.aqi.filters.domain.repository.IFiltersRepository
import kotlinx.coroutines.*
import javax.inject.Inject

class SaveFilterRadiusListUseCase @Inject constructor(
        private val repository: IFiltersRepository
) {
    private var job: Job? = null

    fun execute(coroutineScope: CoroutineScope, filters: List<FilterRadius>, collect: (error: Throwable?) -> Unit) {
        var error: Throwable? = null
        job = coroutineScope.launch(Dispatchers.IO) {
            try {
                repository.saveFilterRadiusList(filters)
            } catch (e: Throwable) {
                error = e.takeIf { it !is CancellationException }
            }
            launch(Dispatchers.Main) { collect(error) }
        }
    }

    fun cancel() {
        job?.cancel()
        job = null
    }
}