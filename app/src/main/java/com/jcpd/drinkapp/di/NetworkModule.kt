package com.jcpd.drinkapp.di

import com.jcpd.drinkapp.data.network.NetworkDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideBaseUrl(): String = "https://www.thecocktaildb.com/api/json/v1/1/"

    @Singleton
    @Provides
    fun provideRetrofit(@Named("BaseUrl") baseUrl : String): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build()
    }

    @Singleton
    @Provides
    fun restDataSource(retrofit: Retrofit): NetworkDataSource =
        retrofit.create(NetworkDataSource::class.java)

}