package com.example.mystudyapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mystudyapp.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {


    private RecyclerView mRecycle_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        mRecycle_view = findViewById(R.id.recycle_view);

        List<String> data = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            data.add("data : " + i);
        }

        MyRecyclerAdapter adapter = new MyRecyclerAdapter(data);

        mRecycle_view.setAdapter(adapter);

    }

    /*
     *    EventBus 에서 보내는 이벤트 수신하는 콜백 메서드
     * */
    @Subscribe
    public void onItemClick(ItemClickEvent event) {
        Toast.makeText(this, "Click Event : " + event.position, Toast.LENGTH_LONG).show();
    }


    // EventBus에 구독자로 현재 엑티비티 추가
    @Override
    protected void onStart() {
        super.onStart();

        EventBus.getDefault().register(this);
    }

    // EventBus에 구독자로 현재 엑티비티 해지
    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    /*
     *   EventBus 에서 발송할 이벤트
     * */
    private static class ItemClickEvent {
        View view;
        int position;

    }

    private static class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(android.R.id.text1);
        }
    }

    private static class MyRecyclerAdapter extends RecyclerView.Adapter<ViewHolder> {

        private final List<String> mData;


        public MyRecyclerAdapter(List<String> dataList) {
            mData = dataList;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
            View convertView = LayoutInflater.from(parent.getContext())
                    .inflate(android.R.layout.simple_list_item_1, parent, false);

            ViewHolder viewHolder = new ViewHolder(convertView);

            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int position) {
            viewHolder.textView.setText(mData.get(position));

            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View view) {

                    // EventBus를 통해 이벤트 발송
                    ItemClickEvent event = new ItemClickEvent();
                    event.view = viewHolder.itemView;
                    event.position = position;
                    EventBus.getDefault().post(event);
                }
            });
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }
    }
}
