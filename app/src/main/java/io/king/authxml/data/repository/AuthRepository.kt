package io.king.authxml.data.repository

import io.king.authxml.data.UserPreferences
import io.king.authxml.data.network.AuthApi

class AuthRepository(
    private val api: AuthApi,
    private val preferences: UserPreferences
)  : BaseRepository(){

    suspend fun login(
        email: String,
        password: String
    ) = safeApiCall {
        api.login(email, password)
    }

    suspend fun saveAuthToken(token: String){
        preferences.saveAccessTokens(token)
    }

}