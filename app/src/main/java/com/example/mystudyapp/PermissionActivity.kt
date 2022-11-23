package com.example.mystudyapp

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_permission.*

class PermissionActivity : AppCompatActivity() {

    /*
    *
    * FastCampus Chapter05. 전자액자
    * Layout 을 그리는 법
    *   - 가로 화면으로 그리기
    * Android Permission 사용하기
    * View Animation 사용하기
    * Activity LifeCycle
    * Content Provider
    *   - SAF (Storage Access FrameWork)
    *
    * 전자액자
    * 저장소 접근권한을 이용하여 로컬 사진을 로드할 수 있음.
    * 추가한 사진들을 일정한 시간으로 전환하며 보여줄 수 있음
    * */
    private val getImageButton: Button by lazy {
        findViewById<Button>(R.id.getImageBtn)
    }

    private val addImageButton: Button by lazy {
        findViewById<Button>(R.id.addImageBtn)
    }

    private val imageViewList: List<ImageView> by lazy{
        mutableListOf<ImageView>().apply{
            add(findViewById(R.id.imageView1))
            add(findViewById(R.id.imageView2))
            add(findViewById(R.id.imageView3))
            add(findViewById(R.id.imageView4))
            add(findViewById(R.id.imageView5))
            add(findViewById(R.id.imageView6))
        }
    }

    private val imageUriList: MutableList<Uri> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_permission)

        initGetImageButton()
        initStartPhotoFrameButton()
    }

    private fun initStartPhotoFrameButton(){
        addImageButton.setOnClickListener{
            val intent = Intent(this, PhotoFrameActivity::class.java)
            imageUriList.forEachIndexed{ index, uri ->
                intent.putExtra("photo$index", uri.toString())
            }
            intent.putExtra("photoListSize", imageUriList.size)
            startActivity(intent)
        }
    }

    private fun initGetImageButton(){
        getImageBtn.setOnClickListener{
            when {
                ContextCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE
                ) ==  PackageManager.PERMISSION_GRANTED ->{
                    // todo 권한이 잘 부여되었을 때 갤러리에서 사진을 선택하는 기능
                    navigatePhotos()
                }
                shouldShowRequestPermissionRationale(android.Manifest.permission.READ_EXTERNAL_STORAGE) -> {
                    // 교육용 팝업 확인 후 권한 팝업을 띄우는 기능
                    showPermissionPopup()
                }
                else -> {
                    // 권한 요청 팝업 호출
                    requestPermissions(arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), 1000)
                }
            }
        }
    }

    private fun showPermissionPopup(){
        AlertDialog.Builder(this)
            .setTitle("권한이 필요합니다.")
            .setMessage("전자액자 앱에서 사진을 불러오기 위해 권한이 필요합니다.")
            .setPositiveButton("동의") { _, _ ->
                requestPermissions(arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), 1000)
            }
            .setNegativeButton("취소") { _, _ ->

            }
            .create()
            .show()
    }



    private fun navigatePhotos(){
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        startActivityForResult(intent, 2000)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode != Activity.RESULT_OK){
            return
        }

        when(requestCode){
            2000 -> {
                val selectedImageUri: Uri? = data?.data

                if(selectedImageUri != null){
                    if(imageUriList.size == 6){
                        Toast.makeText(this, "이미 사진이 꽉 찼습니다.", Toast.LENGTH_LONG).show()
                        return
                    }
                    imageUriList.add(selectedImageUri)
                    imageViewList[imageUriList.size -1].setImageURI(selectedImageUri)
                }else{
                    Toast.makeText(this, "사진을 가져오지 못했습니다.", Toast.LENGTH_LONG).show()
                }

            }
            else -> {
                Toast.makeText(this, "사진을 가져오지 못했습니다.", Toast.LENGTH_LONG).show()
            }
        }
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when(requestCode){
            1000 -> {
                if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    // todo 권한이 부여된 것입니다.
                    navigatePhotos()
                }else{
                    Toast.makeText(this, "권한을 거부하였습니다.", Toast.LENGTH_LONG).show()
                }
            }
            else -> {

            }
        }
    }
}