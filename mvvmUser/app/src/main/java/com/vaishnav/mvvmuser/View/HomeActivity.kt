package com.vaishnav.mvvmuser.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.vaishnav.mvvmuser.Model.SendDataModel
import com.vaishnav.mvvmuser.R
import com.vaishnav.mvvmuser.Repository.GetUserDataFactory
import com.vaishnav.mvvmuser.Repository.GetUserDataRepository
import com.vaishnav.mvvmuser.Repository.SendDataFactory
import com.vaishnav.mvvmuser.Repository.SendDataRepository
import com.vaishnav.mvvmuser.ViewModel.FbGetDataVM
import com.vaishnav.mvvmuser.ViewModel.fbSendDataVM

class HomeActivity : AppCompatActivity() {

    private lateinit var userIvId : ImageView
    private lateinit var userNameId : EditText
    private lateinit var userBioId : EditText
    private lateinit var makeChangesBtnId : Button

    private lateinit var fbGetDataVM: FbGetDataVM
    private lateinit var getUserDataRepository: GetUserDataRepository

    private lateinit var FbSendDataVM: fbSendDataVM
    private lateinit var sendDataRepository: SendDataRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val uname = intent.getStringExtra("username").toString()

        userIvId = findViewById(R.id.userIvId)
        userNameId = findViewById(R.id.userNameId)
        userBioId = findViewById(R.id.userBioId)
        makeChangesBtnId = findViewById(R.id.makeChangesBtnId)



        getUserDataRepository = GetUserDataRepository()
        fbGetDataVM = ViewModelProvider(this, GetUserDataFactory(getUserDataRepository)).get(FbGetDataVM::class.java)


        fbGetDataVM.getNameVM(uname).get().addOnSuccessListener {
            userNameId.setText(it.value.toString())
        }.addOnFailureListener{
            userNameId.setText("Null")
        }

        fbGetDataVM.getBioVM(uname).get().addOnSuccessListener {
            userBioId.setText(it.value.toString())
        }.addOnFailureListener{
            userBioId.setText("null")
        }

        fbGetDataVM.getDpUrlVM(uname).get().addOnSuccessListener {
            Glide.with(userIvId).load(it.value.toString()).into(userIvId)
        }.addOnFailureListener{
            userIvId.setImageResource(androidx.appcompat.R.drawable.abc_ic_arrow_drop_right_black_24dp)
        }

        sendDataRepository = SendDataRepository()
        FbSendDataVM = ViewModelProvider(this, SendDataFactory(sendDataRepository)).get(fbSendDataVM::class.java)

        makeChangesBtnId.setOnClickListener {
            FbSendDataVM.sendDataVM(uname, SendDataModel(userNameId.text.toString(), userBioId.text.toString(), " "), this@HomeActivity)
        }
    }
}