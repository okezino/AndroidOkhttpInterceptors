package com.example.test.network

import com.example.test.BASE_URL
import com.example.test.interceptors.CacheInterceptors
import com.example.test.interceptors.ForcedCacheInterceptors
import com.example.test.interceptors.RequestModification
import com.example.test.interceptors.ResponseModification
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkCall {
    private val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    private val requestModification = RequestModification()
    private val responseModification = ResponseModification()
    private val cacheInterceptors = CacheInterceptors()
    private val forcedCacheInterceptors = ForcedCacheInterceptors()


    private val okHttpClient = OkHttpClient()
        .newBuilder()
        .addNetworkInterceptor(logging)
        .addInterceptor(requestModification)
        .addInterceptor(forcedCacheInterceptors)
        .addNetworkInterceptor(responseModification)
        .addNetworkInterceptor(cacheInterceptors)
        .build()

    private val retrofit: Retrofit.Builder by lazy {
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
    }

    val loginApiService: ApiService by lazy {
        retrofit.build().create(ApiService::class.java)
    }
}