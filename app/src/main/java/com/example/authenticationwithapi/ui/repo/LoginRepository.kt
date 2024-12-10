package com.example.authenticationwithapi.ui.repo

import com.example.authenticationwithapi.data.model.login.LogInApi
import com.example.authenticationwithapi.data.model.login.LoginRequest
import com.example.authenticationwithapi.data.model.login.LoginResponse
import com.example.authenticationwithapi.data.model.register.RegisterRequest
import com.example.authenticationwithapi.data.model.register.RegisterResponse
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class LoginRepository @Inject constructor(private val api: LogInApi){
    suspend fun login(email: String, password: String): Result<LoginResponse> {
        return try {
            val response = api.login(LoginRequest(email, password))
            if (response.isSuccessful) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Login failed"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }


}