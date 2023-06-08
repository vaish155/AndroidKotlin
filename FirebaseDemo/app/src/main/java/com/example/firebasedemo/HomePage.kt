package com.example.firebasedemo

import android.content.ActivityNotFoundException
import android.content.ContentValues.TAG
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.storage.FirebaseStorage
import java.io.ByteArrayOutputStream

@Suppress("DEPRECATION")
class HomePage : AppCompatActivity() {

    private lateinit var newImagesList :ArrayList<ImagesClass>
    private lateinit var ImagesRecycHomeId : RecyclerView
    private lateinit var uploadImageHomeId : ImageView
    private lateinit var uname : String

    private val REQUEST_PICK_IMAGE = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        val uploadImageBtnHomeId : Button = findViewById(R.id.uploadImageBtnHomeId)
        uploadImageHomeId = findViewById(R.id.uploadImageHomeId)

        val bundle: Bundle? = intent.extras
        uname = bundle?.get("Uname") as String
        uname = uname.substringBefore("@")

        val storageReference = FirebaseStorage.getInstance().reference.child(uname)

        newImagesList = arrayListOf<ImagesClass>()

        ImagesRecycHomeId = findViewById(R.id.ImagesRecycHomeId)
        ImagesRecycHomeId.layoutManager = LinearLayoutManager(this)
        ImagesRecycHomeId.setHasFixedSize(true)

        storageReference.listAll().addOnSuccessListener { listResult ->
            for(file in listResult.items)
            {
                val fileName = file.name
                file.downloadUrl.addOnSuccessListener { uri->
                    newImagesList.add(ImagesClass(uri))
                    Log.e(TAG, "Url: "+uri.toString())
                }.addOnFailureListener{
                    Toast.makeText(this, "Couldn't retrieve url", Toast.LENGTH_SHORT).show()
                }

            }
        }.addOnFailureListener{
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
        }

        ImagesRecycHomeId.adapter = ImagesAdapter(this, newImagesList)

        uploadImageBtnHomeId.setOnClickListener{
            val imagepickIntent = Intent(Intent.ACTION_PICK)
            imagepickIntent.type = "image/"
            startActivityForResult(imagepickIntent, REQUEST_PICK_IMAGE)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_PICK_IMAGE && resultCode == RESULT_OK)
        {
            uploadImageHomeId.setImageURI(data?.data)
            val drawable = uploadImageHomeId.drawable
            if (drawable is BitmapDrawable) {
                val bitmap: Bitmap = drawable.bitmap
                val baos = ByteArrayOutputStream()
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
                val data: ByteArray = baos.toByteArray()

                val storage = FirebaseStorage.getInstance()
                val storageRef = storage.reference
                val imagesRef = storageRef.child(uname)
                var imagename = "UploadImage.jpg"
                val imageRef = imagesRef.child(imagename)

                val uploadTask = imageRef.putBytes(data)
                uploadTask.addOnSuccessListener {
                    Toast.makeText(this, "Uploaded", Toast.LENGTH_SHORT).show()
                }.addOnFailureListener{
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                }
            }
        }
        else
        {
            super.onActivityResult(requestCode, resultCode, data)
        }

    }
}