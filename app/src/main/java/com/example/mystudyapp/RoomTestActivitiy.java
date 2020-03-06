package com.example.mystudyapp;

import android.annotation.SuppressLint;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mystudyapp.Room.AppDataBase;
import com.example.mystudyapp.Room.Plan;
import com.example.mystudyapp.adapters.RoomRecyclerAdapter;
import com.example.mystudyapp.models.Check;
import com.stone.vega.library.VegaLayoutManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RoomTestActivitiy extends AppCompatActivity {


    private EditText mEdit_txt;
    private Button mAddBtn, mSaveBtn;

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
        mAddBtn = findViewById(R.id.add_btn);
        mSaveBtn = findViewById(R.id.save_btn);

        mRecycler_view = findViewById(R.id.check_recycler);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecycler_view.setLayoutManager(new VegaLayoutManager());
        //mRecycler_view.setLayoutManager(linearLayoutManager);
        checkList = new ArrayList<Check>();

        db = Room.databaseBuilder(this, AppDataBase.class, "plan_db")
                .allowMainThreadQueries()
                .addMigrations(MIGRATION_2_3)
                .build();

        getList();

        //db.todoDao().getAll().toString();


//        for(int i=0; i<db.todoDao().getAll().size();i++) {
//            Log.d("TAG","todoDao size : " + i + " 번째" + db.todoDao().getAll().get(i).getTitle());
//           // mResult.setText(db.todoDao().getAll().get(i).getTitle());
//        }


        // Add
        mAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                material = mEdit_txt.getText().toString();

                checkGb = 0;
                Check dept = new Check(material, checkGb);

                checkList.add(dept);


                mAdapter = new RoomRecyclerAdapter(RoomTestActivitiy.this, checkList);
                //mCustomAdapter.notifyDataSetChanged();
                mRecycler_view.setAdapter(mAdapter);


                Log.d("TAG", "새로 추가했을 떄!! =========>" + checkList.toString());


                //mResult.setText(db.todoDao().getAll().toString());
            }
        });


        mSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                int getRows = getNumFiles();
                Log.d("TAG", "새로 추가했을 떄!! checkList 갯수=========>" + checkList.size());        //3     개수는 3개  포지션은 0,1,2
                Log.d("TAG", "새로 추가했을 떄!! DB에 저장된 row갯수=========>" + getRows);            //0


                for (int i = getRows; i < checkList.size(); i++) {
                    if (checkList.size() > getRows) {
                        db.todoDao().insertTodo(new Plan(checkList.get(i).getCheckText(), checkList.get(i).getCheck()));
                        Log.d("TAG", "Save ===========> " + i + " 번째 " + checkList.get(i).toString());
                    } else {
                        Log.d("TAG", "Break!!! ===========>");
                        break;

                    }
                }



                /*
                db.todoDao().deleteTodo();
                checkList.clear();
                mAdapter = new RoomRecyclerAdapter(RoomTestActivitiy.this,checkList);
                mAdapter.notifyDataSetChanged();
                mRecycler_view.setAdapter(mAdapter);
                */

                /*
                for(int i=0; i<checkList.size();i++){
                    db.todoDao().insertTodo(new Todo2(checkList.get(i).getCheckText(),checkList.get(i).getCheck()));
                }*/
