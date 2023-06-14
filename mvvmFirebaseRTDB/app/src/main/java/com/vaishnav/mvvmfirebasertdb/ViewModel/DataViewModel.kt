package com.vaishnav.mvvmfirebasertdb.ViewModel

import androidx.lifecycle.ViewModel
import com.google.firebase.database.DatabaseReference
import com.vaishnav.mvvmfirebasertdb.Repository.DataRepository

class DataViewModel(private val dataRepository: DataRepository) : ViewModel() {

    fun updateDatafromModel(value_fromModel : String)
    {
        dataRepository.updateData(value_fromModel)
    }

    fun getDatafromModel() : DatabaseReference
    {
        return dataRepository.getData()
    }

}