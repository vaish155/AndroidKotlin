package com.example.firebasedemo

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val emailidRegInp : EditText = findViewById(R.id.emailidRegInp)
        val pwdRegInp : EditText = findViewById(R.id.pwdRegInp)
        val signUpBtn : Button = findViewById(R.id.signUpBtn)

        val mAuth = FirebaseAuth.getInstance()

        signUpBtn.setOnClickListener {
            mAuth.createUserWithEmailAndPassword(emailidRegInp.text.toString(), pwdRegInp.text.toString()).addOnCompleteListener{
                Toast.makeText(this, "User Created", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener{
                Toast.makeText(this, "User Creation Failed", Toast.LENGTH_SHORT).show()
            }
        }

    }
}