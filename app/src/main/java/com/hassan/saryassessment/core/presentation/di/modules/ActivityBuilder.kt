package com.hassan.saryassessment.core.presentation.di.modules

import com.hassan.saryassessment.flagship.presentation.di.module.FlagShipModule
import com.hassan.saryassessment.flagship.presentation.view.activity.FlagShipActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {
    @ContributesAndroidInjector(modules = [FlagShipModule::class])
    abstract fun bindFlagShipActivity(): FlagShipActivity
}