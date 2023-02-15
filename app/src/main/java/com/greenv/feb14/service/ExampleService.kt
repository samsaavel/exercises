package com.greenv.feb14.service

import android.app.IntentService
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class ExampleService : IntentService("ExampleService") {

    private val TAG = "ExampleServiceClass"

    override fun onHandleIntent(intent: Intent?) {
        Log.d(TAG, "Example service started")
    }
}

class MyService : Service() {

    private val TAG = "ServiceClass"

    override fun onCreate() {
        Log.d(TAG, "Service created")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "Service onCommand " + startId)

        var i: Int = 0

        while (i < 3) {
            try {
                Thread.sleep(1000)
                i++
            } catch (e: Exception) {
                Log.d(TAG, "onStartCommandError: " + e.localizedMessage)
            }
            Log.d(TAG, "onStartCommand: Service running")
        }
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        Log.i(TAG, "Service onBind")
        return null
    }

    override fun onDestroy() {
        Log.i(TAG, "Service onDestroy")
    }

}