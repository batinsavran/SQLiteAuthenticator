package com.example.SQLiteAuthenticator

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    fun openRegister(view: View){
        startActivity(Intent(this, RegisterActivity::class.java))
    }

    fun openLostPassword(view: View){
        startActivity(Intent(this, LostPasswordActivity::class.java))
    }
}
