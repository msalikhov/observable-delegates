package com.apps.bit.observabledelegates

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class ObservableValue<T : Any> : ReadWriteProperty<Any, T> {

    private val observers = mutableListOf<(T) -> Unit>()

    private lateinit var value: T

    /**
     * Empty constructor, to set value later
     */
    constructor()

    /**
     * Constructor with initial value
     */
    constructor(value: T) {
        this.value = value
    }

    override fun getValue(thisRef: Any, property: KProperty<*>) = value

    /**
     * Sets the value to property and triggers all observers
     */
    override fun setValue(thisRef: Any, property: KProperty<*>, value: T) {
        this.value = value
        observers.forEach { it.invoke(value) }
    }

    /**
     * Adds observer if it doesn't already added
     * If value is already have been set - triggers observer immediately
     */
    fun addObserver(observer: (T) -> Unit) {
        if (observer in observers) {
            return
        } else {
            observers.add(observer)
        }

        if (::value.isInitialized) {
            observer.invoke(value)
        }
    }

    /**
     * Removes specified observer
     */
    fun removeObserver(observer: (T) -> Unit) = observers.remove(observer)

    /**
     * Removes all observers
     */
    fun removeAllObservers() = observers.clear()
}