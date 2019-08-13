package com.apps.bit.observabledelegates

import java.util.*

object TimeUpdater : TimerTask() {
    var timeMillis by RxObservableDelegate<Long>(); private set
    var timeNanos by LiveDataObservableDelegate<Long>(); private set

    init {
        Timer().scheduleAtFixedRate(this, 0, 32)
    }

    override fun run() {
        timeMillis = System.currentTimeMillis()
        timeNanos = System.nanoTime()
    }
}