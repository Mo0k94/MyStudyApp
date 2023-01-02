package com.example.mystudyapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mystudyapp.CustomView.RecordButton
import com.example.mystudyapp.models.State

class RecordActivity : AppCompatActivity() {

    private val recordButton: RecordButton by lazy{
        findViewById(R.id.recordButton)
    }

    private var state = State.BEFORE_RECORDING

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record)

        initViews()
    }

    private fun initViews(){
        recordButton.updateIconWithState(state)
    }
}