package com.lekstel.aqi.stations.presentation.view.adapter

import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateLayoutContainer
import com.lekstel.aqi.R
import com.lekstel.aqi.base.presentation.view.adapter.BaseListItem
import com.lekstel.aqi.base.presentation.view.adapter.bindWithPayloadActions
import com.lekstel.aqi.stations.domain.model.StationOnMap
import kotlinx.android.synthetic.main.item_station.*

fun stationAdapterDelegate() =
    adapterDelegateLayoutContainer<StationOnMap, BaseListItem>(R.layout.item_station) {

        fun updateId(item: StationOnMap) {
            tv_id.text = item.id.toString()
        }

        fun updateName(item: StationOnMap) {
            tv_name.text = item.name
        }

        fun updateAqi(item: StationOnMap) {
            tv_aqi.text = item.aqi
        }

        val payloadActions = mapOf(
            Payload.Id to ::updateId,
            Payload.Name to ::updateName,
            Payload.Aqi to ::updateAqi
        )

        bindWithPayloadActions(payloadActions)
    }