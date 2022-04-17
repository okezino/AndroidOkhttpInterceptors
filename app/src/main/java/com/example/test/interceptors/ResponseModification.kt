package com.example.test.interceptors

import com.example.test.UserResponse
import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Response
import okhttp3.ResponseBody

class ResponseModification : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val mediaType = "text/plain; charset=utf-8".toMediaTypeOrNull()
        val userResponse = UserResponse(token = "hasfhgfJGSVGSvzvgzhvcgvgczgcvzg")
        val responseBody = ResponseBody.create(mediaType, Gson().toJson(userResponse))
        val request = chain.request()



        var response = chain.proceed(request)
            .newBuilder()
            .code(401)
            .body(responseBody)

        return response.build()
    }
}