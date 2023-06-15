package com.vaishnav.mvvmuser.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import com.vaishnav.mvvmuser.R
import com.vaishnav.mvvmuser.Repository.signInFactory
import com.vaishnav.mvvmuser.Repository.signInRepository
import com.vaishnav.mvvmuser.ViewModel.fbViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var emailInpId : EditText
    private lateinit var pwdInpId : EditText
    private lateinit var loginBtnId : Button

    private lateinit var FbViewModel: fbViewModel
    private lateinit var SignInRepository: signInRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        emailInpId = findViewById(R.id.emailInpId)
        pwdInpId = findViewById(R.id.pwdInpId)
        loginBtnId = findViewById(R.id.loginBtnId)

        SignInRepository = signInRepository()
        FbViewModel = ViewModelProvider(this, signInFactory(SignInRepository)).get(fbViewModel::class.java)

        loginBtnId.setOnClickListener {
            if(TextUtils.isEmpty(emailInpId.text.toString()))
            {
                emailInpId.setError("Mandatory Field")
                emailInpId.requestFocus()
            }
            else if (TextUtils.isEmpty(pwdInpId.text.toString()))
            {
                pwdInpId.setError("Mandatory Field")
                pwdInpId.requestFocus()
            }
            else
            {
                FbViewModel.signInVM(emailInpId.text.toString(), pwdInpId.text.toString())
            }
        }


    }
}