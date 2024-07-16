package com.bahaa.task.di

import com.bahaa.task.BuildConfig
import com.bahaa.task.data.remote.RemoteMainRepositoryImpl
import com.bahaa.task.data.remote.DataApi
import com.bahaa.task.domain.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    @Singleton
    @Provides
    fun provideCatsApi(client: OkHttpClient):DataApi{
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(DataApi::class.java)
    }

    @Singleton
    @Provides
    fun provideCatsRepository(remoteMainRepositoryImpl: RemoteMainRepositoryImpl): MainRepository{
        return remoteMainRepositoryImpl
    }

    @Singleton
    @Provides
    fun provideOkhttpClient():OkHttpClient{
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .build()
    }

}