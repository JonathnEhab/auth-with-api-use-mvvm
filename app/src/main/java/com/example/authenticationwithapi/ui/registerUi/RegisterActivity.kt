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
import com.example.authenticationwithapi.ui.viewmodel.RegistererViewModel
import com.example.authenticationwithapi.util.validateFields
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
            startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
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
        val email = binding.email
        val password = binding.password
        val confirmPassword = binding.confirmPassword
        if (validateFields(this,
                email to " Please enter Email",
                password to "Please enter password",
                confirmPassword to "Please confirm password",
                passwordPair =Triple(password ,
                    confirmPassword ,"Passwords do not match")))
        { viewModel.register(email.text.toString(), password.text.toString()) }
    }
}
