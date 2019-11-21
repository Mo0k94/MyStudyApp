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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

    String[] dateArray2;         //날짜
    String[] dayArray2;          //요일
    String[] riceArray2;         //밥
    String[] soupArray2;         //국
    String[] ban1Array2;          //반찬1
    String[] ban2Array2;          //반찬2
    String[] ban3Array2;          //반찬3
    String[] ban4Array2;          //반찬4

    FoodMenu foodMenu;

    List<FoodMenu> foodArray = new ArrayList<>();       // 첫번째 주
    List<FoodMenu> foodArray2 = new ArrayList<>();       // 두번째 주
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
                    //Log.d("TAG","hrefArray 배열값 : " + hrefArray.toString());
                }

                Document document = Jsoup.connect(hrefArray.get(0)).get();
                Elements elements    = document.select("div.board_viewDetail table tbody tr");

                for (Element element : elements){

                    String menu = element.select("td").text();

                    menuArray.add(menu);

                }


                // 첫번째 주 구분으로 나눔

                if(menuArray.get(0).contains("구분")){
                    int idx = menuArray.get(0).indexOf("구분");
                    String menu1 = menuArray.get(0).substring(idx+3);                   // 날짜 가져옴
                                                                                            //5일 단위 날짜 배열에 담음   날짜가져오기  =2019-10-28 2019-10-29 2019-10-30 2019-10-31 2019-11-1

                    dateArray = menu1.split(" ");                   // 날짜

                }

                if(menuArray.get(3).contains("정식")){
                    int idx2 = menuArray.get(3).indexOf("정식");
                    String rice = menuArray.get(3).substring(idx2+3);
                    riceArray = rice.split(" " );                    //밥
                }

                dayArray = menuArray.get(1).split(" " );            //요일
                soupArray = menuArray.get(4).split(" " );           //국
                ban1Array = menuArray.get(5).split(" " );           //반찬1
                ban2Array = menuArray.get(6).split(" " );           //반찬2
                ban3Array = menuArray.get(7).split(" " );           //반찬3
                ban4Array = menuArray.get(8).split(" " );           //반찬4


                //두번째 주
                if(menuArray.get(9).contains("구분")){
                    int idx3 = menuArray.get(9).indexOf("구분");
                    String menu2 = menuArray.get(9).substring(idx3+3);
                    dateArray2 = menu2.split(" ");                   // 날짜
                }
                if(menuArray.get(12).contains("정식")){
                    int idx2 = menuArray.get(12).indexOf("정식");
                    String rice = menuArray.get(12).substring(idx2+3);
                    riceArray2 = rice.split(" " );                    //밥
                }


                dayArray2 = menuArray.get(10).split(" " );            //요일
                soupArray2 = menuArray.get(13).split(" " );           //국
                ban1Array2 = menuArray.get(14).split(" " );           //반찬1
                ban2Array2 = menuArray.get(15).split(" " );           //반찬2
                ban3Array2 = menuArray.get(16).split(" " );           //반찬3
                ban4Array2 = menuArray.get(17).split(" " );           //반찬4




//                for(int i=0; i< dateArray.length; i++){
//                    Log.d("TAG","날짜 자름 : [ " +  i + " ] ="  + dateArray2[i]);         //5일 단위 날짜 배열에 담음
//                    Log.d("TAG","요일 자름 : [ " + i + " ] = "  + dayArray2[i]);          // 월~금 배열에 담음
//                    Log.d("TAG","쌀밥 자름 : [ " + i + " ] = "  + riceArray2[i]);          // 월~금 배열에 담음
//                    Log.d("TAG","국 자름 : [ " + i + " ] = "    + soupArray2[i]);          // 월~금 배열에 담음
//                    Log.d("TAG","반찬1 자름 : [ " + i + " ] = " + ban1Array2[i]);          // 월~금 배열에 담음
//                    Log.d("TAG","반찬2 자름 : [ " + i + " ] = " + ban2Array2[i]);          // 월~금 배열에 담음
//                    Log.d("TAG","반찬3 자름 : [ " + i + " ] = " + ban3Array2[i]);          // 월~금 배열에 담음
//                    Log.d("TAG","반찬4 자름 : [ " + i + " ] = " + ban4Array2[i]);          // 월~금 배열에 담음
//                }



            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {

            SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd");


            Date time = new Date();


            String now_date = format1.format(time);

            int count = 0;

            if(dateArray[count].contains(now_date)){
                //Log.d("TAG","현재 날짜 포함 O ======> " + now_date);
                while(count < 5){
                    if(riceArray[count].length() == 1){
                        Log.d("Tag","메뉴없음!!!!");
                        riceArray[count] = "메뉴없음";
                    }

                    foodMenu = new FoodMenu(dateArray[count].substring(8,10),"(" +dayArray[count]+")",riceArray[count],soupArray[count],ban1Array[count],ban2Array[count],ban3Array[count],ban4Array[count]);
                    foodArray.add(foodMenu);
                    count++;
                }

            }else{
                while(count < 5){

                    //Log.d("Tag","메뉴없는거 찾자!!!!!" + riceArray2[count].length());
                    if(riceArray2[count].length() == 1){
                        Log.d("Tag","메뉴없음!!!!");
                        riceArray2[count] = "메뉴없음";
                    }

                    foodMenu = new FoodMenu(dateArray2[count].substring(8,10),"(" +dayArray2[count]+")",riceArray2[count],soupArray2[count],ban1Array2[count],ban2Array2[count],ban3Array2[count],ban4Array2[count]);
                    foodArray.add(foodMenu);
                    count++;
                }
                //Log.d("TAG","현재 날짜 포함 X ======> " + now_date);

            }




            adapter = new Food_Adapter(getApplicationContext(),foodArray);

            listview1.setAdapter(adapter);

            refreshList();

            Log.d("TAG","foodArray : " + foodArray.toString());



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
