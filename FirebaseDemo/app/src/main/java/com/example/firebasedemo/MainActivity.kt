package com.example.firebasedemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val registerMainBtn : TextView = findViewById(R.id.registerMainBtn)
        val emailidInp : EditText = findViewById(R.id.emailidInp)
        val pwdInp : EditText = findViewById(R.id.pwdInp)
        val loginBtn : Button = findViewById(R.id.loginBtn)

        val mAuth = FirebaseAuth.getInstance()

        registerMainBtn.setOnClickListener{
            val intent1 = Intent(this, RegisterActivity::class.java)
            startActivity(intent1)
        }

        loginBtn.setOnClickListener {
            mAuth.signInWithEmailAndPassword(emailidInp.text.toString(), pwdInp.text.toString()).addOnSuccessListener {
                Toast.makeText(this, "Log In Success", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, HomePage::class.java)
                intent.putExtra("Uname",emailidInp.text.toString())
                startActivity(intent)
            }
                .addOnFailureListener{
                    Toast.makeText(this, "Log In Unsuccessful", Toast.LENGTH_SHORT).show()
                }
        }

    }

    override fun onStart() {
        super.onStart()
        val mAuth1 = FirebaseAuth.getInstance()
        val currentUser = mAuth1.currentUser
        if(currentUser != null)
        {
            val emailid = currentUser.email.toString()
            val intent = Intent(this, HomePage::class.java)
            intent.putExtra("Uname",emailid)
            startActivity(intent)
        }
    }



}