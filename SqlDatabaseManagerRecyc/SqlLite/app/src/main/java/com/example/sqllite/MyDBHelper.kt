package com.example.sqllite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDBHelper(context : Context) : SQLiteOpenHelper(context, "USERDB", null, 1)
{
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE USERS(USERID INTEGER PRIMARY KEY AUTOINCREMENT, UNAME TEXT, PWD TEXT)")
        db?.execSQL("INSERT INTO USERS(UNAME, PWD) VALUES('vaish@mail.com','pass123')")
        db?.execSQL("INSERT INTO USERS(UNAME, PWD) VALUES('vaish1@mail1.com','Pass345')")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

}