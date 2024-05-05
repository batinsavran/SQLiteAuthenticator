package com.example.SQLiteAuthenticator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityLostPasswordBinding
import com.example.myapplication.databinding.ActivityRegisterBinding

class LostPasswordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLostPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLostPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}