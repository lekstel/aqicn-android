package com.lekstel.aqi.filters.domain.interactor

import com.lekstel.aqi.base.domain.interactor.BaseFlowUseCase
import com.lekstel.aqi.filters.domain.model.FilterMinQuality
import com.lekstel.aqi.filters.domain.repository.IFiltersRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FlowFilterMinQualityListUseCase @Inject constructor(
    private val repository: IFiltersRepository
) : BaseFlowUseCase<List<FilterMinQuality>, Unit>() {

    override suspend fun getFlow(params: Unit) = repository.flowFilterMinQualityList()
        .map { it.takeIf { it.isNotEmpty() } ?: defaultFilterMinQualityList }

    private val defaultFilterMinQualityList = listOf(
        FilterMinQuality(1, 0, true),
        FilterMinQuality(2, 5, false),
        FilterMinQuality(3, 10, false),
        FilterMinQuality(4, 20, false),
        FilterMinQuality(5, 50, false),
        FilterMinQuality(6, 100, false),
        FilterMinQuality(7, 200, false)
    )
}