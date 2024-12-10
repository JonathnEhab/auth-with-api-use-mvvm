package com.example.authenticationwithapi.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.authenticationwithapi.data.model.register.RegisterResponse
import com.example.authenticationwithapi.ui.repo.RegisterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistererViewModel @Inject constructor(private val repository: RegisterRepository) :
    ViewModel() {

    private val _registerResult =
        MutableStateFlow<Result<RegisterResponse>>(Result.failure(Exception("Not initialized")))
    val registerResult: StateFlow<Result<RegisterResponse>> = _registerResult


    fun register(email: String, password: String) {
        viewModelScope.launch {
            _registerResult.value = repository.register(email, password)
        }
    }
}
