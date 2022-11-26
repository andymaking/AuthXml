package io.king.authxml.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class RemoteDataSource{
    companion object{
        private const val BASE_URL = "https://save-life-project.herokuapp.com/"
    }

    fun<API> buildApi(
        api: Class<API>
    ) : API {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(api)
    }
}