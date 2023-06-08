package com.example.firebasedemo

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class ImagesAdapter(private val context: Context ,private val ImagesList : ArrayList<ImagesClass>) : RecyclerView.Adapter<ImagesAdapter.MyViewHolder> () {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.image_item, parent, false)
        Toast.makeText(context, "Here", Toast.LENGTH_SHORT).show()
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = ImagesList[position]
        Picasso.get().load(currentItem.ImageURL).into(holder.imageItemIVId)
        Log.e(TAG, "In adapter: "+ currentItem.ImageURL)
    }

    override fun getItemCount(): Int {
        return ImagesList.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageItemIVId : ImageView = itemView.findViewById(R.id.imageItemIVId)

    }
}