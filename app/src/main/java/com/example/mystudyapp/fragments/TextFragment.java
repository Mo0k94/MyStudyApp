package com.example.mystudyapp.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mystudyapp.R;

public class TextFragment extends ExamFragment1 {

    private String mText = "Test";
    private TextView mTextView;

    public TextFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  super.onCreateView(inflater,container,savedInstanceState);

        mTextView = (TextView) view.findViewById(R.id.text_text);
        mTextView.setText(mText);
        return view;
    }

    public void setText(String text) {
        mText = text;
        if (getView() !=null){
            mTextView.setText(mText);
        }
    }

}
