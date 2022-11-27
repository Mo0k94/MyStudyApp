package com.example.mystudyapp

import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.OrientationHelper
import com.applikeysolutions.cosmocalendar.dialog.CalendarDialog
import com.applikeysolutions.cosmocalendar.dialog.OnDaysSelectionListener
import com.applikeysolutions.cosmocalendar.selection.OnDaySelectedListener
import com.applikeysolutions.cosmocalendar.selection.RangeSelectionManager
import com.applikeysolutions.cosmocalendar.settings.appearance.ConnectedDayIconPosition
import com.applikeysolutions.cosmocalendar.utils.SelectionType
import kotlinx.android.synthetic.main.activity_vertical_calendar.*
import java.util.*
import com.applikeysolutions.cosmocalendar.selection.BaseSelectionManager as BaseSelectionManager1


class verticalCalendarActivity : AppCompatActivity() {

    lateinit var selected: GregorianCalendar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vertical_calendar)


        calendar_view.selectionType = SelectionType.SINGLE;
        calendar_view.isShowDaysOfWeekTitle = false
//        calendar_view.selectionManager = RangeSelectionManager(OnDaySelectedListener {
//            Log.e(" CALENDAR ", "========== setSelectionManager ==========")
//            Log.e(" CALENDAR ", "Selected Dates : " + calendar_view.selectedDates.size)
//            if (calendar_view.selectedDates.size <= 0) return@OnDaySelectedListener
//            Log.e(" CALENDAR ", "Selected Days : " + calendar_view.selectedDays)
//        })


        calendar_view.selectionManager = RangeSelectionManager(OnDaySelectedListener{
            Log.e(" CALENDAR ", "========== setSelectionManager ==========")
            Log.e(" CALENDAR ", "Selected Dates : " + calendar_view.selectedDates.size)
            if (calendar_view.selectedDates.size <= 0) return@OnDaySelectedListener
            Log.e(" CALENDAR ", "Selected Days : " + calendar_view.selectedDays)
        })

    }
}