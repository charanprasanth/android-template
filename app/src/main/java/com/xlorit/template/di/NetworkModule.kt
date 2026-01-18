package com.xlorit.template.di

import com.xlorit.template.BuildConfig
import com.xlorit.template.core.network.AuthInterceptor
import com.xlorit.template.core.network.OkHttpFactory
import com.xlorit.template.core.network.RetrofitFactory
import com.xlorit.template.core.network.TokenRefreshInterceptor
import com.xlorit.template.feature.auth.data.remote.AuthApi
import com.xlorit.template.feature.auth.data.remote.RefreshApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideAuthOkHttp(
        authInterceptor: AuthInterceptor,
        refreshInterceptor: TokenRefreshInterceptor
    ): OkHttpClient =
        OkHttpFactory.create(
            authInterceptor = authInterceptor,
            refreshInterceptor = refreshInterceptor
        )

    @Provides
    @Singleton
    @Named("refresh")
    fun provideRefreshOkHttp(): OkHttpClient =
        OkHttpFactory.create()

    // ---------- RETROFIT ----------

    @Provides
    @Singleton
    fun provideAuthRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit =
        RetrofitFactory.create(
            baseUrl = BuildConfig.BASE_URL,
            okHttpClient = okHttpClient
        )

    @Provides
    @Singleton
    @Named("refresh")
    fun provideRefreshRetrofit(
        @Named("refresh") okHttpClient: OkHttpClient
    ): Retrofit =
        RetrofitFactory.create(
            baseUrl = BuildConfig.BASE_URL,
            okHttpClient = okHttpClient
        )

    // ---------- APIS ----------

    @Provides
    @Singleton
    fun provideAuthApi(
        retrofit: Retrofit
    ): AuthApi =
        retrofit.create(AuthApi::class.java)

    @Provides
    @Singleton
    fun provideRefreshApi(
        @Named("refresh") retrofit: Retrofit
    ): RefreshApi =
        retrofit.create(RefreshApi::class.java)
}
