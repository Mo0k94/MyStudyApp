package com.example.mystudyapp.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;

public class Common {






    //ProgressDialog 공통
    public static class ProgressDialogHandler extends Handler {

        private final int START_PROGRESSDIALOG = 100;
        private final int END_PROGRESSDIALOG = 101;
        public ProgressDialog mProgressDialog = null;
        private Context context = null;

        public ProgressDialogHandler(Context context){
            this.context = context;
        }

        public void handleMessage(int gubun){
            switch (gubun){
                case START_PROGRESSDIALOG:
                    if(mProgressDialog == null){
                        mProgressDialog = new ProgressDialog(context);
                        mProgressDialog.setTitle("Working...");
                        mProgressDialog.setMessage("wait for complete working..");
                    }
                    mProgressDialog.show();
                    break;
                case END_PROGRESSDIALOG:
                    if(mProgressDialog != null){
                        mProgressDialog.dismiss();
                    }
                    break;
            }
        }
    }
}
