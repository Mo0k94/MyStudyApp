package com.example.mystudyapp.CustomView

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageButton
import androidx.appcompat.widget.AppCompatImageButton
import com.example.mystudyapp.R
import com.example.mystudyapp.models.State

class RecordButton(
    context: Context,
    attrs: AttributeSet
    ): AppCompatImageButton(context, attrs) {


        fun updateIconWithState(state: State){
            when(state){
                State.BEFORE_RECORDING -> {
                    setImageResource(R.drawable.ic_record)
                }
                State.ON_RECORDING, State.ON_PLAYING -> {
                    setImageResource(R.drawable.ic_stop)
                }
                State.AFTER_RECORDING -> {
                    setImageResource(R.drawable.ic_play)
                }
            }
        }
}