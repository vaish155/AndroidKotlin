package com.example.retrofitreqres

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var user_ids : Array<Int>
    private lateinit var user_emails : Array<String>
    private lateinit var user_fnames : Array<String>
    private lateinit var user_lnames : Array<String>
    private lateinit var user_avatarUrls : Array<String>

    private lateinit var usersRecyclerViewId : RecyclerView
    private lateinit var newUsersList : ArrayList<UsersClass>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listUsers = RetrofitHelper.getInstance().create(ListUsersInterface :: class.java)

        usersRecyclerViewId = findViewById(R.id.usersRecyclerViewId)
        usersRecyclerViewId.layoutManager = LinearLayoutManager(this)
        usersRecyclerViewId.setHasFixedSize(true)

        newUsersList = arrayListOf<UsersClass>()

        GlobalScope.launch {
            val result = listUsers.getUserList()
            if(result != null)
            {
                user_ids  = arrayOf()
                user_emails = arrayOf()
                user_fnames = arrayOf()
                user_lnames = arrayOf()
                user_avatarUrls = arrayOf()

                Log.e(TAG, "Result Body: " + result.body().toString())
                val pages = result.body()?.page
                val per_page : Int? = result.body()?.per_page
                val total = result.body()?.total
                val userDataListVal = result.body()?.data

                userDataListVal?.forEach{ UserData ->
                    user_ids += UserData.id
                    user_emails += UserData.email
                    user_fnames += UserData.first_name
                    user_lnames += UserData.last_name
                    user_avatarUrls += UserData.avatar
                    newUsersList.add(UsersClass(UserData.id, UserData.first_name, UserData.last_name, UserData.email, UserData.avatar))

                }
            }
        }
        usersRecyclerViewId.adapter = UserAdapter(newUsersList)
    }
}