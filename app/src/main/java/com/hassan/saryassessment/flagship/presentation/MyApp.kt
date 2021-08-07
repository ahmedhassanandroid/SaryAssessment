package com.hassan.saryassessment.flagship.presentation

import android.app.Application
import com.hassan.saryassessment.core.presentation.di.component.AppComponent
import com.hassan.saryassessment.core.presentation.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class MyApp: Application(), HasAndroidInjector {
    @Inject
    lateinit var androidInjector : DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    private var appComponent: AppComponent? = null

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .application(this)
            .build()
        appComponent?.inject(this)
    }
}