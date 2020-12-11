package com.lekstel.aqi.stations.presentation.view.adapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.lekstel.aqi.base.presentation.view.adapter.BaseListItem

class StationsDiffCallback
    : DiffUtil.ItemCallback<BaseListItem>() {

    override fun areItemsTheSame(oldItem: BaseListItem, newItem: BaseListItem): Boolean =
        oldItem::class == newItem::class && oldItem.id == newItem.id

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: BaseListItem, newItem: BaseListItem): Boolean =
        oldItem == newItem

    override fun getChangePayload(
        oldItem: BaseListItem,
        newItem: BaseListItem
    ) = Payload.values().filter { it.predicate(oldItem, newItem) }
}