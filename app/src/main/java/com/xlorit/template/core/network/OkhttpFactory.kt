package com.xlorit.template.core.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

object OkHttpFactory {

    fun create(
        authInterceptor: AuthInterceptor? = null,
        refreshInterceptor: TokenRefreshInterceptor? = null
    ): OkHttpClient {

        val builder = OkHttpClient.Builder()

        if (authInterceptor != null) {
            builder.addInterceptor(authInterceptor)
        }

        if (refreshInterceptor != null) {
            builder.addInterceptor(refreshInterceptor)
        }

        builder.addInterceptor(
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        )

        return builder.build()
    }
}
