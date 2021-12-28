package com.example.mystudyapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.mystudyapp.adapters.ImageSlider_Adapter;

public class PhotoViewActivity extends AppCompatActivity {

    private ViewPager2 sliderViewPager;
    private LinearLayout layoutIndicator;

    private String[] images = new String[] {
            "http://tkdanr2427.cafe24.com/NewBaby/uploads/762ee9ac-c26f-00eb-4d4c-97f3ae0e81d6_66_0.jpg",
            "http://tkdanr2427.cafe24.com/NewBaby/uploads/cfcbec46-c4f1-d4b7-6fe0-c7475e726758_66_1.jpg",
            "http://tkdanr2427.cafe24.com/NewBaby/uploads/8b8df100-cbcb-7fa5-060f-625a34009566_66_2.jpg"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_view);

        sliderViewPager = findViewById(R.id.sliderViewPager);
        layoutIndicator = findViewById(R.id.layoutIndicators);

        sliderViewPager.setOffscreenPageLimit(1);
        sliderViewPager.setAdapter(new ImageSlider_Adapter(this, images));

        sliderViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentIndicator(position);
            }
        });

        setupIndicators(images.length);
    }
    private void setupIndicators(int count) {
        ImageView[] indicators = new ImageView[count];
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        params.setMargins(16, 8, 16, 8);

        for (int i = 0; i < indicators.length; i++) {
            indicators[i] = new ImageView(this);
            indicators[i].setImageDrawable(ContextCompat.getDrawable(this,
                    R.drawable.bg_indicator_inactive));
            indicators[i].setLayoutParams(params);
            layoutIndicator.addView(indicators[i]);
        }
        setCurrentIndicator(0);
    }

    private void setCurrentIndicator(int position) {
        int childCount = layoutIndicator.getChildCount();
        for (int i = 0; i < childCount; i++) {
            ImageView imageView = (ImageView) layoutIndicator.getChildAt(i);
            if (i == position) {
                imageView.setImageDrawable(ContextCompat.getDrawable(
                        this,
                        R.drawable.bg_indicator_active
                ));
            } else {
                imageView.setImageDrawable(ContextCompat.getDrawable(
                        this,
                        R.drawable.bg_indicator_inactive
                ));
            }
        }
    }
}