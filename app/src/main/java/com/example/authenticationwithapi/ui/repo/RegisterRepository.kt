package com.example.authenticationwithapi.ui.repo

import com.example.authenticationwithapi.data.model.login.LogInApi
import com.example.authenticationwithapi.data.model.login.LoginRequest
import com.example.authenticationwithapi.data.model.login.LoginResponse
import com.example.authenticationwithapi.data.model.register.RegisterApi
import com.example.authenticationwithapi.data.model.register.RegisterRequest
import com.example.authenticationwithapi.data.model.register.RegisterResponse
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class RegisterRepository @Inject constructor(private val api: RegisterApi){

    suspend fun register(email: String, password: String): Result<RegisterResponse> {
        return try {
            val response = api.register(RegisterRequest(email, password))
            if (response.isSuccessful) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Registration failed"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}