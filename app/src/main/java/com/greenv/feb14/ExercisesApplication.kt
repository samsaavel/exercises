package com.greenv.feb14

import com.greenv.feb14.di.component.DaggerAppComponent
import dagger.android.DaggerApplication

class ExercisesApplication : DaggerApplication() {
    override fun applicationInjector() = DaggerAppComponent.factory().create(this)

}