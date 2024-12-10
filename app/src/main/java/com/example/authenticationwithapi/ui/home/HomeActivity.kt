package com.example.authenticationwithapi.ui.home

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.authenticationwithapi.R
import com.example.authenticationwithapi.databinding.ActivityHomeBinding
import com.example.authenticationwithapi.util.validateFields
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val token = intent.getStringExtra("USER_TOKEN")
       val tokan= binding.userToken
        validateFields(this, tokan to "sdnasjdnb")

    }
}