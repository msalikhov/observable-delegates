package com.apps.bit.observabledelegates

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import io.reactivex.Observable
import kotlin.reflect.KCallable
import kotlin.reflect.KProperty0
import kotlin.reflect.jvm.isAccessible

fun Any?.trace() = Log.d("---", toString())

inline fun <R, T, K : KCallable<T>> K.withAccess(action: (K) -> R): R = if (isAccessible) {
    action(this)
} else {
    isAccessible = true
    val result = action(this)
    isAccessible = false
    result
}

val <R : Any> KProperty0<R>.rxObservable get() = withAccess { it.getDelegate() } as Observable<R>

val <R : Any> KProperty0<R>.ldObservable get() = withAccess { it.getDelegate() } as LiveData<R>

inline fun <T> LiveData<T>.observeChanges(owner: LifecycleOwner, crossinline observer: (T) -> Unit) = observe(owner, Observer { observer(it) })