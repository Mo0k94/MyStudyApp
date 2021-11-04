package com.example.mystudyapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.github.tntkhang.fullscreenimageview.library.FullScreenImageViewActivity
import kotlinx.android.synthetic.main.activity_full_screen_image.*


class FullScreenImageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_screen_image)





        val uri = Uri.parse("http://tkdanr2427.cafe24.com/Study/uploads/d61404e0-bc48-c725-1632-ba2d35126226_74_0.jpg");
        val uri2 = Uri.parse("http://tkdanr2427.cafe24.com/Study/uploads/dfe10c2c-f06c-c6de-4e2e-0aa989f4f122_33_0.jpg");
        //val uri3 = Uri.parse("android.resource://$packageName/drawable/board");
        val uri3 = Uri.parse("http://tkdanr2427.cafe24.com/Study/uploads/%EC%95%84%EB%82%98_2020-08-03_1.png");


        //viewHolder.food_price.setText(mData.get(i).getFood_price());
        Glide.with(applicationContext)
                .load(uri)
                .centerCrop() //.override(200,200)
                //.fitCenter()
                .into(img1)


        val uriString: ArrayList<String> = ArrayList()
        uriString.add("http://tkdanr2427.cafe24.com/Study/uploads/d61404e0-bc48-c725-1632-ba2d35126226_74_0.jpg")
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
