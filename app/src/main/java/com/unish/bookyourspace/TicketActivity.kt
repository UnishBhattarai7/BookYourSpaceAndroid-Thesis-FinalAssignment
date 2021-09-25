package com.unish.bookyourspace

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.pdf.PdfDocument
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.app.ActivityCompat
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class TicketActivity : AppCompatActivity() {

    private lateinit var llpdf : LinearLayout
    private lateinit var btnDownload: Button
    private lateinit var btnQR : Button
//    private lateinit var btnSubmit : Button

    private val permissions = arrayOf(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.SEND_SMS
    )
    private lateinit var bitmap: Bitmap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticket)

        btnDownload = findViewById(R.id.btnDownload)
        btnQR = findViewById(R.id.btnQR)
//        btnSubmit = findViewById(R.id.btnSubmit)
        llpdf = findViewById(R.id.llpdf)

        btnQR.setOnClickListener {
            openCamera()
        }

//        btnSubmit.setOnClickListener {
//            Toast.makeText(this, "Thank You For Choosing BookYourSpace. Please Travel Safe", Toast.LENGTH_SHORT).show()
//        }

        btnDownload.setOnClickListener(View.OnClickListener {
            Log.d("size", " " + llpdf.getWidth() + "  " + llpdf.getWidth())
            bitmap = loadBitmapFromView(llpdf, llpdf.getWidth(), llpdf.getHeight())
            createPdf()
        })

        if (!hasPermissions()) {
            requestPermission()
        }
    }

    private var REQUEST_CAMERA_CODE = 1

    private fun openCamera() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(cameraIntent, REQUEST_CAMERA_CODE)
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            this,
            permissions, 12
        )
    }

    private fun hasPermissions(): Boolean {
        var hasPermission = true
        for (permission in permissions) {
            if (ActivityCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                hasPermission = false
            }
        }
        return hasPermission
    }

    private fun createPdf() {
        val wm = getSystemService(WINDOW_SERVICE) as WindowManager
        val displaymetrics = DisplayMetrics()
        this.windowManager.defaultDisplay.getMetrics(displaymetrics)
        val hight = displaymetrics.heightPixels.toFloat()
        val width = displaymetrics.widthPixels.toFloat()
        val convertHighet = hight.toInt()
        val convertWidth = width.toInt()

        val document = PdfDocument()
        val pageInfo = PdfDocument.PageInfo.Builder(convertWidth, convertHighet, 1).create()
        val page = document.startPage(pageInfo)
        val canvas = page.canvas
        val paint = Paint()
        canvas.drawPaint(paint)
        bitmap = Bitmap.createScaledBitmap(bitmap!!, convertWidth, convertHighet, true)
        paint.color = Color.BLUE
        canvas.drawBitmap(bitmap, 0f, 0f, null)
        document.finishPage(page)

        // write the document content
        val targetPdf =
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
        val filePath: File
        filePath = File(targetPdf, "BookYourSpace.pdf")
        try {
            with(document) { writeTo(FileOutputStream(filePath)) }
        } catch (e: IOException) {
            e.printStackTrace()
            Toast.makeText(this, "$e", Toast.LENGTH_LONG).show()
        }

        // close the document
        document.close()
        Toast.makeText(this, "Ticket has been downloaded", Toast.LENGTH_LONG).show()
    }

    companion object {
        fun loadBitmapFromView(v: View?, width: Int, height: Int): Bitmap {
            val b = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
            val c = Canvas(b)
            v!!.draw(c)
            return b
        }
    }
}