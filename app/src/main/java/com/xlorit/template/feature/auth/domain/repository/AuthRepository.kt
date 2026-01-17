package com.xlorit.template.feature.auth.domain.repository

import com.xlorit.template.core.result.Result

interface AuthRepository {
    suspend fun login(
        email: String,
        password: String
    ): Result<Unit>
}

