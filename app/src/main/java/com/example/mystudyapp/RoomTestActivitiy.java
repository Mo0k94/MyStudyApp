package com.example.mystudyapp;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.migration.Migration;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mystudyapp.Room.AppDataBase;
import com.example.mystudyapp.Room.Todo;
import com.example.mystudyapp.Room.Todo2;
import com.example.mystudyapp.adapters.RoomRecyclerAdapter;
import com.example.mystudyapp.models.Check;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

public class RoomTestActivitiy extends AppCompatActivity {


    private EditText mEdit_txt;
    private Button mSaveBtn;

    private String material = "";

    private RoomRecyclerAdapter mAdapter;
    private List<Check> checkList;

    private Integer checkGb = 0;

    private RecyclerView mRecycler_view;

    private AppDataBase db = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_test_activitiy);

        mEdit_txt = findViewById(R.id.edit_txt);
        mSaveBtn = findViewById(R.id.save_btn);

        mRecycler_view = findViewById(R.id.check_recycler);


        checkList = new ArrayList<Check>();


                 db = Room.databaseBuilder(this, AppDataBase.class, "todo2_db")
                .allowMainThreadQueries()
                .addMigrations(MIGRATION_2_3)
                .build();


        getList();

        //db.todoDao().getAll().toString();



//        for(int i=0; i<db.todoDao().getAll().size();i++) {
//            Log.d("TAG","todoDao size : " + i + " 번째" + db.todoDao().getAll().get(i).getTitle());
//           // mResult.setText(db.todoDao().getAll().get(i).getTitle());
//        }


        // Insert
        mSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                material = mEdit_txt.getText().toString();

                checkGb = 0;
                Check dept = new Check(material, checkGb);

                checkList.add(dept);

                db.todoDao().insertTodo(new Todo2(material, checkGb));

                mAdapter = new RoomRecyclerAdapter(RoomTestActivitiy.this, checkList);
                //mCustomAdapter.notifyDataSetChanged();
                mRecycler_view.setAdapter(mAdapter);


                //mResult.setText(db.todoDao().getAll().toString());
            }
        });


    }


    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }


    // 보낸이 : MemoRecyclerAdapter
    @SuppressLint("RestrictedApi")
    @Subscribe
    public void onItemClick(RoomRecyclerAdapter.ItemClickEvent event) {
        Toast.makeText(getApplicationContext(), "" + (event.position),
                Toast.LENGTH_SHORT).show();
        if (checkList.get(event.position).getCheck() == 1) {
            checkGb = 0;
            Check check = new Check(checkList.get(event.position).getCheckText(), checkGb);
            checkList.set(event.position,check);
            //db.todoDao().updateTodo(new Todo2(checkList.get(event.position).getCheckText(),checkGb));
            db.todoDao().updateTodo2(checkList.get(event.position).getCheckText(),checkGb,event.position+1);
            mAdapter = new RoomRecyclerAdapter(RoomTestActivitiy.this, checkList);

            mAdapter.update(checkList, event.position);

        } else {
            Log.d("TAG","checkList checkGb = 0일때 !!");
            checkGb = 1;
            Check check = new Check(checkList.get(event.position).getCheckText(), checkGb);
            checkList.set(event.position,check);
            //db.todoDao().updateTodo(new Todo2(checkList.get(event.position).getCheckText(),checkGb));
            db.todoDao().updateTodo2(checkList.get(event.position).getCheckText(),checkGb,event.position+1);
            mAdapter = new RoomRecyclerAdapter(RoomTestActivitiy.this, checkList);



            mAdapter.update(checkList, event.position);

            Log.d("TAG","onItemClick : update!!!!!!" + db.todoDao().getAll().get(event.position).getCheckGb());
        }

        //mCustomAdapter.notifyDataSetChanged();
        mRecycler_view.setAdapter(mAdapter);


        Log.d("TAG","onItemClick : " + checkList.get(event.position).getCheck());
        mAdapter.setChecked(event.position);
        // Data 변경시 호출 Adapter에 Data 변경 사실을 알려줘서 Update 함.
       // mAdapter.notifyDataSetChanged();

    }

    public void getList(){
        for (int i = 0; i < db.todoDao().getAll().size(); i++) {
            Log.d("TAG", "todoDao size : " + i + " 번째" + db.todoDao().getAll().get(i).getTitle());
            Log.d("TAG", "todoDao size : " + i + " 번째" + db.todoDao().getAll().get(i).getCheckGb());
            Check check = new Check(db.todoDao().getAll().get(i).getTitle(), db.todoDao().getAll().get(i).getCheckGb());
            checkList.add(check);

            mAdapter = new RoomRecyclerAdapter(RoomTestActivitiy.this, checkList);
            mRecycler_view.setAdapter(mAdapter);

        }
    }

    static final Migration MIGRATION_2_3 = new Migration(2, 3) {


        @Override
        public void migrate(SupportSQLiteDatabase database) {
            // Since we didn't alter the table, there's nothing else to do here.
        }
    };

}
