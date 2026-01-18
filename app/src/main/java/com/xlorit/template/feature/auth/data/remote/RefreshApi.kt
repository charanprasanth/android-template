package com.xlorit.template.feature.auth.data.remote

import com.xlorit.template.feature.auth.data.remote.dto.RefreshRequest
import com.xlorit.template.feature.auth.data.remote.dto.RefreshResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface RefreshApi {
    @POST("auth/refresh")
    suspend fun refresh(
        @Body request: RefreshRequest
    ): RefreshResponse
}
