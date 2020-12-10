package com.lekstel.aqi.base.domain.interactor

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect

abstract class BaseFlowUseCase<T, P> : BaseUseCase() {

    protected abstract suspend fun getFlow(params: P): Flow<T>

    fun execute(
        coroutineScope: CoroutineScope,
        params: P,
        observe: (next: T?, error: Throwable?) -> Unit
    ) {
        scope = coroutineScope
        job = coroutineScope.launch {
            try {
                getFlow(params).collect {
                    observe(it, null)
                }
            } catch (e: Throwable) {
                if (e !is CancellationException) {
                    observe(null, e)
                }
            }
        }
    }
}