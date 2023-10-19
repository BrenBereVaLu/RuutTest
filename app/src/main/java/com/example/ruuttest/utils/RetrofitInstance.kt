package com.example.ruuttest.utils

import android.util.Base64
import com.example.ruuttest.data.requests.AlphaRequest
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitInstance {


    /**
     * Use DI for Retrofit Instance.
     */
    companion object {
        private val BASE_URL = "https://www.alphavantage.co/"

        //private const val BASE_URL = com.epica.membresia_android_kotlin.BuildConfig.BASE_URL
        private val AUTH =
            Base64.encodeToString("application/x-www-form-urlencoded".toByteArray(), Base64.NO_WRAP)

        private val okHttpClient = OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .addInterceptor { chain ->
                val original = chain.request()

                val requestBuilder = original.newBuilder()
                    .addHeader("Content-Type", AUTH)
                    .method(original.method(), original.body())

                val request = requestBuilder.build()
                chain.proceed(request)
            }
            .build()

        private val gson = GsonBuilder()
            .setLenient()
            .create()

        private val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()

        fun getApiAlpha(): AlphaRequest =
            retrofit.create(AlphaRequest::class.java)

    }
}