package com.example.SQLiteAuthenticator

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var db: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = openOrCreateDatabase("Users", MODE_PRIVATE, null)
    }

    fun proccessLogin(view: View) {
        val loginEmail = binding.editTextTextEmailAddress.text.toString()
        val loginPassword = binding.editTextTextPassword.text.toString()

        if (loginEmail.isEmpty() || loginPassword.isEmpty()) {
            Toast.makeText(this, "Bilgileri eksiksiz giriniz", Toast.LENGTH_SHORT).show()
        } else {
            val result: Cursor =
                db.rawQuery("SELECT * FROM users WHERE email = ?", arrayOf(loginEmail))
            if (result.count > 0) {
                result.moveToFirst()
                val userId = result.getInt(0)
                val name = result.getString(1)
                val surname = result.getString(2)
                val email = result.getString(3)
                val password = result.getString(4)
                Log.d("test", "$userId $name $surname $email $password")
                if (password == loginPassword) {
                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("userid", userId)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Şifre yanlış", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Kullanıcı bulunamadı", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun openRegister(view: View) {
        startActivity(Intent(this, RegisterActivity::class.java))
    }

    fun openLostPassword(view: View) {
        startActivity(Intent(this, LostPasswordActivity::class.java))
    }
}
