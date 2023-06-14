package com.vaishnav.mvvmfirebasertdb.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.vaishnav.mvvmfirebasertdb.R
import com.vaishnav.mvvmfirebasertdb.Repository.DataFactory
import com.vaishnav.mvvmfirebasertdb.Repository.DataRepository
import com.vaishnav.mvvmfirebasertdb.ViewModel.DataViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var firebaseInpId : EditText
    private lateinit var outputId : TextView
    private lateinit var submitBtn : Button

    private lateinit var viewModel : DataViewModel

    private lateinit var dataRepository: DataRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        firebaseInpId = findViewById(R.id.firebaseInpId)
        outputId = findViewById(R.id.outputId)
        submitBtn = findViewById(R.id.submitBtn)

        dataRepository = DataRepository()

        viewModel = ViewModelProvider(this, DataFactory(dataRepository)).get(DataViewModel::class.java)

        submitBtn.setOnClickListener {
            if(TextUtils.isEmpty(firebaseInpId.text.toString()))
            {
                firebaseInpId.setError("Field can't be empty")
                firebaseInpId.requestFocus()
            }
            else
            {
                viewModel.updateDatafromModel(firebaseInpId.text.toString())
                firebaseInpId.setText("")
                val reference = viewModel.getDatafromModel()
                reference.get().addOnSuccessListener {
                    outputId.text = it.value.toString()
                }
            }
        }



    }
}