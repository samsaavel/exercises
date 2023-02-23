package com.greenv.feb14

import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class ExercisesApplication : Application() {
    fun applicationInjector(): AndroidInjector<out DaggerApplication>? {
        return null
    }

}