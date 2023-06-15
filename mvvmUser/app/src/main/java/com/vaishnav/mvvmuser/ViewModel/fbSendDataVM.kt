package com.vaishnav.mvvmuser.ViewModel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.vaishnav.mvvmuser.Model.SendDataModel
import com.vaishnav.mvvmuser.Repository.SendDataRepository

class fbSendDataVM(private val sendDataRepository: SendDataRepository) : ViewModel() {

    fun sendDataVM(uname : String, sendDataModel: SendDataModel, context: Context)
    {
        sendDataRepository.sendDataRepositoryFn(uname, sendDataModel.nameModel, sendDataModel.bioModel, sendDataModel.dpUrlModel)
        Toast.makeText(context, "Updated", Toast.LENGTH_SHORT).show()

    }

}