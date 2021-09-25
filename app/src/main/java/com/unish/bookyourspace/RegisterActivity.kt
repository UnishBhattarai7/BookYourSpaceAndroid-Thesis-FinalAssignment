package com.unish.bookyourspace

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.LoginActivity

class RegisterActivity : AppCompatActivity() {

    private lateinit var btnregister : Button
    private lateinit var tvsignin : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        btnregister = findViewById(R.id.btnregister)
        tvsignin = findViewById(R.id.tvsignin)

        btnregister.setOnClickListener {
            Toast.makeText(this, "User Registered", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, LoginActivity::class.java))

        }

        tvsignin.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))

        }

    }
}