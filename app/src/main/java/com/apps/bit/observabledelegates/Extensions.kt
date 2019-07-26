package com.apps.bit.observabledelegates

import kotlin.reflect.KProperty0
import kotlin.reflect.jvm.isAccessible

/**
 * Allows to get something from property, which are not accessible
 */
inline fun <R> KProperty0<*>.getNonAccessible(action: KProperty0<*>.() -> R): R {
    isAccessible = true
    val result = action()
    isAccessible = false
    return result
}

/**
 * Returnes typed ObservableValue delegate from property
 * if it is delegated by these
 * or Exception otherwise
 */
@Suppress("UNCHECKED_CAST")
val <reified R : Any> KProperty0<R>.observable
    inline get() = getNonAccessible { getDelegate() } as ObservableValue<R>