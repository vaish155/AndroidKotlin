package com.vaishnav.mvvmuser.Repository

import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class signInRepository {

    private var auth: FirebaseAuth = Firebase.auth

    fun signInUser(emailRep : String, passwordRep : String)
    {
        auth.signInWithEmailAndPassword(emailRep, passwordRep).addOnSuccessListener {
            Log.e(TAG, "Login Successful")
        }.addOnFailureListener{
            Log.e(TAG, "Login UnSuccessful")
        }
    }

}