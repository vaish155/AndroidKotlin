package com.vaishnav.mvvmuser.ViewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.vaishnav.mvvmuser.Model.SignInModel
import com.vaishnav.mvvmuser.Repository.signInRepository

class fbViewModel(private val SignInRepository : signInRepository) : ViewModel() {

    fun signInVM(signInModel: SignInModel, context : Context)
    {
        SignInRepository.signInUser(signInModel.emailInpModel, signInModel.passwordInpModel, context)
    }

}