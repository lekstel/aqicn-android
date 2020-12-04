package com.lekstel.aqi.base.di

import androidx.annotation.CallSuper

abstract class BaseComponentHandler<C : A, A> {

    var component: C? = null

    abstract fun init(): A

    fun get(): C = component ?: error("You must call 'init()' method at first")

    @CallSuper
    open fun resetComponent() {
        component = null
    }
}