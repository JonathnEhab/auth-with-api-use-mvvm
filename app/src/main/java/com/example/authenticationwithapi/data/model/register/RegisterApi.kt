package com.example.authenticationwithapi.data.model.register

import com.example.authenticationwithapi.data.model.login.LoginRequest
import com.example.authenticationwithapi.data.model.login.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface RegisterApi {
    @POST("api/register")
    suspend fun register(@Body registerRequest: RegisterRequest): Response<RegisterResponse>

}