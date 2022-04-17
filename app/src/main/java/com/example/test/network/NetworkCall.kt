package com.example.test.network

import com.example.test.BASE_URL
import com.example.test.LogOutInterceptor
import com.example.test.LoginInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkCall {
    val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    val local = LoginInterceptor()
    val locale = LogOutInterceptor()


    val okHttpClient = OkHttpClient()
        .newBuilder()
        .addInterceptor(local)
        .addInterceptor(logging)
        .addNetworkInterceptor(locale)
        .build()

    val retrofit: Retrofit.Builder by lazy {
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
    }

    val loginApiService: ApiService by lazy {
        retrofit.build().create(ApiService::class.java)
    }
}