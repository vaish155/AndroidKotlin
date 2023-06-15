package com.vaishnav.mvvmuser.Repository

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vaishnav.mvvmuser.ViewModel.FbGetDataVM

class GetUserDataFactory(private val getUserDataRepository: GetUserDataRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(FbGetDataVM::class.java))
        {
            return FbGetDataVM(getUserDataRepository) as T
        }
        throw IllegalArgumentException("Unknown VM Class")
    }
}