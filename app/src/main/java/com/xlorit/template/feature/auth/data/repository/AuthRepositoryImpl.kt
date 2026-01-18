package com.xlorit.template.feature.auth.data.repository

import com.xlorit.template.core.datastore.UserPreferences
import com.xlorit.template.core.error.ErrorMapper
import com.xlorit.template.core.local.dao.UserDao
import com.xlorit.template.core.local.entity.UserEntity
import com.xlorit.template.core.result.Result
import com.xlorit.template.feature.auth.data.remote.AuthApi
import com.xlorit.template.feature.auth.data.remote.dto.LoginRequest
import com.xlorit.template.feature.auth.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val api: AuthApi,
    private val prefs: UserPreferences,
    private val userDao: UserDao
) : AuthRepository {

    override suspend fun login(
        email: String,
        password: String
    ): Result<Unit> {
        return try {
            val res = api.login(LoginRequest(email, password))

            prefs.saveAccessToken(res.accessToken)
            prefs.saveRefreshToken(res.refreshToken)

            userDao.insert(
                UserEntity(
                    id = "1",
                    email = email,
                    name = "User"
                )
            )

            Result.Success(Unit)
        } catch (t: Throwable) {
            Result.Error(ErrorMapper.map(t))
        }
    }
}

