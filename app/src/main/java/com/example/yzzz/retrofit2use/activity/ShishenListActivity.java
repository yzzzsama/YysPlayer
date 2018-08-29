package com.example.yzzz.retrofit2use.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import com.bumptech.glide.Glide;
import com.example.yzzz.retrofit2use.Constanst;
import com.example.yzzz.retrofit2use.R;
import com.example.yzzz.retrofit2use.ShishenInfo;
import com.example.yzzz.retrofit2use.YysService;
import com.example.yzzz.retrofit2use.adapter.ShishenAdapter;
import com.example.yzzz.retrofit2use.server.HttpClientGenerator;
import com.example.yzzz.retrofit2use.server.MyOnClickListener;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ShishenListActivity extends AppCompatActivity {

    @BindView(R.id.gdv1)
    GridView gridView;

    private ArrayList<ShishenInfo> shishen = new ArrayList<>();
    private ShishenAdapter shishenAdapter;

    private String shishentype;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        shishentype = intent.getStringExtra("type");//获得列表类型,

        initData();

        shishenAdapter = new ShishenAdapter(this, shishen);//适配器

        gridView.setAdapter(shishenAdapter);

        gridView.setOnItemClickListener(new MyOnClickListener(ShishenListActivity.this, shishen));


    }

    private void initData() {

        YysService yysService = HttpClientGenerator.getYysService(Constanst.baseurl);

        Call<ResponseBody> call = yysService.getInfo();

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        Document doc = Jsoup.parse(response.body().string());
                        Element element = doc.getElementsByClass("w-pet-wrap clear").first();
                        Element powerElement = element.getElementsByClass("r-handbook-list clear").get(0);
                        Elements links = powerElement.getElementsByTag("li");

                        for (Element link : links) {

                            String name = link.text();//式神名称
                            String imageSrc = link.getElementsByTag("img").attr("src");//式神图片地址
                            String type = link.getElementsByTag("li").attr("data-value");//式神类型
                            String skillIntroduction = link.getElementsByTag("a").attr("href");//详情页地址


                            if (shishentype.contains(type)) {
                                ShishenInfo shishenInfo = new ShishenInfo(name, imageSrc, type, skillIntroduction);
                                shishen.add(shishenInfo);

                                Glide.with(ShishenListActivity.this)
                                   .load(imageSrc)
                                   .preload();
                            }

                        }

                        shishenAdapter.notifyDataSetChanged();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }


        });
    }


}
