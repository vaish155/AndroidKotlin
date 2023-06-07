package com.example.imageclickupload

import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.io.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    lateinit var clickedPhotoId : ImageView
    lateinit var clickPhotoBtnId : Button
    lateinit var uploadImageBtnId : Button
    lateinit var saveImageBtnId : Button
    val REQUEST_IMAGE_CAPTURE = 100
    val REQUEST_PICK_IMAGE = 101

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        clickedPhotoId = findViewById(R.id.clickedPhotoId)
        clickPhotoBtnId = findViewById(R.id.clickPhotoBtnId)
        uploadImageBtnId = findViewById(R.id.uploadImageBtnId)
        saveImageBtnId = findViewById(R.id.saveImageBtnId)

        clickPhotoBtnId.setOnClickListener{
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            try {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            }catch (e : ActivityNotFoundException)
            {
                Toast.makeText(this, "Error: "+e.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        }

        uploadImageBtnId.setOnClickListener{
            pickImageGallery()
        }

        saveImageBtnId.setOnClickListener {
            val permission = android.Manifest.permission.WRITE_EXTERNAL_STORAGE
            val grant = PackageManager.PERMISSION_GRANTED
            if (ContextCompat.checkSelfPermission(this, permission) != grant) {
                ActivityCompat.requestPermissions(this, arrayOf(permission), 1)
            }

            if (ContextCompat.checkSelfPermission(this, permission) == grant)
            {
                val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss")
                val current_time : String = LocalDateTime.now().format(formatter)


                clickedPhotoId.isDrawingCacheEnabled = true
                clickedPhotoId.buildDrawingCache()

                val bitmap = Bitmap.createBitmap(clickedPhotoId.drawingCache)
                clickedPhotoId.isDrawingCacheEnabled = false

                saveBitmapToFile(this, bitmap, current_time+".png")

            }



        }

    }

    fun saveBitmapToFile(context: Context, bitmap: Bitmap, fileName: String) {
        // Get the directory for the app's private pictures directory
        val picturesDirectory = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)

        // Create a new file in the pictures directory
        val file = File(picturesDirectory, fileName)

        var outputStream: OutputStream? = null

        try {
            outputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
            Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show()
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            try {
                outputStream?.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    private fun pickImageGallery() {
        val imagepickIntent = Intent(Intent.ACTION_PICK)
        imagepickIntent.type = "image/"
        startActivityForResult(imagepickIntent, REQUEST_PICK_IMAGE)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK)
        {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            clickedPhotoId.setImageBitmap(imageBitmap)
        }
        else if (requestCode == REQUEST_PICK_IMAGE && resultCode == RESULT_OK)
        {
            clickedPhotoId.setImageURI(data?.data)
        }
        else
        {
            super.onActivityResult(requestCode, resultCode, data)
        }

    }
}