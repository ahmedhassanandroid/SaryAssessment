package com.hassan.saryassessment.flagship.presentation.di.module

import com.hassan.saryassessment.flagship.data.datasource.remote.FlagShipAPI
import com.hassan.saryassessment.flagship.data.repository.FlagShipRepositoryImpl
import com.hassan.saryassessment.flagship.domain.repository.FlagShipRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class FlagShipModule {
    @Provides
    fun provideFlagShipRepo(flagShipRepositoryImpl: FlagShipRepositoryImpl)
            : FlagShipRepository = flagShipRepositoryImpl

    @Provides
    fun provideFlagShipAPI(retrofit: Retrofit): FlagShipAPI{
        return retrofit.create(FlagShipAPI::class.java)
    }
}