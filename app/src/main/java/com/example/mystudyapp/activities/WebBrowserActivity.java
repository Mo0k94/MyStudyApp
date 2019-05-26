package com.example.mystudyapp.activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mystudyapp.R;

public class WebBrowserActivity extends AppCompatActivity {
    private WebView myMwebView;
    private Button mSearch_btn;
    private EditText mUrlEdittext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_browser);

        init();

        mSearch_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myMwebView.loadUrl(mUrlEdittext.getText().toString());
            }
        });


        WebSettings webSettings = myMwebView.getSettings();
        //자바스크립트 기능 이용 여부
        webSettings.setJavaScriptEnabled(true);
        //일반적
        //myMwebView.setWebViewClient(new WebViewClient());

        //기능 확장
        myMwebView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                Toast.makeText(WebBrowserActivity.this, "로딩 완료", Toast.LENGTH_SHORT).show();
            }
        });

        //키보드 이벤트 검출
        mUrlEdittext.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_SEARCH){
                    //버튼을 코드로 누르기
                    mSearch_btn.callOnClick();

                    //키보드 내리기
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(mUrlEdittext.getWindowToken(),0);
                }
                return true;
            }
        });
    }

    private void init() {
        mUrlEdittext = findViewById(R.id.url_edit);
        myMwebView = findViewById(R.id.web_view);
        mSearch_btn = findViewById(R.id.search_btn);
    }


    //뒤로 가기 키 캐치
    @Override
    public void onBackPressed() {
        if(myMwebView.canGoBack()){
            myMwebView.goBack();
        }else{
            super.onBackPressed();
        }
    }
}
