package com.example.SQLiteAuthenticator

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R

class LoginActivity : AppCompatActivity() {

    lateinit var db: SQLiteDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        db = openOrCreateDatabase("Users", MODE_PRIVATE, null)
    }

    fun proccessLogin(view: View){
        val login_email = findViewById<EditText>(R.id.editTextTextEmailAddress).text.toString()
        val login_password = findViewById<EditText>(R.id.editTextTextPassword).text.toString()

        if (login_email.equals("")||login_password.equals("")){
            Toast.makeText(this, "Bilgileri eksiksiz giriniz", Toast.LENGTH_SHORT).show()
        }else{
            val result: Cursor = db.rawQuery("SELECT * FROM users WHERE email = '$login_email'", null)
            if (result.count>0){
                result.moveToFirst()
                val userid=result.getInt(0)
                val name=result.getString(1)
                val surname=result.getString(2)
                val email=result.getString(3)
                val password=result.getString(4)
                Log.d("test", "$userid $name $surname $email $password")
                if (password.equals(login_password)){
                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("userid", userid)
                    startActivity(intent)
                }else{
                    Toast.makeText(this, "Şifre yanlış", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this, "Kullanıcı bulunamadı", Toast.LENGTH_SHORT).show()
            }
        }
    }
    fun openRegister(view: View){
        startActivity(Intent(this, RegisterActivity::class.java))
    }

    fun openLostPassword(view: View){
        startActivity(Intent(this, LostPasswordActivity::class.java))
    }
}
