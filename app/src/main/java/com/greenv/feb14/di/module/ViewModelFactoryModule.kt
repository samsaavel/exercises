package com.greenv.feb14.di.module

import androidx.lifecycle.ViewModelProvider
import com.greenv.feb14.di.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {

    @Binds
    internal abstract fun bindViewModelFactory( factory: ViewModelFactory):
            ViewModelProvider.Factory
}