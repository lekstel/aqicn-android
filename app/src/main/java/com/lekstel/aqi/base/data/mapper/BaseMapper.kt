package com.lekstel.aqi.base.data.mapper

abstract class BaseMapper<From, To> {

    abstract fun map(from: From): To

    abstract fun reverse(to: To): From

    fun map(from: List<From>): List<To> = from.map { map(it) }

    fun reverse(to: List<To>): List<From> = to.map { reverse(it) }
}