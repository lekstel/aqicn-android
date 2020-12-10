package com.lekstel.aqi.filters.domain.interactor

import com.lekstel.aqi.base.domain.interactor.BaseCompleteUseCase
import com.lekstel.aqi.filters.domain.model.FilterRadius
import com.lekstel.aqi.filters.domain.repository.IFiltersRepository
import javax.inject.Inject

class SaveFilterRadiusListUseCase @Inject constructor(
    private val repository: IFiltersRepository
) : BaseCompleteUseCase<SaveFilterRadiusListUseCase.Params>() {

    override suspend fun onLaunch(params: Params) = repository.saveFilterRadiusList(params.filters)

    data class Params(val filters: List<FilterRadius>)
}