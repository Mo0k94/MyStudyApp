package com.example.mystudyapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.ionbit.ionalert.IonAlert
import kotlinx.android.synthetic.main.activity_ion_alert.*


class IonAlertActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ion_alert)

        var ionAlert: IonAlert? = IonAlert(this)

        btn1.setOnClickListener {
            ionAlert?.titleText = "Here`s a Message"
            ionAlert?.setConfirmClickListener(IonAlert.ClickListener {
                ionAlert.dismissWithAnimation()
            })
            ionAlert?.show()
        }

        btn2.setOnClickListener {
            ionAlert?.titleText = "Oops...."
            ionAlert?.contentText = "Something went wrong!!"
            ionAlert?.setConfirmClickListener(IonAlert.ClickListener {
                ionAlert.dismissWithAnimation()
            })
            ionAlert?.show()
        }

        btn3.setOnClickListener {
            ionAlert?.titleText = "Are you sure"
            ionAlert?.contentText = "Won`t be able to recover this file!"
            ionAlert?.confirmText = "Yes, delete it!"
            ionAlert?.setConfirmClickListener(IonAlert.ClickListener {
                ionAlert.dismissWithAnimation()
            })
            ionAlert?.show()
        }




    }
}