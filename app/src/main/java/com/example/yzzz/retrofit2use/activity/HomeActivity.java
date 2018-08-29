package com.example.yzzz.retrofit2use.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.yzzz.retrofit2use.Constanst;
import com.example.yzzz.retrofit2use.R;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3,R.id.btn4})
    void onclick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                initIntent(Constanst.ssr);
                break;
            case R.id.btn2:
                initIntent(Constanst.sr);
                break;
            case R.id.btn3:
                initIntent(Constanst.r);
                break;
            case R.id.btn4:
                initIntent(Constanst.n);
                break;
        }
    }

    private void initIntent(String type) {
        Intent intent = new Intent(HomeActivity.this, ShishenListActivity.class);
        intent.putExtra("type", type);
        startActivity(intent);
    }
}
