package com.podium.technicalchallenge.common.di

import com.google.gson.Gson
import com.podium.technicalchallenge.common.network.retrofit.GraphQLService
import com.podium.technicalchallenge.common.AuthorizationInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val API_URL = "https://podium-fe-challenge-2021.netlify.app/.netlify/functions/"

    @Provides
    @Singleton
    fun provideGson(): Gson{
        return Gson()
    }

    @Provides
    @Singleton
    fun provideRetrofitClient(): Retrofit {
        val client = OkHttpClient.Builder()
            .addInterceptor(AuthorizationInterceptor())
            .build()
        return Retrofit.Builder()
            .client(client)
            .baseUrl(API_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideGraphQlService(retrofit: Retrofit): GraphQLService {
        return retrofit.create(GraphQLService::class.java)
    }
}