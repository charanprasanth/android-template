package com.xlorit.template.core.datastore

import kotlinx.coroutines.flow.Flow

interface UserPreferences {
    val accessToken: Flow<String?>
    val refreshToken: Flow<String?>

    suspend fun saveAccessToken(token: String)
    suspend fun saveRefreshToken(token: String)
    suspend fun clear()
}
