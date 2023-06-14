package com.vaishnav.mvvmrecyclerapi

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.vaishnav.mvvmrecyclerapi.network.RetroInstance
import com.vaishnav.mvvmrecyclerapi.network.RetroService
import com.vaishnav.mvvmrecyclerapi.viewmodels.RecyclerActivityViewModel
import retrofit2.Call
import retrofit2.Response

class RecyclerView : AppCompatActivity() {

    private lateinit var recyclerView: androidx.recyclerview.widget.RecyclerView

    private lateinit var recyclerViewAdapter : RecyclerViewAdapter

    private lateinit var searchboxid : EditText
    private lateinit var serachButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)

        recyclerView = findViewById(R.id.recyclerView)

        searchboxid = findViewById(R.id.searchboxid)
        serachButton = findViewById(R.id.serachButton)


        initRecyclerView()
        createData()

    }

    private fun initRecyclerView()
    {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@RecyclerView)
            recyclerViewAdapter = RecyclerViewAdapter()
            adapter = recyclerViewAdapter

            val decoration = DividerItemDecoration(applicationContext, VERTICAL)
            addItemDecoration(decoration)
        }
    }

    fun createData()
    {
//        val item = ArrayList<RecyclerData>()
//        item.add(RecyclerData("Java", "Yes"))
//        item.add(RecyclerData("Kotlin", "Yes"))
//        item.add(RecyclerData("Python", "Oh yeah"))
//        item.add(RecyclerData("C", "Hmm"))
//        item.add(RecyclerData("Swift", "Okie"))
//
//        recyclerViewAdapter.setListData(item)
//        recyclerViewAdapter.notifyDataSetChanged()

//        val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
//        val call = retroInstance.getDatafromAPI("atlanta")
//        call.enqueue(object : retrofit2.Callback<RecyclerList>{
//            override fun onResponse(call: Call<RecyclerList>, response: Response<RecyclerList>) {
//                if(response.isSuccessful)
//                {
//                    recyclerViewAdapter.setListData(response.body()?.items!!)
//                    recyclerViewAdapter.notifyDataSetChanged()
//                }
//            }
//
//            override fun onFailure(call: Call<RecyclerList>, t: Throwable) {
//                Toast.makeText(this@RecyclerView, "Error in getting data from API", Toast.LENGTH_SHORT).show()
//            }
//        })



        val viewModel = ViewModelProviders.of(this).get(RecyclerActivityViewModel::class.java)
        viewModel.getRecyclerListDataObserver().observe(this, Observer<RecyclerList>{
            if(it != null)
            {
                recyclerViewAdapter.setListData(it.items)
                recyclerViewAdapter.notifyDataSetChanged()
            }
            else
            {
                Toast.makeText(this@RecyclerView, "Error in getting data from API", Toast.LENGTH_SHORT).show()
            }
        })

        serachButton.setOnClickListener {
            viewModel.makeApiCall(searchboxid.text.toString())
        }

    }

}