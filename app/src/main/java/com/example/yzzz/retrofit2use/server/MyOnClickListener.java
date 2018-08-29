package com.example.yzzz.retrofit2use.server;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.example.yzzz.retrofit2use.ShishenInfo;
import com.example.yzzz.retrofit2use.activity.ShishenInfoActivity;

import java.util.ArrayList;


public class MyOnClickListener implements AdapterView.OnItemClickListener {
    private Context context;
    private ArrayList<ShishenInfo> shiShenArrayList;
    String si;

    public MyOnClickListener(Context context, ArrayList<ShishenInfo> arrayList) {
        this.context = context;
        shiShenArrayList = arrayList;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        si = shiShenArrayList.get(position).getSkillIntroduction();

        if(si!=null) {
            Intent intent = new Intent(context, ShishenInfoActivity.class);
            intent.putExtra("info", si);
            context.startActivity(intent);
            Log.v("sisi",si);
        }

    }
}
