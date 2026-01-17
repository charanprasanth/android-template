package com.xlorit.template.di

import com.xlorit.template.core.dispatcher.DefaultDispatcherProvider
import com.xlorit.template.core.dispatcher.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DispatcherModule {

    @Provides
    fun provideDispatcher(): DispatcherProvider =
        DefaultDispatcherProvider()
}
