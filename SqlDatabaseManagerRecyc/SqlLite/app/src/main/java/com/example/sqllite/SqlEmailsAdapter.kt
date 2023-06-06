package com.example.sqllite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SqlEmailsAdapter(private val emailsList : ArrayList<SqlModelClass>) : RecyclerView.Adapter<SqlEmailsAdapter.MyViewHolder> () {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.sql_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = emailsList[position]
        holder.sqlItemTextId.text = currentItem.emailAddress
    }

    override fun getItemCount(): Int {
        return emailsList.size
    }

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)
    {
        val sqlItemTextId : TextView = itemView.findViewById(R.id.sqlItemTextId)
    }

}