package io.king.authxml.data.network

import io.king.authxml.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteDataSource{
    companion object{
        private const val BASE_URL = "https://save-life-project.herokuapp.com/"
    }

    fun<API> buildApi(
        api: Class<API>
    ) : API {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(
                OkHttpClient.Builder().also { client ->
                    if (BuildConfig.DEBUG){
                        val logging = HttpLoggingInterceptor();
                        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                        client.addInterceptor(logging)
                    }
                }.build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(api)
    }
}