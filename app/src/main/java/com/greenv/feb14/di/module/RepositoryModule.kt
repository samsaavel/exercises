package com.greenv.feb14.di.module

import com.greenv.feb14.network.RamApi
import com.greenv.feb14.repository.RamRepository
import com.greenv.feb14.repository.RamRepositoryContract
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun providesRamRepository(ramApi: RamApi): RamRepositoryContract =
        RamRepository(ramApi)
}