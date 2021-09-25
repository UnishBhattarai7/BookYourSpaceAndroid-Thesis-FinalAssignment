package com.unish.bookyourspace

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.DashboardActivity
import java.text.SimpleDateFormat
import java.util.*

class BookParkingActivity : AppCompatActivity() {

    private lateinit var ETDate: TextView
    private lateinit var ETDateExit: TextView
    private lateinit var TVtime: TextView
    private lateinit var TVtimeExit: TextView
    private lateinit var btnconfirm : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_parking)

        ETDate = findViewById(R.id.ETDate)
        ETDateExit = findViewById(R.id.ETDateExit)
        TVtime = findViewById(R.id.TVTime)
        TVtimeExit = findViewById(R.id.TVTimeExit)
        btnconfirm = findViewById(R.id.btnconfirm)

        val cal = Calendar.getInstance()
        val hour = cal.get(Calendar.HOUR_OF_DAY)
        val min = cal.get(Calendar.MINUTE)


        TVtime.setOnClickListener {
            val timePickerDialog = TimePickerDialog(
                this,
                TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                    TVtime.text = "Time : " + hourOfDay + " : " + minute

                },
                hour,
                min,
                false
            )
            timePickerDialog.show()
        }
        TVtimeExit.setOnClickListener {
            val timePickerDialog = TimePickerDialog(
                this,
                TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                    TVtimeExit.text = "Time : " + hourOfDay + " : " + minute

                },
                hour,
                min,
                false
            )
            timePickerDialog.show()
        }


        val myCalendar = Calendar.getInstance()

        val datePicker = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, monthOfYear)
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            updateTable(myCalendar)
        }
        ETDate.setOnClickListener {
            DatePickerDialog(
                this, datePicker, myCalendar.get(Calendar.YEAR), myCalendar.get(
                    Calendar.MONTH
                ),
                myCalendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        ETDateExit.setOnClickListener {
            DatePickerDialog(
                this, datePicker, myCalendar.get(Calendar.YEAR), myCalendar.get(
                    Calendar.MONTH
                ),
                myCalendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        btnconfirm.setOnClickListener {
            startActivity(Intent(this, TicketActivity::class.java))

        }
    }

    private fun updateTable(myCalendar: Calendar) {
        val myFormat = "dd-MM-yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.UK)
        ETDate.setText(sdf.format(myCalendar.time))
        ETDateExit.setText(sdf.format(myCalendar.time))
    }



}
