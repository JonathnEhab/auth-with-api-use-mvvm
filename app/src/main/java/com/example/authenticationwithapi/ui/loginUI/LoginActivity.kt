package com.example.authenticationwithapi.ui.loginUI

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.authenticationwithapi.databinding.ActivityLoginBinding
import com.example.authenticationwithapi.ui.home.HomeActivity
import com.example.authenticationwithapi.ui.registerUi.RegisterActivity
import com.example.authenticationwithapi.ui.viewmodel.LoginViewModel
import com.example.authenticationwithapi.util.validateFields
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.login.setOnClickListener {
            Login()
        }
        lifecycleScope.launchWhenStarted {
            viewModel.loginResult.collect { result ->
                result.onSuccess {
                    Toast.makeText(this@LoginActivity, "Login Successful! Token: ${it.token}", Toast.LENGTH_LONG).show()
                    goToHome(it.token)
                }.onFailure {

                }
            }
        }
        binding.register.setOnClickListener {
            goToRegister()
        }
    }

    fun goToRegister() {
        startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
    }

    fun goToHome(token: String) {
        val intent = Intent(this@LoginActivity, HomeActivity::class.java)
        intent.putExtra("USER_TOKEN", token)
        startActivity(intent)
    }

    fun Login() {
        val email = binding.email
        val password = binding.password

        if (validateFields(this,email to " Please enter Email",
                password to "Please enter password" )) {
            viewModel.login(email.text.toString(), password.text.toString())
        }
    }
}
