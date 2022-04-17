package com.example.test

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("login")
    fun loginUsers(@Body userInput: UserInput) : Call<UserResponse>
}