package com.example.mystudyapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener
import kotlinx.android.synthetic.main.activity_image_slide.*


class ImageSlideActivity : AppCompatActivity() {


    private var slideModels : ArrayList<SlideModel> = ArrayList<SlideModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_slide)





        slideModels.add(SlideModel("http://tkdanr2427.cafe24.com/Study/uploads/%ED%98%B8%EC%87%BC_2020-08-03_2.jpg","제목입니다."))
        slideModels.add(SlideModel("http://tkdanr2427.cafe24.com/Study/uploads/%ED%98%B8%EC%87%BC_2020-08-03_1.jpg","제목입니다."))
        slideModels.add(SlideModel("http://tkdanr2427.cafe24.com/Study/uploads/%ED%98%B8%EC%87%BC_2020-08-03_0.jpg","제목입니다."))

        img_slider.setImageList(slideModels, ScaleTypes.FIT)

        setSupportActionBar(toolbar)
        supportActionBar?.title = ""

        app_bar.addOnOffsetChangedListener(object : OnOffsetChangedListener {
            override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
                when {
                    verticalOffset == 0 -> { //  이미지가 보일때
                        supportActionBar?.title = ""
                        toolbar_layout.title = ""
                    }
                    Math.abs(verticalOffset) >= app_bar.totalScrollRange -> { // 이미지 안보이고 툴바만 보일떄
                        supportActionBar?.title = "제목입니다."
                    }
                    Math.abs(verticalOffset) <= app_bar.totalScrollRange -> {// 중간
                        toolbar_layout.title = ""
                        supportActionBar?.subtitle=""
                    }
                    else -> {
                        supportActionBar?.title = ""
                        toolbar_layout.title = ""
                    }
                }
            }
        })


    }
}