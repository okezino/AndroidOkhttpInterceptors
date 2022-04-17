package com.example.test.interceptors

import com.example.test.UserInput
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull

class RequestModification : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request : Request = chain.request()
        var mediaType = "text/plain; charset=utf-8".toMediaTypeOrNull()
        val newUser = UserInput("okeh.joseph@ymail", "Github4")
        var requestBody : RequestBody = RequestBody.create(mediaType, newUser.toString())
        request = request.newBuilder().post(requestBody).build()

        return chain.proceed(request)
    }
}