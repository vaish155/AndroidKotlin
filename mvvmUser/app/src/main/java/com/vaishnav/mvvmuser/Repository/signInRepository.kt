package com.vaishnav.mvvmuser.Repository

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.vaishnav.mvvmuser.View.HomeActivity

class signInRepository {

    private var auth: FirebaseAuth = Firebase.auth

    fun signInUser(emailRep : String, passwordRep : String, context: Context)
    {
        auth.signInWithEmailAndPassword(emailRep, passwordRep).addOnSuccessListener {
            Log.e(TAG, "Login Successful")
            Toast.makeText(context, "Successful Login", Toast.LENGTH_SHORT).show()
            startActivityRepo(context, emailRep)
        }.addOnFailureListener{
            Log.e(TAG, "Login UnSuccessful")
        }
    }

    private fun startActivityRepo(context: Context, emailid: String) {
        val intent = Intent(context, HomeActivity::class.java)
        intent.putExtra("username",emailid.substringBefore('@'))
        context.startActivity(intent)
    }

}