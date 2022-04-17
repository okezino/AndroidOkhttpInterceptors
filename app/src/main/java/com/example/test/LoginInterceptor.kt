package com.example.test

import android.util.Log
import com.google.gson.Gson
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull

private const val TAG = "LoginInterceptor"
class LoginInterceptor : Interceptor {
    var mediaType = "text/plain; charset=utf-8".toMediaTypeOrNull();
    override fun intercept(chain: Interceptor.Chain): Response {
        var request : Request = chain.request()
        val newUser = UserInput("okeh.joseph@yamail", "city")
        var requestBody : RequestBody = RequestBody.create(mediaType,newUser.toString())

        request = request.newBuilder().method(request.method, requestBody).build()
      //  Log.d(TAG,request.body.toString())
        return chain.proceed(request)
    }
}


class LogOutInterceptor : Interceptor {
    private  val TAGE = "LogOutInterceptor"
    var mediaType = "text/plain; charset=utf-8".toMediaTypeOrNull();
    override fun intercept(chain: Interceptor.Chain): Response {
        var userResponse = UserResponse("gfcdcsxszz")
        var resBody  = ResponseBody.create(mediaType,Gson().toJson(userResponse))
        var request = chain.request()
        var res = chain.proceed(request).newBuilder().code(200).body(resBody)

        return res.build()
    }
}