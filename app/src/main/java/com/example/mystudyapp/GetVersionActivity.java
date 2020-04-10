package com.example.mystudyapp;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetVersionActivity extends AppCompatActivity {

    String nowVersion="";
    String marketVersion ="";
    TextView mNowVersion, mMarketVersion;

    AlertDialog.Builder mDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_version);

        mNowVersion = findViewById(R.id.nowVersion);
        mMarketVersion = findViewById(R.id.marketVersion);

        mDialog = new AlertDialog.Builder(this);


        Log.d("TAG","패키지 명 ==> "  + getPackageName());

        new JsoupAsyncTask().execute();
       //String store_version = MarketVersionChecker.getMarketVersion("com.psmstudio.user.mygwangju");
        //mMarketVersion.setText("마켓 버전 : " + store_version);
        try{
            String device_version = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
            mNowVersion.setText("현재 버전 : " + device_version);
            nowVersion = device_version;
        }catch (PackageManager.NameNotFoundException e){
            e.printStackTrace();
        }
    }

    private class JsoupAsyncTask extends AsyncTask<Void, Void, Void> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                //해당 URL 페이지를 가져온다.

                String url = "https://play.google.com/store/apps/details?id=com.psmstudio.user.mygwangju";
                Document doc = Jsoup.connect(url)
                        .timeout(5000)
                        .maxBodySize(0)
                        .get();

                Elements Version = doc.select("div.W4P4ne div.JHTxhe div.IxB2fe div.hAyfc:eq(3) div.IQ1z0d");
                Log.d("TAG","버전 가져오기 == > " + Version.text());

                marketVersion = Version.text();

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {

            mMarketVersion.setText("마켓 버전 : " + marketVersion);

            if (marketVersion.compareTo(nowVersion) > 0) {
                mDialog.setMessage("업데이트 후 사용해주세요.")
                        .setCancelable(false)
                        .setPositiveButton("업데이트 바로가기",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int id) {
                                        Intent marketLaunch = new Intent(
                                                Intent.ACTION_VIEW);
                                        marketLaunch.setData(Uri
                                                .parse("https://play.google.com/store/apps/details?id=com.psmstudio.user.mygwangju"));
                                        startActivity(marketLaunch);
                                        finish();
                                    }
                                });
                AlertDialog alert = mDialog.create();
                alert.setTitle("안 내");
                alert.show();

            } else {
                Toast.makeText(GetVersionActivity.this, "업데이트가 필요없습니다.", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
