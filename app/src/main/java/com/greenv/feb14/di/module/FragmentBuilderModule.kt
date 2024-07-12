package com.greenv.feb14.di.module

import com.greenv.feb14.ui.fragments.BottomSheetFragment
import com.greenv.feb14.ui.fragments.RaMFragment
import com.greenv.feb14.ui.fragments.CounterFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    abstract fun providesRaMFragment(): RaMFragment

    @ContributesAndroidInjector
    abstract fun providesServiceFragment(): CounterFragment

    @ContributesAndroidInjector
    abstract fun providesBottomSheetFragment(): BottomSheetFragment
}