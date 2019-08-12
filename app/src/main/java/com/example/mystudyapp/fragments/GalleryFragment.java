package com.example.mystudyapp.fragments;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mystudyapp.R;

public class GalleryFragment extends Fragment {


    private OnFragmentInteractionListener mListener;
    private GridView mGridView;

    private final int MY_PERMISSIONS_REQUEST_GALLERY = 1001;

    public GalleryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);


        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    //Bundle 받는 곳
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_gallery, container, false);
    }



    //뷰 가져온 이후 할 곳
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        // 뷰
        mGridView = (GridView) view.findViewById(R.id.grid_view);

        if(ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){

            //설명을 보여줄 것인가
            if(ActivityCompat.shouldShowRequestPermissionRationale(getActivity()
                    ,Manifest.permission.READ_EXTERNAL_STORAGE)){

                // 사용자 응답을 기다리는 설명을 비동기로 보여주기
                // 권한 체크를 안 하면 이 기능을 사용할 수 없다고 어필하고

                // 다이얼로그 표시
                // 이 권한을 수락하지 않으면 이 기능을 사용할 수 없습니다.ㅣ
                // 권한을 설정하시려면 설정 > 애플리케이션 > 앱이름 가서 설정하시오

                // 다시 권한 요청

                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}
                        ,1000);
            }else{
                //권한을 요청
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        1000);
            }
        }else{

            // 이미 권한이 존재할 때
            getPicture();
        }




    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 1000:{
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                    //승인 됨
                    Toast.makeText(getActivity(),"권한 승인됨", Toast.LENGTH_LONG).show();
                    getPicture();
                }else{


                    // 앱을 종료함
                    //승인 거부됨
                    Toast.makeText(getActivity(),"권한 거부됨", Toast.LENGTH_LONG).show();
                    getActivity().finish();
                }
                return;
            }
        }
    }

    private void getPicture() {
        // 사진 정보
        // 미디어(사진 , 동영상, 음악) media db에 존재
        // provider로 media db 정보를 가져와야함
        Cursor cursor = getActivity().getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null,
                null,
                null,
                null
        );
        // 사진을 뿌릴 Adapter
        MyCursorAdapter adapter = new MyCursorAdapter(getActivity(),cursor);
        mGridView.setAdapter(adapter);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private static class MyCursorAdapter extends CursorAdapter {


        public MyCursorAdapter(Context context, Cursor c) {
            super(context, c, false);
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {

            View convertView = LayoutInflater.from(context)
                    .inflate(R.layout.item_gallery, parent, false);

            ViewHolder viewHolder = new ViewHolder();
            viewHolder.image_view = (ImageView) convertView.findViewById(R.id.image_view);
            convertView.setTag(viewHolder);

            return convertView;
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            ViewHolder viewHolder = (ViewHolder) view.getTag();

            String path = cursor.getString(cursor.getColumnIndexOrThrow(
                    MediaStore.Images.Media.DATA
            ));
            viewHolder.image_view.setImageURI(Uri.parse(path));
        }
    }

    private static class ViewHolder {
        ImageView image_view;
    }



}
