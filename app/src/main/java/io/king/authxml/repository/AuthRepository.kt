package io.king.authxml.repository

import io.king.authxml.network.AuthApi

class AuthRepository(
    private val api: AuthApi
)  : BaseRepository(){

    suspend fun login(
        email: String,
        password: String
    ) = safeApiCall {
        api.login(email, password)
    }

}