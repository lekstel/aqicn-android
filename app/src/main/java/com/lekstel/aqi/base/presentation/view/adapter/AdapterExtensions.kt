package com.lekstel.aqi.base.presentation.view.adapter

import com.hannesdorfmann.adapterdelegates4.dsl.AdapterDelegateLayoutContainerViewHolder
import kotlin.reflect.KFunction1

private fun List<Any>.flat() = flatMap { it as List<*> }.distinct()

fun <T : BaseListItem> AdapterDelegateLayoutContainerViewHolder<T>.bindWithPayloadActions(
    payloadActions: Map<*, KFunction1<T, Unit>>
) = bind {
    val payloads = it.flat()
    when {
        payloads.isEmpty() -> payloadActions.values.forEach { it(item) }
        else -> payloads.forEach { payloadActions[it]?.invoke(item) }
    }
}