package com.lekstel.aqi.base.domain.interactor

import kotlinx.coroutines.*

abstract class BaseUseCase {

    protected open var launchDispatcher: CoroutineDispatcher = Dispatchers.IO

    protected open var observeDispatcher: CoroutineDispatcher = Dispatchers.Main

    protected var job: Job? = null

    protected var scope: CoroutineScope? = null

    fun cancel() {
        job?.cancel()
        job = null
        scope = null
    }
}