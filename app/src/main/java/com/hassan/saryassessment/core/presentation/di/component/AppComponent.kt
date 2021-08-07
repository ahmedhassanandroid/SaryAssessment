package com.hassan.saryassessment.core.presentation.di.component

import android.app.Application
import com.hassan.saryassessment.flagship.presentation.MyApp
import com.hassan.saryassessment.core.presentation.di.modules.ActivityBuilder
import com.hassan.saryassessment.core.presentation.di.modules.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, AppModule::class, ActivityBuilder::class])
interface AppComponent {

    fun inject(app: MyApp)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}