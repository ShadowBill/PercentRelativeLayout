package com.mega.viewpager;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

/**
 * Created by shadow. on 2016/9/24 0024.
 */

public class WelcomActivity extends Activity {
    private final static int GO_HOME = 1;
    private final static int GO_GUIDE = 2;
    private final static int TIME = 1000;
    private boolean isFirst = true;

    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case GO_HOME:
                    startActivity(new Intent(WelcomActivity.this,MainActivity.class));
                    finish();
                break;
                case GO_GUIDE:
                    startActivity(new Intent(WelcomActivity.this,Guide.class));
                    finish();
                    break;
                default:
                break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcom_activity);
        SharedPreferences sp = getSharedPreferences("jike", MODE_PRIVATE);
        isFirst = sp.getBoolean("isFirst", true);
        if (!isFirst) {
            mHandler.sendEmptyMessageDelayed(GO_HOME, TIME);
        } else {
            mHandler.sendEmptyMessageDelayed(GO_GUIDE, TIME);
            SharedPreferences.Editor edit = sp.edit();
            edit.putBoolean("isFirst", true);
            edit.commit();
        } 
    }
}
