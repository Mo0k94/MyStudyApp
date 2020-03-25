package com.example.mystudyapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class ChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        BarChart barChart = findViewById(R.id.barchart);

        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(8f,0));
        entries.add(new BarEntry(5f,1));
        entries.add(new BarEntry(4f,2));
        entries.add(new BarEntry(2f,3));
        entries.add(new BarEntry(1f,4));
        entries.add(new BarEntry(10f,5));

        BarDataSet barDataSet = new BarDataSet(entries, "Cells");

        ArrayList<String> labels = new ArrayList<String>();
        labels.add("2016");
        labels.add("2015");
        labels.add("2014");
        labels.add("2013");
        labels.add("2012");
        labels.add("2011");

        BarData data = new BarData(labels, barDataSet);
        barChart.setData(data);
        barChart.setDescription("Set Bar Chart Description Here");
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        barChart.animateY(5000);
    }
}
