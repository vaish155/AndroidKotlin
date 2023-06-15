package com.vaishnav.mvvmuser.ViewModel

import androidx.lifecycle.ViewModel
import com.google.firebase.database.DatabaseReference
import com.vaishnav.mvvmuser.Repository.GetUserDataRepository

class FbGetDataVM(private val getUserDataRepository: GetUserDataRepository) : ViewModel() {

    fun getNameVM(uname : String): DatabaseReference {
        return getUserDataRepository.getNameFactory(uname)
    }

    fun getBioVM(uname: String): DatabaseReference {
        return getUserDataRepository.getBioFactory(uname)
    }

    fun getDpUrlVM(uname: String) : DatabaseReference{
        return getUserDataRepository.getDpUrlFactory(uname)
    }

}