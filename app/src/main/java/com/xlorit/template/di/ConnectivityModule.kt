package com.xlorit.template.di

import android.content.Context
import com.xlorit.template.core.connectivity.ConnectivityObserver
import com.xlorit.template.core.connectivity.NetworkConnectivityObserver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ConnectivityModule {

    @Provides
    fun provideConnectivityObserver(
        @ApplicationContext context: Context
    ): ConnectivityObserver = NetworkConnectivityObserver(context)
}
