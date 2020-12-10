package com.lekstel.aqi.filters.domain.interactor

import com.lekstel.aqi.base.domain.interactor.BaseFlowUseCase
import com.lekstel.aqi.filters.domain.model.FilterRadius
import com.lekstel.aqi.filters.domain.repository.IFiltersRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FlowFilterRadiusListUseCase @Inject constructor(
    private val repository: IFiltersRepository
) : BaseFlowUseCase<List<FilterRadius>, Unit>() {

    override suspend fun getFlow(params: Unit) = repository.flowFilterRadiusList()
        .map { it.takeIf { it.isNotEmpty() } ?: defaultFilterRadiusList }

    private val defaultFilterRadiusList = listOf(
        FilterRadius(1, 5, false),
        FilterRadius(2, 10, false),
        FilterRadius(3, 20, true),
        FilterRadius(4, 50, false),
        FilterRadius(5, 100, false)
    )
}