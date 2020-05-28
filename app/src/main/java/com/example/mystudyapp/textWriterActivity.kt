package com.example.mystudyapp

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sarnava.textwriter.TextWriter
import kotlinx.android.synthetic.main.activity_text_writer.*

class textWriterActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_writer)
        textWriter
                .setWidth(12F)
                .setDelay(30)
                .setColor(Color.rgb(183,240,177))
                .setConfig(TextWriter.Configuration.INTERMEDIATE)
                .setSizeFactor(30f)
                .setLetterSpacing(25f)
                .setText("GWANGJU UNIV")
                .setListener {
                    //do stuff after animation is finished
                }
                .startAnimation()

    }
}
