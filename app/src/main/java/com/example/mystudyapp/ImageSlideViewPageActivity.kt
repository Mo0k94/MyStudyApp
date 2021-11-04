package com.example.mystudyapp

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.mystudyapp.adapters.ImageSlideViewPagerAdapter
import com.example.mystudyapp.adapters.RoomRecyclerAdapter
import com.github.tntkhang.fullscreenimageview.library.FullScreenImageViewActivity
import kotlinx.android.synthetic.main.activity_image_slide_view_page.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe


class ImageSlideViewPageActivity : AppCompatActivity() {

    lateinit var mViewPager : ViewPager

    var imageList :  ArrayList<Uri> = ArrayList<Uri>()
    val uriString: ArrayList<String> = ArrayList()
    // Creating Object of ViewPagerAdapter
    var mViewPagerAdapter: ImageSlideViewPagerAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_slide_view_page)

        val uri = Uri.parse("http://tkdanr2427.cafe24.com/Study/uploads/d61404e0-bc48-c725-1632-ba2d35126226_74_0.jpg");
        imageList.add(uri)

        val uri2 = Uri.parse("http://tkdanr2427.cafe24.com/Study/uploads/dfe10c2c-f06c-c6de-4e2e-0aa989f4f122_33_0.jpg");
        imageList.add(uri2)

        val uri3 = Uri.parse("http://tkdanr2427.cafe24.com/Study/uploads/%EC%95%84%EB%82%98_2020-08-03_1.png");
        imageList.add(uri3)


        uriString.add("http://tkdanr2427.cafe24.com/Study/uploads/dfe10c2c-f06c-c6de-4e2e-0aa989f4f122_33_0.jpg")
        uriString.add("http://tkdanr2427.cafe24.com/Study/uploads/d61404e0-bc48-c725-1632-ba2d35126226_74_0.jpg")
        uriString.add("http://tkdanr2427.cafe24.com/Study/uploads/d61404e0-bc48-c725-1632-ba2d35126226_74_0.jpg")
        mViewPagerAdapter = ImageSlideViewPagerAdapter(applicationContext, uriString)

        viewPagerMain.adapter = mViewPagerAdapter




    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }


    // 보낸이 : MemoRecyclerAdapter
    @SuppressLint("RestrictedApi")
    @Subscribe
    fun onItemClick(event: ImageSlideViewPagerAdapter.ItemClickEvent) {

        onImageClickAction(uriString, event.position)
    }


    private fun onImageClickAction(uriString: ArrayList<String>, pos: Int) {
        val fullImageIntent = Intent(this, FullScreenImageViewActivity::class.java)
        fullImageIntent.putExtra(FullScreenImageViewActivity.URI_LIST_DATA, uriString)
        fullImageIntent.putExtra(FullScreenImageViewActivity.IMAGE_FULL_SCREEN_CURRENT_POS, pos)
        startActivity(fullImageIntent)
    }


}