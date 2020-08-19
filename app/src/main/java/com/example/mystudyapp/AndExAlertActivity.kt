package com.example.mystudyapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ir.androidexception.andexalertdialog.AndExAlertDialog
import ir.androidexception.andexalertdialog.AndExAlertDialogListener
import ir.androidexception.andexalertdialog.InputType
import kotlinx.android.synthetic.main.activity_and_ex_alert.*


class AndExAlertActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_and_ex_alert)

        btn1.setOnClickListener {
            AndExAlertDialog.Builder(this)
                    .setTitle("제목")
                    .setMessage("삭제를 하시겠습니까?")
                    .setPositiveBtnText("예")
                    .setNegativeBtnText("아니오")
                    .setCancelableOnTouchOutside(false) // Alert 이외 터치시 종료 여부
                    //.setFont(Font.IRAN_SANS)  //폰트
                    .setImage(R.drawable.bus, 20)    //이미지
                    .setEditText(true, true, "종료를 할거면 '예'를 입력하세요.", InputType.TEXT_MULTI_LINE)
                    .OnPositiveClicked(AndExAlertDialogListener { input -> Toast.makeText(this@AndExAlertActivity, "you typed $input", Toast.LENGTH_SHORT).show() })
                    .OnNegativeClicked(AndExAlertDialogListener { input -> Toast.makeText(this@AndExAlertActivity, "you typed $input", Toast.LENGTH_SHORT).show() })
                    .setTitleTextColor(R.color.AccentColor)
                    .setMessageTextColor(R.color.AccentColor)
                    .setButtonTextColor(R.color.AccentColor)
                    .build()
        }

    }


}