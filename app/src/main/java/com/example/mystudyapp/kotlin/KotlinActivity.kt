package com.example.mystudyapp.kotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.widget.ListView
import com.example.mystudyapp.R
import com.example.mystudyapp.adapters.MyAdapter
import com.example.mystudyapp.models.ListItem
import kotlinx.android.synthetic.main.activity_kotlin.*
import java.util.*

class KotlinActivity : AppCompatActivity() {

    private var mListView: ListView? = null
    private var mDataList: ArrayList<ListItem>? = null
    private var mAdapter: MyAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)


        //데이터터
        mDataList = ArrayList()


        addItem("var","읽기, 쓰기 가능한 변수");
        addItem("val","읽기만 가능한 변수");
        addItem("변수 nullAble","ex) title2: String?")

        mAdapter = MyAdapter(mDataList)

        list_view.adapter = mAdapter

        var a = 0;

        while(a<5){
            println(a++)
        }
    }

    private fun addItem(title: String, title2: String?) {
        val item = ListItem(title, title2,null)
        mDataList?.add(item)
    }

}

