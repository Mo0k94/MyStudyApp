package com.example.mystudyapp

import android.content.Intent
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mystudyapp.Retrofit2.BarcodeApi
import com.example.mystudyapp.Retrofit2.C005
import com.example.mystudyapp.Retrofit2.RetrofitBarcode
import com.google.zxing.integration.android.IntentIntegrator
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.jsoup.Jsoup
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.util.*

class BarcodeActivity : AppCompatActivity() {

    private var mBarcodeApi: BarcodeApi = RetrofitBarcode().barcodeApi
    var barcode : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_barcode)

        var barCodeScan  = IntentIntegrator(this)

        barCodeScan.setBeepEnabled(false)   // 바코드 인식시 소리
        barCodeScan.setPrompt("상품 정보 조회")
        barCodeScan.initiateScan()


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents == null) {  //qrCode 결과가 없는경우
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show()
                finish()
            } else {    //qrCode 결과가 있으면
                Log.d("TAG", "barCode 결과 : " + result.contents)

                barcode = result.contents

                val barcodePart = RequestBody.create(MultipartBody.FORM, Uri.decode(result.contents))

                var call : Call<C005> = mBarcodeApi.getItem(result.contents)
                //var call : Call<C005> = mBarcodeApi.items

               call.enqueue(object : Callback<C005> {
                   override fun onResponse(call: Call<C005>, response: Response<C005>) {
                       Log.d("TAG", "통신 성공!  ")

                       //Log.d("TAG", "통신 결과값 : " + response.headers())
                       Log.d("TAG", "통신 결과값 : " + response.body()?.RESULT()?.msg)
                       Log.d("TAG", "통신 결과값 : " + response.body()?.RESULT()?.code)
                       var info: C005 = response.body()!!

                       Log.d("TAG", "ㄱㄷㄱㄷㄷㄷㄱㄷㄱ$info")

                       Log.d("TAG", "통신 결과값 : " + info.RESULT().code)
                       Log.d("TAG", "통신 결과값 : " + info.RESULT().msg)
                       Log.d("TAG", "통신 결과값 : " + info.RESULT().toString())
                       Log.d("TAG", "통신 결과값 : " + info.Row().barcd)
                       Log.d("TAG", "통신 결과값 : " + info.Row().toString())


                   }

                   override fun onFailure(call: Call<C005>, t: Throwable) {
                       Log.d("TAG", "통신 실패! ")
                       Log.d("TAG", "통신 실패! " + t.message)
                       Log.d("TAG", "통신 실패! " + t.cause)
                       Log.d("TAG", "통신 실패! " + t.toString())
                   }

               })
           }
        } else {
            Log.d("TAG", "qrCode 결과 없음 ")
            super.onActivityResult(requestCode, resultCode, data)
        }
    }



}

