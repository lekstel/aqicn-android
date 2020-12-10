package com.lekstel.aqi.base.domain.interactor

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

abstract class BaseCompleteUseCase<P> : BaseUseCase() {

    protected abstract suspend fun onLaunch(params: P)

    fun execute(coroutineScope: CoroutineScope, params: P, observe: (error: Throwable?) -> Unit) {
        job = coroutineScope.launch(launchDispatcher) {
            try {
                onLaunch(params)
                launch(observeDispatcher) { observe(null) }
            } catch (e: Throwable) {
                if (e !is CancellationException) {
                    launch(observeDispatcher) { observe(e) }
                }
            }
        }
    }
}