package com.example.yzzz.retrofit2use.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.yzzz.retrofit2use.R;

public class SpalashActivity extends AppCompatActivity {
    private Handler mHandler = new Handler();
    private Intent mIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spalash);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }

        //延时
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mIntent = new Intent(SpalashActivity.this, HomeActivity.class);
                startActivity(mIntent);
                finish();
            }
        }, 3000);//3000ms
    }


}
