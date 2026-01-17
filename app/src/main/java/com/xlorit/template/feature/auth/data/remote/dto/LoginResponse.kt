package com.xlorit.template.feature.auth.data.remote.dto

data class LoginResponse(
    val accessToken: String,
    val refreshToken: String
)
