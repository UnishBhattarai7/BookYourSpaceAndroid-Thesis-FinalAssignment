package com

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.unish.bookyourspace.R
import com.unish.bookyourspace.RegisterActivity

class DashboardActivity : AppCompatActivity() {
    private lateinit var btnsearch : ImageView
    private lateinit var btnmap : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        btnsearch = findViewById(R.id.btnsearch)
        btnmap = findViewById(R.id.btnmap)


        btnsearch.setOnClickListener {

            startActivity(Intent(this, SearchDashboard::class.java))

        }
        btnmap.setOnClickListener {
            startActivity(Intent(this, Map_Dashboard::class.java))

        }
    }
}