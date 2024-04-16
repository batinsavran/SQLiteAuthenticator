package com.example.SQLiteAuthenticator

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
    }

    fun register(view: View){
        val nameView=findViewById<EditText>(R.id.editTextText)
        val name=findViewById<EditText>(R.id.editTextText).text.toString()
        val surname=findViewById<EditText>(R.id.editTextText2).text.toString()
        val email=findViewById<EditText>(R.id.editTextTextEmailAddress2).text.toString()
        val password=findViewById<EditText>(R.id.editTextTextPassword2).text.toString()
        val password_repeat=findViewById<EditText>(R.id.editTextTextPassword3).text.toString()

        if (name.equals("") || surname.equals("") || email.equals("") || password.equals("") || password_repeat.equals("") ){
            Toast.makeText(this@RegisterActivity, "Tüm Bilgileri Eksiksiz Giriniz.", Toast.LENGTH_SHORT).show()
        }else{
            if (password.equals(password_repeat)){
                val passwordLength = password.length
                if (passwordLength >= 8){

                }else{
                    Toast.makeText(this@RegisterActivity, "Şifreniz en az 8 karakter olmalı.", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this@RegisterActivity, "Girdiğiniz şifreler birbirini tutmuyor.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun openLogin(view: View){
        startActivity(Intent(this, LoginActivity::class.java))
    }
}