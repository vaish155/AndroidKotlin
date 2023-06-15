package com.vaishnav.mvvmuser.Repository

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vaishnav.mvvmuser.ViewModel.fbViewModel

class signInFactory(private val SignInRepository : signInRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(fbViewModel::class.java))
        {
            return fbViewModel(SignInRepository) as T
        }
        throw IllegalArgumentException("Unknown VM Class")
    }
}