package com.example.galleryapp

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView

class ImageAdapter(private val context: Context, private val imageList: List<Int>) : BaseAdapter() {
    override fun getCount(): Int {
        return imageList.size
    }

    override fun getItem(p0: Int): Any {
        return imageList[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val imageView = ImageView(context)
        imageView.setImageResource(imageList[p0])
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        imageView.layoutParams = ViewGroup.LayoutParams(200,200)
        return imageView
    }

}