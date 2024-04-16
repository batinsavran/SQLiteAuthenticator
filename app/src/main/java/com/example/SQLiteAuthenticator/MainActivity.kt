package com.example.SQLiteAuthenticator

import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R

class MainActivity : AppCompatActivity() {

    lateinit var context: Context
    lateinit var db: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        context = this
        val dbFile = context.getDatabasePath("TestDB")
        Log.i("DBtest", dbFile.exists().toString())
        db = openOrCreateDatabase("TestDB", Context.MODE_PRIVATE, null)

        db.execSQL("CREATE TABLE users (userid INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, surname TEXT, email TEXT, password TEXT)")


        val userId = intent.getIntExtra("userId", -1)
        if (userId > 0) {

        } else {
            val loginIntent = Intent(this, LoginActivity::class.java)
            startActivity(loginIntent)
            finish()
        }
    }
}
