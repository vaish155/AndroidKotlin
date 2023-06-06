package com.example.cricketplayersrecycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PlayerAdapter(private val playersList : ArrayList<PlayersClass>) : RecyclerView.Adapter<PlayerAdapter.MyViewHolder> () {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.cricket_players_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = playersList[position]
        holder.circketItemImageId.setImageResource(currentItem.playerImage)
        holder.CricketItemNameId.text = currentItem.playerName
        holder.CricketItemCountryId.text = currentItem.playerCountry
        holder.CricketItemDescId.text = currentItem.playerDesc
    }

    override fun getItemCount(): Int {
        return playersList.size
    }

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)
    {
        val circketItemImageId : ImageView = itemView.findViewById(R.id.circketItemImageId)
        val CricketItemNameId : TextView = itemView.findViewById(R.id.CricketItemNameId)
        val CricketItemCountryId : TextView = itemView.findViewById(R.id.CricketItemCountryId)
        val CricketItemDescId : TextView = itemView.findViewById(R.id.CricketItemDescId)
    }
}