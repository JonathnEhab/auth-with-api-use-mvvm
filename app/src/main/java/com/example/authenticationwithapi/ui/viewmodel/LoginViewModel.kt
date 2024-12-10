package com.example.authenticationwithapi.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.authenticationwithapi.data.model.login.LoginResponse
import com.example.authenticationwithapi.data.model.register.RegisterResponse
import com.example.authenticationwithapi.ui.repo.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val repository: LoginRepository) : ViewModel() {

    private val _loginResult =
        MutableStateFlow<Result<LoginResponse>>(Result.failure(Exception("Not initialized")))
    val loginResult: StateFlow<Result<LoginResponse>> = _loginResult


    fun login(email: String, password: String) {
        viewModelScope.launch {
            _loginResult.value = repository.login(email, password)
        }
    }


}
