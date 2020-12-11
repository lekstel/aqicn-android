package com.lekstel.aqi.base.presentation.view.adapter

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

abstract class DelegationAdapter(diffCallback: DiffUtil.ItemCallback<BaseListItem>) :
    AsyncListDifferDelegationAdapter<BaseListItem>(diffCallback) {

    fun addDelegate(delegate: AdapterDelegate<List<BaseListItem>>) {
        delegatesManager.addDelegate(delegate)
    }
}