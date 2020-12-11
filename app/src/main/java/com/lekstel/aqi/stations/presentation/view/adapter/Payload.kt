package com.lekstel.aqi.stations.presentation.view.adapter

import com.lekstel.aqi.base.presentation.view.adapter.BaseListItem
import com.lekstel.aqi.stations.domain.model.StationOnMap

enum class Payload(val predicate: (first: BaseListItem, second: BaseListItem) -> Boolean) {
    Id({ first, second -> first is StationOnMap && second is StationOnMap && first.id != second.id }),
    Name({ first, second -> first is StationOnMap && second is StationOnMap && first.name != second.name }),
    Aqi({ first, second -> first is StationOnMap && second is StationOnMap && first.aqi != second.aqi }),
}