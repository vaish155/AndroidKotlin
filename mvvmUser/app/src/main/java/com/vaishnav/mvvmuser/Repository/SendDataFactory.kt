package com.vaishnav.mvvmuser.Repository

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vaishnav.mvvmuser.ViewModel.fbSendDataVM

class SendDataFactory(private val sendDataRepository: SendDataRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(fbSendDataVM::class.java))
        {
            return fbSendDataVM(sendDataRepository) as T
        }
        throw IllegalArgumentException("Wrong VM Supplied")
    }
}