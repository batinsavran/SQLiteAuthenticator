package com.example.SQLiteAuthenticator

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var db: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = this.openOrCreateDatabase("TestDB", AppCompatActivity.MODE_PRIVATE, null)
    }

    fun register(view: View) {
        val name = binding.editTextText.text.toString()
        val surname = binding.editTextText2.text.toString()
        val email = binding.editTextTextEmailAddress2.text.toString()
        val password = binding.editTextTextPassword2.text.toString()
        val passwordRepeat = binding.editTextTextPassword3.text.toString()

        if (name.isEmpty() || surname.isEmpty() || email.isEmpty() || password.isEmpty() || passwordRepeat.isEmpty()) {
            Toast.makeText(
                this@RegisterActivity,
                "Tüm Bilgileri Eksiksiz Giriniz.",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            val result: Cursor = db.rawQuery("SELECT * FROM users WHERE email = ?", arrayOf(email))
            if (result.count > 0) {
                Toast.makeText(
                    this@RegisterActivity,
                    "Bu mail adresi zaten kayıtlı.",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                if (password == passwordRepeat) {
                    if (password.length >= 3) {
                        db.execSQL("INSERT INTO users (name, surname, email, password) VALUES ('$name', '$surname', '$email', '$password')")
                        Toast.makeText(this@RegisterActivity, "Kayıt Başarılı.", Toast.LENGTH_SHORT)
                            .show()
                        startActivity(Intent(this, LoginActivity::class.java))
                    } else {
                        Toast.makeText(
                            this@RegisterActivity,
                            "Şifreniz en az 3 karakter olmalı.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    Toast.makeText(
                        this@RegisterActivity,
                        "Girdiğiniz şifreler birbirini tutmuyor.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    fun openLogin(view: View) {
        startActivity(Intent(this, LoginActivity::class.java))
    }
}
