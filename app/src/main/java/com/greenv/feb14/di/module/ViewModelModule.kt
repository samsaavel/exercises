package com.greenv.feb14.di.module

import androidx.lifecycle.ViewModel
import com.greenv.feb14.di.viewmodel.ViewModelKey
import com.greenv.feb14.ui.viewmodel.RamViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(RamViewModel::class)
    internal abstract fun bindRamViewModel(ramViewModel: RamViewModel): ViewModel
}