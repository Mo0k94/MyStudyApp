package com.example.mystudyapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mystudyapp.R;
import com.example.mystudyapp.models.FoodMenu;
import com.github.chrisbanes.photoview.PhotoView;

import java.util.List;

public class ImageSlider_Adapter extends RecyclerView.Adapter<ImageSlider_Adapter.MyViewHolder> {
    private Context context;
    private String[] sliderImage;

    public ImageSlider_Adapter(Context context, String[] sliderImage) {
        this.context = context;
        this.sliderImage = sliderImage;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_image_slider, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bindSliderImage(sliderImage[position]);
    }

    @Override
    public int getItemCount() {
        return sliderImage.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private PhotoView mPhotoView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mPhotoView = itemView.findViewById(R.id.photoView);
        }

        public void bindSliderImage(String imageURL) {
            Glide.with(context)
                    .load(imageURL)
                    .into(mPhotoView);
        }
    }

}
