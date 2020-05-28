package com.example.mystudyapp;

import android.content.Context;
import android.graphics.Paint;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mystudyapp.models.Check;

import java.util.ArrayList;
import java.util.List;

public class CheckListViewActivity extends AppCompatActivity {


    private EditText mEdit_txt;
    private Button mCheckBtn;
    private ListView mListView;

    private String material = "";
    // Data를 관리해주는 Adapter
    private CustomAdapter mCustomAdapter = null;
    // 제네릭(String)을 사용한 ArrayList
    private ArrayList<String> mArrayList = new ArrayList<String>();


    private List<Check> checkList;

    private Integer checkGb = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_list_view);

        setLayout();


        checkList = new ArrayList<Check>();



        mCheckBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // mCheckBtn.setText("현재 체크 수 = " + mCustomAdapter.getChecked().size()+ " 개");

                material = mEdit_txt.getText().toString();
                checkGb = 0;
                Check dept = new Check(material,checkGb);


                checkList.add(dept);
                mCustomAdapter = new CustomAdapter(CheckListViewActivity.this, mArrayList, checkList);
                //mCustomAdapter.notifyDataSetChanged();
                mListView.setAdapter(mCustomAdapter);


            }
        });
        // ArrayList에 String으로 이루어진 값들을 Add 한다.
//        mArrayList.add("첫번째");
//        mArrayList.add("두번째");
//        mArrayList.add("세번째");
//        mArrayList.add("네번째");
//        mArrayList.add("다섯번째");
//        mArrayList.add("여섯번째");
//        mArrayList.add("일곱번째");
//        mArrayList.add("여덜번째");

        mListView.setOnItemClickListener(mItemClickListener);
    }


    // ListView 안에 Item을 클릭시에 호출되는 Listener
    private AdapterView.OnItemClickListener mItemClickListener = new
            AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1,
                                        int position, long arg3) {
                    Toast.makeText(getApplicationContext(), "" + (position),
                            Toast.LENGTH_SHORT).show();
                    if(checkList.get(position).getCheck() == 1){
                        checkGb = 0;
                        checkList.get(position).setCheck(checkGb);
                    }else{
                        checkGb = 1;
                        checkList.get(position).setCheck(checkGb);
                    }

                    mCustomAdapter.setChecked(position);
                    // Data 변경시 호출 Adapter에 Data 변경 사실을 알려줘서 Update 함.
                    mCustomAdapter.notifyDataSetChanged();

                }
            };


    // Custom Adapter
    class CustomAdapter extends BaseAdapter {

        private ViewHolder viewHolder = null;
        // 뷰를 새로 만들기 위한 Inflater
        private LayoutInflater inflater = null;
        private ArrayList<String> sArrayList = new ArrayList<String>();
        private List<Check> checkList;
        private boolean[] isCheckedConfrim;

        public CustomAdapter(Context c, ArrayList<String> mList, List<Check> checkList) {
            inflater = LayoutInflater.from(c);
            this.sArrayList = mList;
            // ArrayList Size 만큼의 boolean 배열을 만든다.
            // CheckBox의 true/false를 구별 하기 위해
            this.isCheckedConfrim = new boolean[checkList.size()];
            this.checkList = checkList;
        }

        // CheckBox를 모두 선택하는 메서드
        public void setAllChecked(boolean ischeked) {
            int tempSize = isCheckedConfrim.length;
            for (int a = 0; a < tempSize; a++) {
                isCheckedConfrim[a] = ischeked;
            }
        }

        public void setChecked(int position) {
            isCheckedConfrim[position] = !isCheckedConfrim[position];
        }

        public ArrayList<Integer> getChecked() {
            int tempSize = isCheckedConfrim.length;
            ArrayList<Integer> mArrayList = new ArrayList<Integer>();
            for (int b = 0; b < tempSize; b++) {
                if (isCheckedConfrim[b]) {
                    mArrayList.add(b);
                }
            }
            return mArrayList;
        }

        @Override
        public int getCount() {
            return checkList.size();
        }

        @Override
        public Object getItem(int arg0) {
            return checkList.get(arg0);
        }

        @Override
        public long getItemId(int arg0) {
            return arg0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            // ConvertView가 null 일 경우
            View v = convertView;

            if (v == null) {
                viewHolder = new ViewHolder();
                // View를 inflater 시켜준다.
                v = inflater.inflate(R.layout.item_check, null);
                viewHolder.cBox = (CheckBox) v.findViewById(R.id.check_box);
                viewHolder.cText = v.findViewById(R.id.check_txt);
                v.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) v.getTag();
            }

            // CheckBox는 기본적으로 이벤트를 가지고 있기 때문에 ListView의 아이템
            // 클릭리즈너를 사용하기 위해서는 CheckBox의 이벤트를 없애 주어야 한다.
            viewHolder.cBox.setClickable(false);
            viewHolder.cBox.setFocusable(false);

            //viewHolder.cBox.setText(sArrayList.get(position));
            // isCheckedConfrim 배열은 초기화시 모두 false로 초기화 되기때문에
            // 기본적으로 false로 초기화 시킬 수 있다.





                if(checkList.get(position).getCheck() == 1){     // 체크 된 게 있으면
                    Boolean checkBool = true;
                    viewHolder.cBox.setChecked(checkBool);
                }else{
                    viewHolder.cBox.setChecked(false);
                }

            viewHolder.cText.setText(checkList.get(position).getCheckText());

            if(viewHolder.cBox.isChecked()){
                viewHolder.cText.setPaintFlags(viewHolder.cText.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            }else{
                viewHolder.cText.setPaintFlags(0);
            }


            return v;
        }
    }

    class ViewHolder {
        // 새로운 Row에 들어갈 CheckBox
        private CheckBox cBox = null;
        private TextView cText;
    }


    private void setLayout() {
        mEdit_txt = findViewById(R.id.edit_txt);
        mListView = findViewById(R.id.check_listView);
        mCheckBtn = findViewById(R.id.check_Btn);
    }
}
