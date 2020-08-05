package com.example.mystudyapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.denzcoskun.imageslider.models.SlideModel
import kotlinx.android.synthetic.main.activity_image_slide.*

class ImageSlideActivity : AppCompatActivity() {


    private var slideModels : ArrayList<SlideModel> = ArrayList<SlideModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_slide)





        slideModels.add(SlideModel("http://tkdanr2427.cafe24.com/Study/uploads/%ED%98%B8%EC%87%BC_2020-08-03_2.jpg","Image1"))
        slideModels.add(SlideModel("http://tkdanr2427.cafe24.com/Study/uploads/%ED%98%B8%EC%87%BC_2020-08-03_1.jpg","Image2"))
        slideModels.add(SlideModel("http://tkdanr2427.cafe24.com/Study/uploads/%ED%98%B8%EC%87%BC_2020-08-03_0.jpg","Image3"))

        img_slider.setImageList(slideModels, true)

    }
}