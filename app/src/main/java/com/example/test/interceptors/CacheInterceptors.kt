package com.example.test.interceptors

import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.util.concurrent.TimeUnit

class CacheInterceptors : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request : Request = chain.request()
        val response : Response = chain.proceed(request)
        val cacheControl : CacheControl = CacheControl.Builder()
                                          .maxAge(4, TimeUnit.DAYS).build()
        val res =  response.newBuilder().header("Cache-Control", cacheControl.toString())
        return res.build()
    }
}