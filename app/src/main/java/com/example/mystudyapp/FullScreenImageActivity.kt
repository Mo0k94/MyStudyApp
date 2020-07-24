package com.example.mystudyapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.tntkhang.fullscreenimageview.library.FullScreenImageViewActivity
import kotlinx.android.synthetic.main.activity_full_screen_image.*


class FullScreenImageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_screen_image)

        val uri = Uri.parse("android.resource://$packageName/drawable/bus");
        val uri2 = Uri.parse("android.resource://$packageName/drawable/icon");
        val uri3 = Uri.parse("android.resource://$packageName/drawable/board");



        val uriString: ArrayList<String> = ArrayList()
        uriString.add(uri.toString())
        uriString.add(uri2.toString())
        uriString.add(uri3.toString())

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
