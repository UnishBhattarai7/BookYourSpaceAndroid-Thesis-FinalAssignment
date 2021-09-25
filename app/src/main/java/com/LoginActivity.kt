package com

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.unish.bookyourspace.R
import com.unish.bookyourspace.RegisterActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var tvsignup : TextView
    private lateinit var btnlogin : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        tvsignup = findViewById(R.id.tvsignup)
        btnlogin = findViewById(R.id.btnlogin)

        tvsignup.setOnClickListener {
            startActivity(Intent(this,RegisterActivity::class.java))

        }

        btnlogin.setOnClickListener {
            Toast.makeText(this, "Welcome To BookYourSpace", Toast.LENGTH_SHORT).show()

            startActivity(Intent(this,DashboardActivity::class.java))

        }
    }
}