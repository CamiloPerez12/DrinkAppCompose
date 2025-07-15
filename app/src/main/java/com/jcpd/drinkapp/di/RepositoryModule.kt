package com.jcpd.drinkapp.di

import com.jcpd.drinkapp.data.Repository
import com.jcpd.drinkapp.data.RepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindRepository(impl: RepositoryImpl): Repository
}