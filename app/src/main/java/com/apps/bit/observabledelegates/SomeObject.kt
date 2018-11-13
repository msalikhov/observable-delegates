package com.apps.bit.observabledelegates

import java.util.*

object SomeObject : TimerTask() {
    var someValue by ObservableValue<String>()

    init {
        Timer().scheduleAtFixedRate(this, 1000, 1000)
    }

    override fun run() {
        someValue = Date().toString()
    }
}