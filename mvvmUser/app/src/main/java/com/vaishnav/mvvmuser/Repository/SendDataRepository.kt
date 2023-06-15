package com.vaishnav.mvvmuser.Repository

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class SendDataRepository {

    val reference = Firebase.database

    fun sendDataRepositoryFn(unameRep : String, nameRep : String, bioRep : String, dpUrl : String)
    {
        reference.getReference(unameRep).child("name").setValue(nameRep)
        reference.getReference(unameRep).child("bio").setValue(bioRep)
//        reference.getReference(unameRep).child("dpUrl").setValue(dpUrl)
    }

}