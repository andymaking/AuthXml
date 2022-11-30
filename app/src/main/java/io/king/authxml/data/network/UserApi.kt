package io.king.authxml.data.network

import io.king.authxml.data.responses.User
import retrofit2.http.GET

interface UserApi{
    @GET("api/user")
    suspend fun getUser(): User
}