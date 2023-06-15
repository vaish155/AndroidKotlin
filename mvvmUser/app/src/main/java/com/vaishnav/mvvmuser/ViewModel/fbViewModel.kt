package com.vaishnav.mvvmuser.ViewModel

import androidx.lifecycle.ViewModel
import com.vaishnav.mvvmuser.Repository.signInRepository

class fbViewModel(private val SignInRepository : signInRepository) : ViewModel() {

    fun signInVM(emailVM : String, passwordVM: String)
    {
        SignInRepository.signInUser(emailVM, passwordVM)
    }

}