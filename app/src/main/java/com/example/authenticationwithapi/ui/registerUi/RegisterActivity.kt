package com.example.authenticationwithapi.ui.registerUi

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.authenticationwithapi.databinding.ActivityRegisterBinding
import com.example.authenticationwithapi.ui.home.HomeActivity
import com.example.authenticationwithapi.ui.loginUI.LoginActivity
import com.example.authenticationwithapi.ui.viewmodel.LoginViewModel
import com.example.authenticationwithapi.ui.viewmodel.RegistererViewModel
import com.example.authenticationwithapi.util.isPasswordMatch
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private val viewModel: RegistererViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.register.setOnClickListener {
            Register()
        }
        binding.login.setOnClickListener {
            startActivity(Intent(this@RegisterActivity,LoginActivity::class.java))
        }

        lifecycleScope.launchWhenStarted {
            viewModel.registerResult.collect { result ->
                result.onSuccess {
                    Toast.makeText(
                        this@RegisterActivity,
                        "Registration Successful! Token: ${it.token}",
                        Toast.LENGTH_LONG
                    ).show()
                    goToHome(it.token)
                }.onFailure {

                }
            }
        }
    }

    fun goToHome(token: String) {
        val intent = Intent(this@RegisterActivity, HomeActivity::class.java)
        intent.putExtra("USER_TOKEN", token)
        startActivity(intent)
    }

    private fun Register() {
        val email = binding.email.text.toString()
        val password = binding.password.text.toString()
        val confirmPassword = binding.confirmPassword.text.toString()

        if (email.isEmpty()) {
            binding.userEmail.error = "Email cannot be empty"
            return
        }

        if (password.isEmpty()) {
            binding.userPassword.error = "Password cannot be empty"
            return
        }

        if (confirmPassword.isEmpty()) {
            binding.confirmPassword.error = "Confirm Password cannot be empty"
            return
        }

        if (isPasswordMatch(password, confirmPassword)) {
            viewModel.register(email, password)
        } else {
            binding.confirmPassword.error = "Passwords do not match"
        }
    }
}
