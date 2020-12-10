package com.lekstel.aqi.filters.domain.interactor

import com.lekstel.aqi.base.domain.interactor.BaseCompleteUseCase
import com.lekstel.aqi.filters.domain.model.FilterMinQuality
import com.lekstel.aqi.filters.domain.repository.IFiltersRepository
import javax.inject.Inject

class SaveFilterMinQualityListUseCase @Inject constructor(
    private val repository: IFiltersRepository
) : BaseCompleteUseCase<SaveFilterMinQualityListUseCase.Params>() {

    override suspend fun onLaunch(params: Params) =
        repository.saveFilterMinQualityList(params.filters)

    data class Params(val filters: List<FilterMinQuality>)
}