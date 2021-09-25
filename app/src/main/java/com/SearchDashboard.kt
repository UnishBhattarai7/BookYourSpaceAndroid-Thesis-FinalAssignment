package com

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.unish.bookyourspace.BookParkingActivity
import com.unish.bookyourspace.R

class SearchDashboard : AppCompatActivity() {

    private lateinit var book1 : TextView
    private lateinit var book2 : TextView
    private lateinit var book3 : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_dashboard)


        book1 = findViewById(R.id.book1)
        book2 = findViewById(R.id.book2)
        book3 = findViewById(R.id.book3)


        book1.setOnClickListener {
            startActivity(Intent(this,BookParkingActivity::class.java))

        }
        book2.setOnClickListener {
            startActivity(Intent(this,BookParkingActivity::class.java))

        }
        book3.setOnClickListener {
            startActivity(Intent(this,BookParkingActivity::class.java))

        }


    }
}