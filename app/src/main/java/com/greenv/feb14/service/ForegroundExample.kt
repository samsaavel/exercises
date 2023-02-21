package com.greenv.feb14.service

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import android.provider.Settings
import android.util.Log
import kotlinx.serialization.builtins.serializer

class ForegroundExample : Service() {

    private lateinit var examplePlayer: MediaPlayer

    override fun onStartCommand(init: Intent, flag: Int, startId: Int): Int {
        examplePlayer = MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI)
        examplePlayer.setLooping(true)
        examplePlayer.start()
        Log.d("vanneDebug", "Start service")
        return START_STICKY
    }

    override fun onCreate() {
        super.onCreate()
    }

    override fun onDestroy() {
        super.onDestroy()
        examplePlayer.stop()
    }

    override fun onBind(intent:Intent) = MyBinder()

    inner class MyBinder: Binder() {
        fun getServiceExample() : ForegroundExample? {
            return this@ForegroundExample
        }
    }
}