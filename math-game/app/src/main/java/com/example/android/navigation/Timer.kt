package com.example.android.navigation

import android.os.Handler
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.Observer
import androidx.lifecycle.OnLifecycleEvent
import timber.log.Timber

class Timer {

    var secondsCount = 0

    private var handler = Handler()
    private lateinit var runnable: Runnable

    fun startTimer() {
        runnable = Runnable {
            secondsCount++
            Timber.i("Timer is at : $secondsCount")

            handler.postDelayed(runnable, 1000)
        }

        handler.postDelayed(runnable, 1000)
    }

    fun stopTimer() {
        handler.removeCallbacks(runnable)
    }

}