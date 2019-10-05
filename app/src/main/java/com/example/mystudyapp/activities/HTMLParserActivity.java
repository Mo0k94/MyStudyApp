package com.example.mystudyapp.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mystudyapp.R;
import com.example.mystudyapp.adapters.Food_Adapter;
import com.example.mystudyapp.models.FoodMenu;

import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HTMLParserActivity extends AppCompatActivity {


    private String htmlPageUrl = "http://www.gwangju.ac.kr/_prog/_board/?code=sub6_060307&site_dvs_cd=kr&menu_dvs_cd=060307";

    Button foodBtn;
    List<String> hrefArray = new ArrayList<>();
    List<String> menuArray = new ArrayList<>();
    String[] dateArray;         //날짜
    String[] dayArray;          //요일
    String[] riceArray;         //밥
    String[] soupArray;         //국
    String[] ban1Array;          //반찬1
    String[] ban2Array;          //반찬2
    String[] ban3Array;          //반찬3
    String[] ban4Array;          //반찬4

    FoodMenu foodMenu;

    List<FoodMenu> foodArray = new ArrayList<>();
    Intent intent;

    TextView mMenu_text;

    private ListView listview1;


    private Food_Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_htmlparser);




        foodBtn = (Button) findViewById(R.id.foodBtn);
        foodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new JsoupAsyncTask().execute();
            }
        });


        listview1 = (ListView) findViewById(R.id.list_view);



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

                Document doc = Jsoup.connect(htmlPageUrl).get();

                Elements mElementDataSize = doc.select("div.board_list td.title");//필요한 녀석만 꼬집어서 지정

                for (Element elem : mElementDataSize) {
                    String my_link = elem.select("td.title a").attr("abs:href");
                    // Log.d("TAG","my_link url : " + my_link);
                    //http://www.gwangju.ac.kr/_prog/_board/?mode=V&no=2056473&code=sub6_060307&site_dvs_cd=kr&menu_dvs_cd=060307&skey=&sval=&site_dvs=&stype=&GotoPage= 이렇게 가져옴
                    //titleArray.add(my_link);
                    hrefArray.add(my_link);
                    //Log.d("TAG","hrefArray 배열값 : " + hrefArray);
                }

                Document document = Jsoup.connect(hrefArray.get(1)).get();
                Elements elements    = document.select("div.board_viewDetail table tbody tr");

                for (Element element : elements){

                    String menu = element.select("td").text();

                    menuArray.add(menu);
                    //Elements menutr = element.getElementsByIndexEquals(3);
                    //Element menutd = menutr.get(2);
                    //String menu_title = String.valueOf(menutd.select("td"));


                }


                int idx = menuArray.get(0).indexOf("구분");

                if(menuArray.get(0).contains("구분")){
                    String menu1 = menuArray.get(0).substring(idx+3);                   // 날짜 가져옴
                    dateArray = menu1.split(" ");                   // 날짜

                }
                int idx2 = menuArray.get(3).indexOf("정식");
                if(menuArray.get(3).contains("정식")){

                    String rice = menuArray.get(3).substring(idx2+3);
                    riceArray = rice.split(" " );                    //밥

                }

                dayArray = menuArray.get(1).split(" " );            //요일
                soupArray = menuArray.get(4).split(" " );           //국
                ban1Array = menuArray.get(5).split(" " );           //반찬1
                ban2Array = menuArray.get(6).split(" " );           //반찬2
                ban3Array = menuArray.get(7).split(" " );           //반찬3
                ban4Array = menuArray.get(8).split(" " );           //반찬4



                for(int i=0; i< dateArray.length; i++){
                    Log.d("TAG","날짜 자름 : [ " +  i + " ] =" + dateArray[i]);         //5일 단위 날짜 배열에 담음
                    Log.d("TAG","요일 자름 : [ " + i + " ] = " + dayArray[i]);          // 월~금 배열에 담음
                    Log.d("TAG","쌀밥 자름 : [ " + i + " ] = " + riceArray[i]);          // 월~금 배열에 담음
                    Log.d("TAG","국 자름 : [ " + i + " ] = " + soupArray[i]);          // 월~금 배열에 담음
                    Log.d("TAG","반찬1 자름 : [ " + i + " ] = " + ban1Array[i]);          // 월~금 배열에 담음
                    Log.d("TAG","반찬2 자름 : [ " + i + " ] = " + ban2Array[i]);          // 월~금 배열에 담음
                    Log.d("TAG","반찬3 자름 : [ " + i + " ] = " + ban3Array[i]);          // 월~금 배열에 담음
                    Log.d("TAG","반찬4 자름 : [ " + i + " ] = " + ban4Array[i]);          // 월~금 배열에 담음
                }


            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {


            int count = 0;

            while(count < 5){

                Log.d("Tag","메뉴없는거 찾자!!!!!" + riceArray[count].length());
                if(riceArray[count].length() == 1){
                    Log.d("Tag","메뉴없음!!!!");
                    riceArray[count] = "메뉴없음";
                }

                foodMenu = new FoodMenu(dateArray[count],"(" +dayArray[count]+")",riceArray[count],soupArray[count],ban1Array[count],ban2Array[count],ban3Array[count],ban4Array[count]);
                foodArray.add(foodMenu);
                count++;

            }



            adapter = new Food_Adapter(getApplicationContext(),foodArray);

            listview1.setAdapter(adapter);

            refreshList();

            Log.d("TAG","foodArray : " + foodArray.toString());


            Log.d("TAG","foodArray 0번째 : " + foodArray.get(0).getBan1());
            //mMenu_text.setText(ban1Array[4]);
            //intent = new Intent(Intent.ACTION_VIEW, Uri.parse(hrefArray.get(0)));
            //startActivity(intent);
        }
    }



    private void refreshList() {
        //freeList.clear();
        adapter.notifyDataSetInvalidated();
        //adapter.notifyAll();
    }


}
