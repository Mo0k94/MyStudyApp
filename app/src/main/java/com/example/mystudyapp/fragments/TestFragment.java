package com.example.mystudyapp.fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.mystudyapp.R;

public class TestFragment extends Fragment {

    private OnImageTouchListener mListener;
    private ImageView mImageView;

    public interface OnImageTouchListener{
        void onImageTouch(ImageView view, String msg);
    }

    public void setOnImageTouchListener(OnImageTouchListener listener){
        mListener = listener;
        getView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null){
                    mListener.onImageTouch(mImageView,"임의의 데이터");
                }
            }
        });
    }
    public TestFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test, container, false);
        mImageView = (ImageView) view.findViewById(R.id.image_view);
        return view;
    }



}
