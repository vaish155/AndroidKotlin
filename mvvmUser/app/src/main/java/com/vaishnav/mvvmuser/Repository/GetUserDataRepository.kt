package com.vaishnav.mvvmuser.Repository

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class GetUserDataRepository {

    private var dbReference = Firebase.database

    fun getNameFactory(unameRef : String): DatabaseReference {
        return dbReference.getReference(unameRef).child("name")
    }

    fun getBioFactory(unameRef : String): DatabaseReference {
        return dbReference.getReference(unameRef).child("bio")
    }

    fun getDpUrlFactory(unameRef : String): DatabaseReference {
        return dbReference.getReference(unameRef).child("dpUrl")
    }

}