package com.example.mystudyapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mystudyapp.R;
import com.example.mystudyapp.activities.RecyclerViewActivity;
import com.example.mystudyapp.models.getServerImage;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class ImageRecyclerAdapter extends RecyclerView.Adapter<ImageRecyclerAdapter.ViewHolder> {

    private final List<getServerImage> mData;

    private Context context;

    public ImageRecyclerAdapter(Context context,List<getServerImage> dataList) {
        this.context = context;
        mData = dataList;
    }

    @NonNull
    @Override
    public ImageRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View convertView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_image_board, parent, false);

        ViewHolder viewHolder = new ViewHolder(convertView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int position) {
        viewHolder.title_txt.setText(mData.get(position).getTitle());

        Glide.with(context)
                .load(mData.get(position).getPath())
                .fitCenter()
                .into(viewHolder.img_view);


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView img_view;
        TextView title_txt ;

        public ViewHolder(View itemView) {
            super(itemView);
            // 레이아웃 들고 오기
            ImageView img_view = itemView.findViewById(R.id.image_view);

            TextView title_txt = itemView.findViewById(R.id.titleTxt);

            this.img_view = img_view;
            this.title_txt = title_txt;
        }

    }
}
