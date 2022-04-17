package com.example.test.interceptors

import okhttp3.Interceptor
import okhttp3.Response

class AuthTokenInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request.newBuilder().header("Authorization", "New Token from shared Preference").build()
        return chain.proceed(request)
    }
}