package com.example.mystudyapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.mystudyapp.R;
import com.github.tntkhang.fullscreenimageview.library.FullScreenImageViewActivity;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ImageSlideViewPagerAdapter extends PagerAdapter {

    Context context;

    List<String> images;

    LayoutInflater mLayoutInflater;

    //Event Bus 클래스
    public static class ItemClickEvent {
        public ItemClickEvent(int position) {
            this.position = position;

        }
        public int position;
    }

    // Viewpager Constructor
    public ImageSlideViewPagerAdapter(Context context, List<String> images) {
        this.context = context;
        this.images = images;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        // return the number of images
        return images.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((LinearLayout) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        // inflating the item.xml
        View itemView = mLayoutInflater.inflate(R.layout.item_image_slide, container, false);

        // referencing the image view from the item.xml file
        ImageView imageView = (ImageView) itemView.findViewById(R.id.imageViewMain);

        Glide.with(context)
                .load(images.get(position))
                .centerCrop() //.override(200,200)
                //.fitCenter()
                .into(imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // MainActivity에 onItemClick이 받음
                EventBus.getDefault().post(new ImageSlideViewPagerAdapter.ItemClickEvent(position));
                Log.d("TAG","onBindViewHolder memo.getId 값 : " + position);
            }
        });

        // setting the image in the imageView
        //imageView.setImageResource(images.get(position));

        // Adding the View
        Objects.requireNonNull(container).addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView((LinearLayout) object);
    }
}
