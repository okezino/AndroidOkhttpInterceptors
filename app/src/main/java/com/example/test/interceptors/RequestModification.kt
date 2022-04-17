package com.example.test.interceptors

import okhttp3.Interceptor
import okhttp3.Response

class RequestModification : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        TODO("Not yet implemented")
    }
}