package com.example.mystudyapp.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mystudyapp.R;
import com.example.mystudyapp.activities.HTMLParserActivity;
import com.example.mystudyapp.adapters.Food_Adapter;
import com.example.mystudyapp.models.FoodMenu;
import com.example.mystudyapp.utils.Common;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Food_WeekFragment extends Fragment {

    private List<String> mData;
    public Boolean tag;

    public static Food_WeekFragment newInstance(Boolean tag) {

        Food_WeekFragment fragment = new Food_WeekFragment();

        Bundle bundle = new Bundle();

        Log.d("TAG","Food_WeekFragement =====> " + tag);
        bundle.putSerializable("data",(Serializable) tag);

        fragment.setArguments(bundle);
        return fragment;
    }


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
    private ListView listView;
    private RecyclerView mRecycle_view;
    private Food_Adapter adapter;
    private ProgressDialog progressDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_menu, container, false);
        return view;
    }

    //onCreateView   와     onActivityCreated 사이에 존재 생명주기에는 없음
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        listView = view.findViewById(R.id.list_view);

        Bundle bundle = getArguments();

        tag = (Boolean) bundle.getSerializable("data");

        Log.d("TAG","Food_WeekFragement 2222=====> " + tag);

        new JsoupAsyncTask().execute();


        //mData = (List<String>) bundle.getSerializable("data");

        //MyAdapter mAdapter = new MyAdapter(mData);
        //listView.setAdapter(mAdapter);


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }



    private static class MyAdapter extends BaseAdapter {

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
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = LayoutInflater.from(parent.getContext())
                        .inflate(android.R.layout.simple_list_item_1, parent, false);

                viewHolder.text_view = convertView.findViewById(android.R.id.text1);

                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            String data = mmData.get(position);

            viewHolder.text_view.setText(data);
            return convertView;
        }
    }

    private static class ViewHolder {
        TextView text_view;
    }


    private class JsoupAsyncTask extends AsyncTask<Void, Void, Void> {

        //진행바표시

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            Log.d("TAG","async ====> " + tag);
            if(tag){
                //진행다일로그 시작

                progressDialog = new ProgressDialog(getContext());
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.setTitle("광주대학교 학식");
                progressDialog.setMessage("잠시 기다려 주세요.");
                progressDialog.show();
            }



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

                Document document = Jsoup.connect(hrefArray.get(1)).get();
                Elements elements = document.select("div.board_viewDetail table tbody tr");

                for (Element element : elements) {

                    String menu = element.select("td").text();

                    menuArray.add(menu);

                }
                Log.d("TAG", "menuArray ====> " + menuArray.toString());


                // 첫번째 주 구분으로 나눔

                if (menuArray.get(0).contains("구분")) {
                    int idx = menuArray.get(0).indexOf("구분");
                    String menu1 = menuArray.get(0).substring(idx + 3);                   // 날짜 가져옴
                    //5일 단위 날짜 배열에 담음   날짜가져오기  =2019-10-28 2019-10-29 2019-10-30 2019-10-31 2019-11-1
                    dateArray = menu1.split(" ");                   // 날짜

                } else {
                    String menu1 = menuArray.get(0);
                    dateArray = menu1.split(" ");
                }

                if (menuArray.get(3).contains("정식")) {
                    int idx2 = menuArray.get(3).indexOf("정식");
                    String rice = menuArray.get(3).substring(idx2 + 3);
                    riceArray = rice.split(" ");                    //밥
                } else {
                    String rice = menuArray.get(3);
                    riceArray = rice.split(" ");                    //밥

                }

                dayArray = menuArray.get(1).split(" ");            //요일
                soupArray = menuArray.get(4).split(" ");           //국
                ban1Array = menuArray.get(5).split(" ");           //반찬1
                ban2Array = menuArray.get(6).split(" ");           //반찬2
                ban3Array = menuArray.get(7).split(" ");           //반찬3
                ban4Array = menuArray.get(8).split(" ");           //반찬4


                if (menuArray.size() > 9) {


                    //두번째 주
                    if ("구분".contains(menuArray.get(9))) {
                        int idx3 = menuArray.get(9).indexOf("구분");
                        String menu2 = menuArray.get(9).substring(idx3 + 3);
                        dateArray2 = menu2.split(" ");                   // 날짜


                    } else {
                        String menu2 = menuArray.get(9);
                        dateArray2 = menu2.split(" ");
                    }

                    if (menuArray.get(12).contains("정식")) {
                        int idx2 = menuArray.get(12).indexOf("정식");
                        String rice = menuArray.get(12).substring(idx2 + 3);
                        riceArray2 = rice.split(" ");                    //밥
                    } else {
                        String rice = menuArray.get(12);
                        riceArray2 = rice.split(" ");
                    }

                    dayArray2 = menuArray.get(10).split(" ");            //요일
                    soupArray2 = menuArray.get(13).split(" ");           //국
                    ban1Array2 = menuArray.get(14).split(" ");           //반찬1
                    ban2Array2 = menuArray.get(15).split(" ");           //반찬2
                    ban3Array2 = menuArray.get(16).split(" ");           //반찬3
                    ban4Array2 = menuArray.get(17).split(" ");           //반찬4


                }


            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {

            SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");


            Date time = new Date();


            String now_date = format1.format(time);


            for (int count = 0; count < dateArray.length; count++) {
                if (dateArray[count].contains(now_date)) {
                    for (int count2 = 0; count2 < dateArray.length; count2++) {
                        if (riceArray[count2].length() == 1) {
                            Log.d("Tag", "메뉴없음!!!!");
                            riceArray[count2] = "메뉴없음";
                        }

                        foodMenu = new FoodMenu(dateArray[count2].substring(8, 10), "(" + dayArray[count2] + ")", riceArray[count2], soupArray[count2], ban1Array[count2], ban2Array[count2], ban3Array[count2], ban4Array[count2]);
                        foodArray.add(foodMenu);

                    }

                } else {
                    if (dateArray2 != null) {
                        if (dateArray2[count].contains(now_date)) {
                            for (int count2 = 0; count2 < dateArray2.length; count2++) {
                                //Log.d("Tag","메뉴없는거 찾자!!!!!" + riceArray2[count].length());
                                if (riceArray2[count].length() == 1) {
                                    Log.d("Tag", "메뉴없음!!!!");
                                    riceArray2[count] = "메뉴없음";
                                }

                                foodMenu = new FoodMenu(dateArray2[count].substring(8, 10), "(" + dayArray2[count] + ")", riceArray2[count], soupArray2[count], ban1Array2[count], ban2Array2[count], ban3Array2[count], ban4Array2[count]);
                                foodArray.add(foodMenu);
                            }

                        }
                    }
                }
            }

            adapter = new Food_Adapter(getContext(), foodArray);

            listView.setAdapter(adapter);

            refreshList();


            if(tag){
                progressDialog.dismiss();
            }


            Log.d("TAG", "foodArray : " + foodArray.toString());


        }

    }

    private void refreshList() {
        //freeList.clear();
        adapter.notifyDataSetInvalidated();
        //adapter.notifyAll();
    }

}
