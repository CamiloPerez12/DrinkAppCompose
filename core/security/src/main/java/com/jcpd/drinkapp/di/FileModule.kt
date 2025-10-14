package com.jcpd.drinkapp.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.io.File
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FileModule {
    @Singleton
    @Provides
    @Named("secretFile")
    fun provideSecretFile(@ApplicationContext context: Context): File {
        return File(context.filesDir, "secret.txt")
    }
}