package com.example.mystudyapp

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_full_screen_image.*
import java.util.*
import kotlin.concurrent.timer

class PhotoFrameActivity : AppCompatActivity() {

    private val photoList = mutableListOf<Uri>()

    private val photoImageView: ImageView by lazy {
        findViewById<ImageView>(R.id.photoImageView)
    }

    private val backPhotoImageView: ImageView by lazy {
        findViewById<ImageView>(R.id.backPhotoImageView)
    }

    private var currentPosition = 0

    private var timer :Timer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_frame)
        getPhotoUriFromIntent()
    }

    private fun getPhotoUriFromIntent(){
        val size = intent.getIntExtra("photoListSize", 0)
        for (i in 0..size){
            intent.getStringExtra("photo$i")?.let{
                photoList.add(Uri.parse(it))
            }
        }
    }

    private fun startTimer(){
        timer = timer(period = 5 * 1000){
            runOnUiThread{
                val current = currentPosition
                val next = if (photoList.size <= currentPosition +1) 0 else currentPosition + 1

                backPhotoImageView.setImageURI(photoList[current])

                photoImageView.alpha = 0f
                photoImageView.setImageURI(photoList[next])
                /* Fade in Animate*/
                photoImageView.animate()
                    .alpha(1.0f)
                    .setDuration(1000)
                    .start()

                currentPosition = next
            }
        }
    }

    override fun onStop() {
        super.onStop()
        timer?.cancel()
    }

    override fun onStart() {
        super.onStart()
        startTimer()
    }

    override fun onDestroy() {
        super.onDestroy()
        timer?.cancel()
    }
}