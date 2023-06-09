package com.example.retrofitreqres

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.coroutines.CoroutineScope

class UserAdapter(private val usersList:ArrayList<UsersClass>) : RecyclerView.Adapter<UserAdapter.MyViewHolder> () {
//    private lateinit var context1 : Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
//        context1 = parent.context
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = usersList[position]
        holder.userItemFnameId.text = currentItem.user_fname
        holder.userItemLnameId.text = currentItem.user_lname
        holder.userItemEmailId.text = currentItem.user_email
        holder.userItemIdId.text = currentItem.user_id.toString()
        Log.e(TAG, "From adapter: " + holder.userItemFnameId.text.toString())
        Glide.with(holder.userItemAvatarId.context).load(currentItem.user_avatar_url).into(holder.userItemAvatarId)
    }

    override fun getItemCount(): Int {
        return usersList.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val userItemAvatarId : ImageView = itemView.findViewById(R.id.userItemAvatarId)
        val userItemFnameId : TextView = itemView.findViewById(R.id.userItemFnameId)
        val userItemLnameId : TextView = itemView.findViewById(R.id.userItemLnameId)
        val userItemIdId : TextView = itemView.findViewById(R.id.userItemIdId)
        val userItemEmailId : TextView = itemView.findViewById(R.id.userItemEmailId)
    }
}