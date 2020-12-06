package com.lekstel.aqi.base.di

import androidx.annotation.CallSuper

abstract class BaseComponentHandler<C : A, A> {

    var component: C? = null

    fun get(): C = component ?: error("You must call child's create() method at first")

    @CallSuper
    open fun resetComponent() {
        component = null
    }
}