//                for(int i=0;i<checkList.size();i++){
//                    Log.d("TAG","checkList : "+ i +"번째"+ checkList.get(i));
//                    //Log.d("TAG","이미 저장된 값들"+ db.todoDao().getAll().get(i).getId());
////                    int result = i+1;
////                    if(result >= db.todoDao().getAll().get(i).getId()){
////                        db.todoDao().insertTodo(new Todo2(checkList.get(i).getCheckText(), checkList.get(i).getCheck()));
////
////                    }else{
////                        Log.d("TAG","이미 저장된 값들"+ db.todoDao().getAll().get(i).getId());
////                    }
////                    if(i == db.todoDao().getAll().get(i).getId()){
////                        Log.d("TAG","이미 저장된 값들"+ db.todoDao().getAll().get(i).getId());
////                    }else{
////                        db.todoDao().insertTodo(new Todo2(checkList.get(i).getCheckText(), checkList.get(i).getCheck()));
////                    }
//
//                }
                Toast.makeText(RoomTestActivitiy.this, "저장되었습니다.", Toast.LENGTH_SHORT).show();

                //db.todoDao().insertTodo(new Todo2(material, checkGb));
            }
        });

        runAnimation();

    }

    public void runAnimation() {
        final LayoutAnimationController controller =
                AnimationUtils.loadLayoutAnimation(RoomTestActivitiy.this, R.anim.layout_animation_fall_down);

        mRecycler_view.setLayoutAnimation(controller);
        mRecycler_view.getAdapter().notifyDataSetChanged();
        mRecycler_view.scheduleLayoutAnimation();


    }


    int getNumFiles() {
        return db.todoDao().getCount();
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


    @SuppressLint("RestrictedApi")
    @Subscribe
    public void onDelclick(final RoomRecyclerAdapter.BtnClickEvent event) {
        Log.d("TAG", "Delete 시작 ====>" + db.todoDao().getAll().get(event.position).getId());
        AlertDialog.Builder dialog = new AlertDialog.Builder(RoomTestActivitiy.this);
        dialog.setTitle("삭제 알림")
                .setMessage("정말 삭제하시겠습니까?")
                .setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        checkList.remove(event.position);
                        mAdapter.notifyItemRemoved(event.position);
                        mAdapter.notifyItemRangeChanged(event.position, checkList.size());

                        mAdapter = new RoomRecyclerAdapter(RoomTestActivitiy.this, checkList);
                        //mAdapter.update(checkList, event.position);
                        runAnimation();
                        db.todoDao().deletePlan(db.todoDao().getAll().get(event.position).getId());
                        Toast.makeText(RoomTestActivitiy.this, "삭제하였습니다", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("아니요", new DialogInterface.OnClickListener() {

                    @Override

                    public void onClick(DialogInterface dialog, int which) {

                        Toast.makeText(RoomTestActivitiy.this, "삭제하지 않습니다", Toast.LENGTH_SHORT).show();
                    }
                }).create().show();

    }


    // 보낸이 : MemoRecyclerAdapter
    @SuppressLint("RestrictedApi")
    @Subscribe
    public void onItemClick(RoomRecyclerAdapter.ItemClickEvent event) {

        Toast.makeText(getApplicationContext(), "" + (event.id),
                Toast.LENGTH_SHORT).show();
        if (checkList.get(event.position).getCheck() == 1) {
            checkGb = 0;
            chk_update(checkGb, event.position, event.id);

        } else {
            Log.d("TAG", "checkList checkGb = 0일때 !!");
            checkGb = 1;
            chk_update(checkGb, event.position, event.id);

            Log.d("TAG", "onItemClick : update!!!!!!" + db.todoDao().getAll().get(event.position).getCheckGb());
        }

        //mCustomAdapter.notifyDataSetChanged();
        mRecycler_view.setAdapter(mAdapter);


        Log.d("TAG", "onItemClick : " + checkList.get(event.position).getCheck());
        mAdapter.setChecked(event.position);
        // Data 변경시 호출 Adapter에 Data 변경 사실을 알려줘서 Update 함.
        //mAdapter.notifyDataSetChanged();

    }

    public void getList() {
        for (int i = 0; i < db.todoDao().getAll().size(); i++) {
            Log.d("TAG", "todoDao title : " + i + " 번째" + db.todoDao().getAll().get(i).getTitle());
            Log.d("TAG", "todoDao checkGb : " + i + " 번째" + db.todoDao().getAll().get(i).getCheckGb());
            Log.d("TAG", "todoDao Id : " + i + " 번째" + db.todoDao().getAll().get(i).getId());
            Check check = new Check(db.todoDao().getAll().get(i).getTitle(),
                    db.todoDao().getAll().get(i).getCheckGb());
            checkList.add(check);
            checkList.get(i).setId(db.todoDao().getAll().get(i).getId());

            Log.d("TAG", "checkList ====> " + checkList.toString());
            mAdapter = new RoomRecyclerAdapter(RoomTestActivitiy.this, checkList);
            mRecycler_view.setAdapter(mAdapter);

        }
    }


    public void chk_update(int chkgb, int position, long id) {
        checkGb = chkgb;
        Check check = new Check(checkList.get(position).getCheckText(), checkGb);
        checkList.set(position, check);
        //db.todoDao().updateTodo(new Todo2(checkList.get(event.position).getCheckText(),checkGb));
        Log.d("TAG", "update 확인 -=====> : " + id + " 번째" + checkList.get(position).getCheck() + " / " + db.todoDao().getAll().get(position).getTitle());

        db.todoDao().updateTodo2(checkList.get(position).getCheckText(), checkList.get(position).getCheck(), id);

        Log.d("TAG", "update 확인 -=====> : " + id + " 번째" + db.todoDao().getAll().get(position).getCheckGb() + " / " + db.todoDao().getAll().get(position).getTitle() + " / " + db.todoDao().getAll().get(position).getId());

        mAdapter = new RoomRecyclerAdapter(RoomTestActivitiy.this, checkList);

        mAdapter.update(checkList, position);
    }

    static final Migration MIGRATION_2_3 = new Migration(3, 4) {


        @Override
        public void migrate(SupportSQLiteDatabase database) {
            // Since we didn't alter the table, there's nothing else to do here.
        }
    };

}
