package com.example.salttest.data.di

import com.example.salttest.data.network.ApiServices
import com.example.salttest.data.repository.Repository
import com.example.salttest.data.repository.RepositoryImpl
import com.example.salttest.utils.BASEAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Module {

    @Provides
    @Singleton
    fun provideOkhttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            ).build()
    }

    @Provides
    @Singleton
    fun provideApi(client: OkHttpClient): ApiServices {
        return Retrofit.Builder()
            .baseUrl(BASEAPI)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideRepository(
        api: ApiServices
    ): Repository {
        return RepositoryImpl(
            api = api
        )
    }

}