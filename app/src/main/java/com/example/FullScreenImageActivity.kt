package com.example

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.mystudyapp.R
import com.github.tntkhang.fullscreenimageview.library.FullScreenImageViewActivity
import kotlinx.android.synthetic.main.activity_full_screen_image.*


class FullScreenImageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_screen_image)

        val uri = Uri.parse("android.resource://" + getPackageName() + "/drawable/call");
        val uri2 = Uri.parse("android.resource://" + getPackageName() + "/drawable/bus");



        val uriString: ArrayList<String> = ArrayList()
        uriString.add(uri.toString())
        uriString.add(uri2.toString())
        uriString.add(uri.toString())

        img1.setOnClickListener { onImageClickAction(uriString, 0) }
        img2.setOnClickListener{ onImageClickAction(uriString, 1) }
        img3.setOnClickListener{ onImageClickAction(uriString, 2) }
    }

    private fun onImageClickAction(uriString: ArrayList<String>, pos: Int) {
        val fullImageIntent = Intent(this, FullScreenImageViewActivity::class.java)
        fullImageIntent.putExtra(FullScreenImageViewActivity.URI_LIST_DATA, uriString)
        fullImageIntent.putExtra(FullScreenImageViewActivity.IMAGE_FULL_SCREEN_CURRENT_POS, pos)
        startActivity(fullImageIntent)
    }
}
