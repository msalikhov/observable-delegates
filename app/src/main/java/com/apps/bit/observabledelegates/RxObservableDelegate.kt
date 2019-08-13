package com.apps.bit.observabledelegates

import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.subjects.BehaviorSubject
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class RxObservableDelegate<T : Any> : Observable<T>(), ReadWriteProperty<Any, T> {
    private val subject = BehaviorSubject.create<T>()

    override fun subscribeActual(observer: Observer<in T>) = subject.subscribe(observer)
    override fun getValue(thisRef: Any, property: KProperty<*>) = subject.value!!
    override fun setValue(thisRef: Any, property: KProperty<*>, value: T) = subject.onNext(value)
}