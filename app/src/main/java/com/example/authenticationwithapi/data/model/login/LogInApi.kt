package com.example.authenticationwithapi.data.model.login

import com.example.authenticationwithapi.data.model.register.RegisterRequest
import com.example.authenticationwithapi.data.model.register.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LogInApi {
    @POST("api/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>


}