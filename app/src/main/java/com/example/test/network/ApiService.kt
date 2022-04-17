package com.example.test.network

import com.example.test.UserInput
import com.example.test.UserResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("login")
    fun loginUsers(@Body userInput: UserInput) : Call<UserResponse>
}