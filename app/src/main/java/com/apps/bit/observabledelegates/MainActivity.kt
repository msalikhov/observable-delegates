package com.apps.bit.observabledelegates

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var disposable: Disposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        TimeUpdater::timeNanos.ldObservable.observeChanges(this, this::updateTimeNanos)
    }

    override fun onStart() {
        super.onStart()
        disposable = TimeUpdater::timeMillis.rxObservable.subscribe(this::updateTimeMillis)
    }

    override fun onStop() {
        disposable.dispose()
        super.onStop()
    }

    private fun updateTimeMillis(value: Long) = updateTime(time_millis, value)

    private fun updateTimeNanos(value: Long) = updateTime(time_nanos, value)

    private fun updateTime(tv: TextView, value: Long) = tv.setText(value.toString())
}
