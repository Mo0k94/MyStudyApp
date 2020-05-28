package com.example.mystudyapp.adapters;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mystudyapp.R;
import com.example.mystudyapp.Retrofit2.ResultFood;

import java.util.List;

public class Food_Menu_Adapter extends RecyclerView.Adapter<Food_Menu_Adapter.ViewHolder> {
    private List<ResultFood> mData;
    private Context context;

    public Food_Menu_Adapter(Context context, List<ResultFood> listitem) {
        this.context = context;
        this.mData = listitem;
    }


    @NonNull
    @Override
    public Food_Menu_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View convertView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_food_div, parent, false);

        ViewHolder viewHolder = new ViewHolder(convertView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {

        viewHolder.food_name.setText(mData.get(i).getFood_name());
        //viewHolder.food_price.setText(mData.get(i).getFood_price());
        Glide.with(context)
                .load(mData.get(i).getFood_path())
                .centerCrop()
                 //.override(200,200)
                //.fitCenter()
                .into(viewHolder.food_img);


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView food_name;
        TextView food_price;
        ImageView food_img;
        public ViewHolder(View itemView) {
            super(itemView);

            // 레이아웃 들고 오기
            TextView name_txt = (TextView) itemView.findViewById(R.id.name_txt);
            //TextView price_txt = (TextView) itemView.findViewById(R.id.price_txt);
            ImageView food_img = itemView.findViewById(R.id.food_img);
            this.food_name = name_txt;
            //this.food_price = price_txt;
            this.food_img = food_img;
        }
    }


}
