package com.example.mystudyapp

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.captaindroid.tvg.Tvg
import kotlinx.android.synthetic.main.activity_text_gradient.*

class textGradientActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_gradient)

        Tvg.change(gradient_txt, Color.parseColor("#800CDD"), Color.parseColor("#3BA3F2"))

    }
}
