package com.lekstel.aqi.stations.presentation.view.adapter

import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateLayoutContainer
import com.lekstel.aqi.R
import com.lekstel.aqi.base.presentation.view.adapter.BaseListItem
import com.lekstel.aqi.base.presentation.view.adapter.bindWithPayloadActions
import com.lekstel.aqi.stations.domain.model.StationOnMap
import kotlinx.android.synthetic.main.item_station.*

fun stationAdapterDelegate(itemClickListener : (StationOnMap) -> Unit) =
    adapterDelegateLayoutContainer<StationOnMap, BaseListItem>(R.layout.item_station) {

        item_root.setOnClickListener { itemClickListener(item) }

        fun updateId(item: StationOnMap) {
            tv_id.text = getString(R.string.list_id, item.id)
        }

        fun updateName(item: StationOnMap) {
            tv_name.text = getString(R.string.list_name, item.name)
        }

        fun updateAqi(item: StationOnMap) {
            tv_aqi.text = getString(R.string.list_aqi, item.aqi)
        }

        val payloadActions = mapOf(
            Payload.Id to ::updateId,
            Payload.Name to ::updateName,
            Payload.Aqi to ::updateAqi
        )

        bindWithPayloadActions(payloadActions)
    }