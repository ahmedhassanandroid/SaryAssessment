package com.hassan.saryassessment.core.presentation.di.modules

import android.app.Application
import android.content.Context
import com.hassan.saryassessment.BuildConfig
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASED_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(getOkHttp())
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    private fun getOkHttp(): OkHttpClient {

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val headersInterceptor = Interceptor{chain ->
            val requestBuilder = chain.request().newBuilder()
                .addHeader("Authorization", "token eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6ODg2NiwidXNlcl9waG9uZSI6Ijk2NjU2NDk4OTI1MCJ9.VYE28vtnMRLmwBISgvvnhOmPuGueW49ogOhXm5ZqsGU")
                .addHeader("Device-Type", "android")
                .addHeader("Accept-Language", "ar")
                .addHeader("App-Version", "3.1.1.0.0")
            chain.proceed(requestBuilder.build())
        }
        return OkHttpClient().newBuilder()
            .addInterceptor(logging)
            .addInterceptor(headersInterceptor)
            .build()
    }
}