package com.example.cricketplayersrecycler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var playersRecycId : RecyclerView
    private lateinit var newCricketPlayersList : ArrayList<PlayersClass>
    lateinit var playerImageId : Array<Int>
    lateinit var playerNameId : Array<String>
    lateinit var playerCountryId : Array<String>
    lateinit var playerDescId : Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        playerImageId = arrayOf(R.drawable.msd, R.drawable.virat, R.drawable.stevesmith, R.drawable.benstokes)
        playerNameId = arrayOf("MS Dhoni", "Virat Kohli", "Steve Smith", "Ben Stokes")
        playerCountryId = arrayOf("India", "India", "Australia", "England")
        playerDescId = arrayOf("RH Batter", "RH Batter", "RH Batter", "LH Batter")

        playersRecycId = findViewById(R.id.playersRecycId)
        playersRecycId.layoutManager = LinearLayoutManager(this)
        playersRecycId.setHasFixedSize(true)

        newCricketPlayersList = arrayListOf<PlayersClass>()

        getPlayersData()




    }

    private fun getPlayersData() {
        for(i in playerImageId.indices)
        {
            val player = PlayersClass(playerImageId[i], playerNameId[i], playerCountryId[i], playerDescId[i])
            newCricketPlayersList.add(player)
        }

        newCricketPlayersList.add(PlayersClass(R.drawable.dalesteyn, "Dale Steyn", "South Africa", "RH Bowler"))

        newCricketPlayersList.add(PlayersClass(R.drawable.mstarc, "Mithchell Starc", "Australia", "LH Bowler"))

        newCricketPlayersList.add(PlayersClass(R.drawable.janderson, "James Anderson", "England", "RH Bowler"))

        playersRecycId.adapter = PlayerAdapter(newCricketPlayersList)
    }
}