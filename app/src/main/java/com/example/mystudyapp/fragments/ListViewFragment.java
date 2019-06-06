package com.example.mystudyapp.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mystudyapp.R;

import java.io.Serializable;
import java.util.List;

public class ListViewFragment extends Fragment {

    private List<String> mData;

    public static ListViewFragment newInstance(List<String> data){
        ListViewFragment fragment = new ListViewFragment();

        Bundle bundle = new Bundle();
        bundle.putSerializable("data",(Serializable) data);

        fragment.setArguments(bundle);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_view,container,false);
        return view;
    }

    //onCreateView   와     onActivityCreated 사이에 존재 생명주기에는 없음
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        ListView listView = view.findViewById(R.id.list_view);

        Bundle bundle = getArguments();
        mData = (List<String>) bundle.getSerializable("data");

        MyAdapter mAdapter = new MyAdapter(mData);
        listView.setAdapter(mAdapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }



    private static class MyAdapter extends BaseAdapter{

        private final List<String> mmData;
        public MyAdapter(List<String> data) {
            mmData = data;
        }

        @Override
        public int getCount() {
            return mmData.size();
        }

        @Override
        public Object getItem(int position) {
            return mmData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if(convertView == null){
                viewHolder = new ViewHolder();
                convertView = LayoutInflater.from(parent.getContext())
                        .inflate(android.R.layout.simple_list_item_1,parent,false);

                viewHolder.text_view = convertView.findViewById(android.R.id.text1);

                convertView.setTag(viewHolder);
            }else{
                viewHolder = (ViewHolder) convertView.getTag();
            }
            String data = mmData.get(position);

            viewHolder.text_view.setText(data);
            return convertView;
        }
    }

    private static class ViewHolder{
        TextView text_view;
    }
}
