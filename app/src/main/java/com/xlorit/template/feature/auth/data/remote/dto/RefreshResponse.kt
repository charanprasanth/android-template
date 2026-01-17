package com.xlorit.template.feature.auth.data.remote.dto

data class RefreshResponse(
    val accessToken: String,
    val refreshToken: String
)
