package com.xlorit.template.core.network

import com.xlorit.template.core.datastore.UserPreferences
import com.xlorit.template.feature.auth.data.remote.AuthApi
import com.xlorit.template.feature.auth.data.remote.dto.RefreshRequest
import jakarta.inject.Inject
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class TokenRefreshInterceptor @Inject constructor(
    private val authApi: AuthApi,
    private val prefs: UserPreferences
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())

        if (response.code != 401) return response

        response.close()

        val refreshToken = runBlocking {
            prefs.refreshToken.first()
        } ?: return response

        val refreshResponse = runBlocking {
            authApi.refresh(
                RefreshRequest(refreshToken)
            )
        }

        runBlocking {
            prefs.saveAccessToken(refreshResponse.accessToken)
            prefs.saveRefreshToken(refreshResponse.refreshToken)
        }

        val newRequest = chain.request().newBuilder()
            .header("Authorization", "Bearer ${refreshResponse.accessToken}")
            .build()

        return chain.proceed(newRequest)
    }
}
