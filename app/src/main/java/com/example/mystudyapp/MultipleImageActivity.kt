package com.example.mystudyapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class MultipleImageActivity : AppCompatActivity() {

    var PICK_IMAGE_MULTIPLE = 1
    val IMAGE_LIMIT = 3
    var mArrayUri: ArrayList<Uri?>? = null
    var position = 0
    var imagesEncodedList: MutableList<String>? = null
    var imageSwitcher : ImageSwitcher? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multiple_image)


        imageSwitcher = findViewById<ImageSwitcher>(R.id.imageSwitcher)
        imageSwitcher?.setFactory(ViewSwitcher.ViewFactory { ImageView(applicationContext) })

        var selectBtn = findViewById<Button>(R.id.selectBtn)
        var previousBtn = findViewById<Button>(R.id.previousBtn)
        var nextBtn = findViewById<Button>(R.id.nextBtn)

        //init list
        mArrayUri = ArrayList()

         nextBtn.setOnClickListener {
             if (position < mArrayUri!!.size - 1) {
                 // increase the position by 1
                 position++
                 imageSwitcher?.setImageURI(mArrayUri!![position])
             } else {
                 Toast.makeText(
                         this@MultipleImageActivity,
                         "Last Image Already Shown",
                         Toast.LENGTH_SHORT
                 ).show()
             }
         }

         // click here to view previous image

         // click here to view previous image
         previousBtn.setOnClickListener {
             if (position > 0) {
                 // decrease the position by 1
                 position--
                 imageSwitcher?.setImageURI(mArrayUri!![position])
             }
         }

        // click here to select image
        selectBtn.setOnClickListener { // initialising intent
            val intent = Intent()

            // setting type to select to be image
            intent.type = "image/*"

            // allowing multiple image to be selected
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(
                    Intent.createChooser(intent, "Select Picture"),
                    PICK_IMAGE_MULTIPLE
            )
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // When an Image is picked
        if (requestCode == PICK_IMAGE_MULTIPLE && resultCode == RESULT_OK && null != data) {
            // Get the Image from data
            position = if (data.clipData != null) {
                val mClipData = data.clipData

                val cout = data.clipData!!.itemCount
                if(cout > IMAGE_LIMIT){
                    Toast.makeText(this, "이미지는 최대 3장까지 등록 가능합니다.", Toast.LENGTH_LONG).show()
                    val intent = Intent()
                    // setting type to select to be image
                    intent.type = "image/*"

                    // allowing multiple image to be selected
                    intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
                    intent.action = Intent.ACTION_GET_CONTENT
                    startActivityForResult(
                            Intent.createChooser(intent, "Select Picture"),
                            PICK_IMAGE_MULTIPLE
                    )
                }else{
                    for (i in 0 until cout) {
                        // adding imageuri in array
                        val imageurl = data.clipData!!.getItemAt(i).uri
                        mArrayUri!!.add(imageurl)
                    }
                    // setting 1st selected image into image switcher
                    imageSwitcher?.setImageURI(mArrayUri!![0])
                }
                0
            } else {    // 이미지 1장인경우
                val imageurl = data.data
                mArrayUri!!.add(imageurl!!)
                imageSwitcher?.setImageURI(mArrayUri!![0])
                0
            }
        } else {
            // show this if no image is selected
            Toast.makeText(this, "You haven't picked Image", Toast.LENGTH_LONG).show()
        }
    }
}