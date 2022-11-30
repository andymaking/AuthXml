package io.king.authxml.data.repository

import io.king.authxml.data.UserPreferences
import io.king.authxml.data.network.AuthApi
import io.king.authxml.data.network.UserApi

class UserRepository(
    private val api: UserApi
)  : BaseRepository(){

    suspend fun getUser() = safeApiCall {
        api.getUser()
    }

}