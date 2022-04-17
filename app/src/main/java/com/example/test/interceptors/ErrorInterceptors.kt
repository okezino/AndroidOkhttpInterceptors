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
                response =  response.newBuilder()
                     .message("").build()
             }
             401 -> {
               response =   response.newBuilder()
                     .message("").build()
             }
             403 -> {
                response =  response.newBuilder()
                     .message("").build()
             }
            404 -> {
               response =  response.newBuilder()
                    .message("").build()
            }
            else -> {
                response = response.newBuilder()
                .message("").build()
            }

        }

        return response


    }
}