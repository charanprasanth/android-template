package com.xlorit.template.core.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPreferencesImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : UserPreferences {

    companion object {
        val ACCESS = stringPreferencesKey("access_token")
        val REFRESH = stringPreferencesKey("refresh_token")
    }

    override val accessToken: Flow<String?> =
        dataStore.data.map { it[ACCESS] }

    override val refreshToken: Flow<String?> =
        dataStore.data.map { it[REFRESH] }

    override suspend fun saveAccessToken(token: String) {
        dataStore.edit { it[ACCESS] = token }
    }

    override suspend fun saveRefreshToken(token: String) {
        dataStore.edit { it[REFRESH] = token }
    }

    override suspend fun clear() {
        dataStore.edit { it.clear() }
    }
}
