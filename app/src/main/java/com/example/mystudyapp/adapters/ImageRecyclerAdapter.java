package com.example.mystudyapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
    private final List<getServerImage> saveList;
    private Context context;

    public ImageRecyclerAdapter(Context context,List<getServerImage> dataList,List<getServerImage> saveList) {
        this.context = context;
        mData = dataList;
        this.saveList = saveList;

    }

    //Event Bus 클래스
    public static class ItemClickEvent {
        public ItemClickEvent(int position) {
            this.position = position;
            //this.id = id;
        }

        public int position;
        //public long id;

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
        viewHolder.user_txt.setText(mData.get(position).getUser_id());
        viewHolder.date_txt.setText(mData.get(position).getDate());
        viewHolder.title_txt.setText(mData.get(position).getTitle());

        Glide.with(context)
                .load(mData.get(position).getPath())
                .override(600,300)
                .fitCenter()
                .into(viewHolder.img_view);

        Log.d("ITPANGPANG","img("+viewHolder.img_view.getWidth()+" x "+viewHolder.img_view.getHeight()+")");


        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // MainActivity에 onItemClick이 받음
                EventBus.getDefault().post(new ItemClickEvent(position));
            }
        });


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView img_view;
        TextView title_txt;
        TextView user_txt;
        TextView date_txt;
        public ViewHolder(View itemView) {
            super(itemView);
            // 레이아웃 들고 오기
            ImageView img_view = itemView.findViewById(R.id.image_view);

            TextView title_txt = itemView.findViewById(R.id.titleTxt);
            TextView user_txt = itemView.findViewById(R.id.userTxt);
            TextView date_txt = itemView.findViewById(R.id.dateTxt);
            this.img_view = img_view;
            this.title_txt = title_txt;
            this.user_txt = user_txt;
            this.date_txt = date_txt;
        }

    }
}
