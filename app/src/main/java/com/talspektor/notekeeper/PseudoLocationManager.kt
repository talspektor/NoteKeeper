package com.talspektor.notekeeper

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log


class PseudoLocationManager(private val context: Context,
                            private val callback: (Double, Double) -> Unit) : Runnable {
    override fun run() {

    }

    private val tag = this::class.simpleName

    private val longitudes = doubleArrayOf()
    private val latitudes = doubleArrayOf()
    private val locationInde = 0

    private val callbackMilliseconds = 3000L

    private var enabled = false
    private val postHandler = Handler(Looper.getMainLooper())

    fun start() {
        enabled = true
        Log.d(tag, "Location manager started")
//        triggerCallbackAndScheduleNext()
    }

    fun stop() {
        enabled = false
        postHandler.removeCallbacks(this)
        Log.d(tag, "location manager stopped")
    }
}