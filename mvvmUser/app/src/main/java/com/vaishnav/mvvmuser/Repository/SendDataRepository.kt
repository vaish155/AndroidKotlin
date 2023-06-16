package com.vaishnav.mvvmuser.Repository

import android.graphics.Bitmap
import android.widget.ImageView
import androidx.core.graphics.drawable.toBitmap
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import java.io.ByteArrayOutputStream

class SendDataRepository {

    val reference = Firebase.database
    var storageReference = Firebase.storage.reference.child("MvvmUserApp")

    fun sendDataRepositoryFn(unameRep : String, nameRep : String, bioRep : String, dpIvRep : ImageView)
    {
        val imageBitmap =imagetoBitmap(dpIvRep)
        val baos = ByteArrayOutputStream()
        imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, baos)
        val imaegData = baos.toByteArray()

        reference.getReference(unameRep).child("name").setValue(nameRep)
        reference.getReference(unameRep).child("bio").setValue(bioRep)

        val uploadTask = storageReference.child(unameRep+".png").putBytes(imaegData)
        uploadTask.addOnSuccessListener {
            val downloadUrl = it.metadata?.reference?.downloadUrl
            downloadUrl?.addOnSuccessListener {uri->
                val imageUrl = uri.toString()
                reference.getReference(unameRep).child("dpUrl").setValue(imageUrl)
            }
        }

    }


    fun imagetoBitmap(image : ImageView) : Bitmap
    {
        val drawable = image.drawable
        val bitmap = drawable.toBitmap()
        return bitmap
    }

}