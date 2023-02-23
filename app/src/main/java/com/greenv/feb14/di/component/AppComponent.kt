package com.greenv.feb14.di.component

import android.app.Application
import com.greenv.feb14.ExercisesApplication
import com.greenv.feb14.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjection
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ActivityBuilderModule::class,
        FragmentBuilderModule::class,
        AndroidSupportInjection::class,
        ViewModelFactoryModule::class,
        ViewModelModule::class,
        RepositoryModule::class
    ]
)
interface AppComponent : AndroidInjector<ExercisesApplication> {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): AppComponent
    }
}