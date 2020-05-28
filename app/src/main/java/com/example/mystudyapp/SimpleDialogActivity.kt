package com.example.mystudyapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.barisatalay.simpledialog.SimpleDialog
import com.barisatalay.simpledialog.model.mdlStyle

class SimpleDialogActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple_dialog)


        val style = mdlStyle(
                titleColor = R.color.colorControlHighlight,
                messageColor = R.color.colorText,
                buttonColor = R.color.AccentColor)

        SimpleDialog(this)
                .style(style)
                .show("Title",
                        "Description",
                        "Done",
                        true)

    }
}
