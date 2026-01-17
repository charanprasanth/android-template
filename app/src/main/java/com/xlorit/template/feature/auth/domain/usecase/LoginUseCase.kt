package com.xlorit.template.feature.auth.domain.usecase

import com.xlorit.template.core.result.Result
import com.xlorit.template.feature.auth.domain.repository.AuthRepository
import jakarta.inject.Inject

class LoginUseCase @Inject constructor(
    private val repo: AuthRepository
) {
    suspend operator fun invoke(
        email: String,
        password: String
    ): Result<Unit> = repo.login(email, password)
}

