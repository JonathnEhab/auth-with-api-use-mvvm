package com.example.authenticationwithapi.util


fun isPasswordMatch(password: String, confirmPassword: String): Boolean {
    return password == confirmPassword
}