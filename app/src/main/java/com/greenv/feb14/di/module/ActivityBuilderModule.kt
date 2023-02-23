package com.greenv.feb14.di.module

import com.greenv.feb14.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {
    @ContributesAndroidInjector(
        modules = [
            FragmentBuilderModule::class,
            ViewModelFactoryModule::class
        ]
    )

    abstract fun contributeMainActivity(): MainActivity
}