package com.vaishnav.mvvmfirebasertdb.Repository

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class DataRepository {

    private val database = FirebaseDatabase.getInstance()
    private val reference = database.getReference("Data")

    fun updateData(value: String)
    {
        reference.setValue(value)
    }

    fun getData() : DatabaseReference
    {
        return reference
    }

}