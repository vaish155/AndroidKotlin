package com.vaishnav.mvvmrecyclerapi

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class RecyclerViewAdapter : androidx.recyclerview.widget.RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> () {

    var items = ArrayList<RecyclerData>()

    fun setListData(data: ArrayList<RecyclerData>)
    {
        this.items = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_row, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class MyViewHolder(view : View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view)
    {
        val tvTitle = view.findViewById<TextView>(R.id.tv_Title)
        val tvDesc = view.findViewById<TextView>(R.id.tv_Desc)
        val imageThumb = view.findViewById<ImageView>(R.id.imageThumb)

        fun bind(data :RecyclerData)
        {
            tvTitle.text = data.name
            if(!TextUtils.isEmpty(data.description))
            {
                tvDesc.text = data.description
            }
            else
            {
                tvDesc.text = "Not available"
            }
            val url = data.owner.avatar_url
            Glide.with(imageThumb).load(url).circleCrop().placeholder(androidx.appcompat.R.drawable.btn_checkbox_checked_mtrl).fallback(androidx.appcompat.R.drawable.btn_checkbox_checked_mtrl).into(imageThumb)

        }
    }



}