package com.example.mystudyapp

import android.R.attr
import android.app.Activity
import android.content.ClipData
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_matisse.*


class MatisseActivity : AppCompatActivity() {

    private val PERMISSIONS_READ_WRITE = 123

    val REQUEST_RESULT_CODE = 101

    val PICTURE_REQUEST_CODE = 100

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_matisse)

       // if (isReadWritePermitted()) getGalleryResults() else checkReadWritePermission()
        imageSelect.setOnClickListener{
               var intent : Intent =  Intent(Intent.ACTION_PICK, MediaStore.Audio.Media.EXTERNAL_CONTENT_URI);
                //사진을 여러개 선택할수 있도록 한다
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Select Picture"),  PICTURE_REQUEST_CODE);


        }



    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICTURE_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {

                //기존 이미지 지우기
                image1.setImageResource(0)
                image2.setImageResource(0)
                image3.setImageResource(0)

                //ClipData 또는 Uri를 가져온다
                val uri: Uri = data?.data!!
                val clipData: ClipData? = data.clipData

                //이미지 URI 를 이용하여 이미지뷰에 순서대로 세팅한다.
                for (i in 0..2) {
                    if (clipData != null) {
                        if (i < clipData.itemCount) {
                            val urione: Uri = clipData.getItemAt(i).uri
                            when (i) {
                                0 -> image1.setImageURI(urione)
                                1 -> image2.setImageURI(urione)
                                2 -> image3.setImageURI(urione)
                            }
                        }
                    }
                }
            }
        }

    }

}