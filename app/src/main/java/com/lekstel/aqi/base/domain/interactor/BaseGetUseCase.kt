package com.lekstel.aqi.base.domain.interactor

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

abstract class BaseGetUseCase<T, P> : BaseUseCase() {

    protected abstract suspend fun onLaunch(params: P): T?

    fun execute(
            coroutineScope: CoroutineScope,
            params: P,
            observe: (next: T?, error: Throwable?) -> Unit
    ) {
        scope = coroutineScope
        job = coroutineScope.launch(launchDispatcher) {
            try {
                onLaunch(params).also {
                    launch(observeDispatcher) { observe(it, null) }
                }
            } catch (e: Throwable) {
                if (e !is CancellationException) {
                    launch(observeDispatcher) { observe(null, e) }
                }
            }
        }
    }
}