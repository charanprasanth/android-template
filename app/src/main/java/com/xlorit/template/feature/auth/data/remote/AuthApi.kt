package com.xlorit.template.feature.auth.data.remote

import com.xlorit.template.feature.auth.data.remote.dto.LoginRequest
import com.xlorit.template.feature.auth.data.remote.dto.LoginResponse
import com.xlorit.template.feature.auth.data.remote.dto.RefreshRequest
import com.xlorit.template.feature.auth.data.remote.dto.RefreshResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("auth/login")
    suspend fun login(
        @Body request: LoginRequest
    ): LoginResponse

    @POST("auth/refresh")
    suspend fun refresh(
        @Body request: RefreshRequest
    ): RefreshResponse
}
