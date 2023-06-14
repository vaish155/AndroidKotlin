package com.vaishnav.mvvmfirebasertdb.Repository

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vaishnav.mvvmfirebasertdb.ViewModel.DataViewModel

class DataFactory(private val dataRepository: DataRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(DataViewModel::class.java))
        {
            return DataViewModel(dataRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}