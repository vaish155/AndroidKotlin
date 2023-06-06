package com.example.sqllite

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var sqlEmailsRecycId : RecyclerView
    private lateinit var sqlEmailsArray : ArrayList<SqlModelClass>
    private lateinit var db : SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        var helper = MyDBHelper(applicationContext)
//        var db = helper.readableDatabase
//        var rs = db.rawQuery("SELECT * FROM USERS", null)

        sqlEmailsRecycId = findViewById(R.id.sqlEmailsRecyc)
        sqlEmailsRecycId.layoutManager = LinearLayoutManager(this)
        sqlEmailsRecycId.setHasFixedSize(true)

        sqlEmailsArray = arrayListOf<SqlModelClass>()

        getEmails()


        var submitBtn = findViewById<Button>(R.id.submitBtn)
        var emailInp = findViewById<EditText>(R.id.emailInp)
        var pwdInp = findViewById<EditText>(R.id.pwdInp)
        var refreshBtn = findViewById<Button>(R.id.refreshBtn)

        refreshBtn.setOnClickListener {
            getEmails()
        }

        submitBtn.setOnClickListener{
            var cv = ContentValues()
            cv.put("UNAME", emailInp.text.toString())
            cv.put("PWD", pwdInp.text.toString())

            db.insert("USERS",null, cv)

            emailInp.setText(" ")
            pwdInp.setText("")

            emailInp.requestFocus()
        }
    }

    private fun getEmails() {
        var helper = MyDBHelper(applicationContext)
        db = helper.readableDatabase
        var rs = db.rawQuery("SELECT * FROM USERS", null)

        while(rs.moveToNext())
        {
            sqlEmailsArray.add(SqlModelClass(rs.getString(1)))
        }

        sqlEmailsRecycId.adapter = SqlEmailsAdapter(sqlEmailsArray)
    }
}