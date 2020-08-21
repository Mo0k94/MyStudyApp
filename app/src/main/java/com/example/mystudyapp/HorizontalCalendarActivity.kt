package com.example.mystudyapp

import `in`.akshit.horizontalcalendar.HorizontalCalendarView
import `in`.akshit.horizontalcalendar.Tools
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_horizontal_calendar.*
import java.util.*


class HorizontalCalendarActivity : AppCompatActivity() {


    var startTime : Calendar = Calendar.getInstance()
    var endTime : Calendar = Calendar.getInstance()

    var array : ArrayList<String> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_horizontal_calendar)


        startTime.add(Calendar.MONTH, -6)
        endTime.add(Calendar.MONTH, 6)

        array.add(Tools.getFormattedDateToday())


        horizontalCalendar.setUpCalendar(startTime.timeInMillis,
                endTime.timeInMillis,
                array,
                HorizontalCalendarView.OnCalendarListener { date ->
                    Toast.makeText(this@HorizontalCalendarActivity, "$date clicked!", Toast.LENGTH_SHORT).show()
                })
    }
}

