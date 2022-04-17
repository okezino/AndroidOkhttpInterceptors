package com.example.test.interceptors

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class ErrorInterceptors : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request : Request = chain.request()
        var response : Response = chain.proceed(request)

        when(response.code){
             400 ->  {
                 response.newBuilder()
                     .message("")
             }
             401 -> {
                 response.newBuilder()
                     .message("")
             }
             403 -> {
                 response.newBuilder()
                     .message("")
             }
            404 -> {
                response.newBuilder()
                    .message("")
            }
            else -> {   response.newBuilder()
                .message("")
            }

        }

        return response


    }
}