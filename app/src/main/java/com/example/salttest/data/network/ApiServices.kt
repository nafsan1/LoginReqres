package com.example.salttest.data.network

import com.example.salttest.data.model.LoginRequest
import com.example.salttest.data.model.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST


interface ApiServices {

    @POST("api/login")
    suspend fun getLogin(
        @Body loginRequest: LoginRequest
    ): LoginResponse
}