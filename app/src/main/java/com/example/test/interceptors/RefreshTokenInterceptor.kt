package com.example.test.interceptors

import okhttp3.Interceptor
import okhttp3.Response

class RefreshTokenInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val getToken = "sharedPreference"
        var request = chain.request()
        request = request.newBuilder().header("Authorization", getToken).build()

        val response = chain.proceed(request)

        if (response.code == 401) {
            val newToken = ""// fetch from a different call
            var newRequest = chain.request()
            newRequest = newRequest.newBuilder().addHeader("Authorization", newToken).build()
            return chain.proceed(newRequest)

        }
        return response
    }
}