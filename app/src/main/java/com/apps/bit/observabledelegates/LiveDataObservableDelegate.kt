package com.apps.bit.observabledelegates

import android.os.Looper
import androidx.lifecycle.LiveData
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class LiveDataObservableDelegate<T : Any> : LiveData<T>(), ReadWriteProperty<Any, T> {
    override fun getValue(thisRef: Any, property: KProperty<*>) = super.getValue()!!
    override fun setValue(thisRef: Any, property: KProperty<*>, value: T) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            super.setValue(value)
        } else {
            super.postValue(value)
        }
    }
}