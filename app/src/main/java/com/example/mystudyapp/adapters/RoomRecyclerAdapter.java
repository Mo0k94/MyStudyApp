package com.example.mystudyapp.adapters;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mystudyapp.CheckListViewActivity;
import com.example.mystudyapp.R;
import com.example.mystudyapp.models.Check;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class RoomRecyclerAdapter extends RecyclerView.Adapter<RoomRecyclerAdapter.ViewHolder> {


    private List<Check> checkList;
    private final Context mContext;

    private boolean[] isCheckedConfrim;


    public void swap(List<Check> newCheckList) {
        checkList = newCheckList;
        notifyDataSetChanged();
    }

    public void insert(List<Check> checkList) {
        this.checkList = checkList;
        //notifyItemInserted(0);
        notifyDataSetChanged();

    }
    public void update(List<Check> checkList, int position) {
        this.checkList = checkList;

        notifyItemChanged(position);
    }

    //Event Bus 클래스
    public static class ItemClickEvent {
        public ItemClickEvent(int position) {
            this.position = position;
        }

        public int position;

    }


    public RoomRecyclerAdapter(Context context, List<Check> memoList) {

        checkList = memoList;
        mContext = context;
    }


    public void setChecked(int position) {
        isCheckedConfrim[position] = !isCheckedConfrim[position];
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {




        //뷰를 새로 만들 때
        View convertView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_room,parent,false);

        return new ViewHolder(convertView);
    }




    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        // 데이터
        final Check check = checkList.get(position);


        holder.checkBox.setClickable(false);
        holder.checkBox.setFocusable(false);


        // 화면에 뿌리기
        holder.checkTextView.setText(check.getCheckText());


        if(checkList.get(position).getCheck() == 1){     // 체크 된 게 있으면
            holder.checkBox.setChecked(true);
        }else{
            holder.checkBox.setChecked(false);
        }

        holder.checkTextView.setText(checkList.get(position).getCheckText());

        Log.d("TAG","뿌려주는 곳 !!!" + checkList.get(position).getCheck());
        if(holder.checkBox.isChecked()){
            holder.checkTextView.setPaintFlags(holder.checkTextView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }else{
            holder.checkTextView.setPaintFlags(0);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // MainActivity에 onItemClick이 받음
                EventBus.getDefault().post(new ItemClickEvent(position));
                Log.d("TAG","onBindViewHolder memo.getId 값 : " + position);
            }
        });


    }

    @Override
    public int getItemCount() {
        return checkList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView checkTextView;
        CheckBox  checkBox;
        //ImageButton delBtn;

        public ViewHolder(View itemView) {
            super(itemView);
            // 레이아웃 들고 오기
            TextView checkTextView = itemView.findViewById(R.id.check_txt);
            CheckBox check_box =  itemView.findViewById(R.id.check_box);


            this.checkTextView = checkTextView;
            this.checkBox = check_box;

        }

    }

}



