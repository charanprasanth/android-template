package com.xlorit.template.core.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit

object ApiClient {
    fun create(
        baseUrl: String,
        okHttpClient: OkHttpClient
    ): Retrofit = RetrofitFactory.create(baseUrl, okHttpClient)
}
