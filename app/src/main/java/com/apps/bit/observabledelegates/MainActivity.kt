package com.apps.bit.observabledelegates

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val loggingObserver: (String) -> Unit = {
        Log.d("---", "observable value changed: $it")
    }

    private val textViewObserver: (String) -> Unit = {
        textview.text = it
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()

        //initial value will be set immediately
        SomeObject::someValue.observable.addObserver(textViewObserver)
        SomeObject::someValue.observable.addObserver(loggingObserver)
    }

    override fun onStop() {
        SomeObject::someValue.observable.removeObserver(textViewObserver)
        SomeObject::someValue.observable.removeObserver(loggingObserver)
        super.onStop()
    }
}
