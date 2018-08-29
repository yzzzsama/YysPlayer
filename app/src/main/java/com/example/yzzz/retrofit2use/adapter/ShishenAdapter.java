package com.example.yzzz.retrofit2use.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yzzz.retrofit2use.R;
import com.example.yzzz.retrofit2use.ShishenInfo;

import java.util.ArrayList;

public class ShishenAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<ShishenInfo> al;

    public ShishenAdapter(Context context, ArrayList<ShishenInfo> arrayList) {
        this.context = context;
        al = arrayList;
    }


    @Override
    public int getCount() {
        return al.size();
    }

    @Override
    public Object getItem(int position) {
        return al.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ShishenAdapter.ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ShishenAdapter.ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_shishen, null);
            viewHolder.img = convertView.findViewById(R.id.img1);
            viewHolder.name = convertView.findViewById(R.id.tv1);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ShishenAdapter.ViewHolder) convertView.getTag();
        }


        Glide.with(context).load(al.get(position).getImageUrl()).into(viewHolder.img);
        viewHolder.name.setText(al.get(position).getName());
        return convertView;
    }

    private final class ViewHolder {
        ImageView img;
        TextView name;
    }
}
