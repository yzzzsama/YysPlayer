package com.example.yzzz.retrofit2use.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yzzz.retrofit2use.R;
import com.example.yzzz.retrofit2use.ShishenInfo;
import com.example.yzzz.retrofit2use.YysService;
import com.example.yzzz.retrofit2use.server.HttpClientGenerator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ShishenInfoActivity extends AppCompatActivity {
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.cvname)
    TextView cvName;
    @BindView(R.id.rarity)
    TextView rarity;
    @BindView(R.id.type)
    TextView type;
    @BindView(R.id.pvpPower)
    TextView pvpPower;
    @BindView(R.id.img2)
    ImageView imageView;


    String sname;
    String scvName;
    String srarity;
    String stype;
    String spvpPower;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shishen_info);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String si = intent.getStringExtra("info");

        initData(si);

    }

    private void initData(String url) {

        YysService yysService = HttpClientGenerator.getYysService(url + "/");

        Call<ResponseBody> call = yysService.getIntroduction(url);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                }
                try {
                    Document document = Jsoup.parse(response.body().string());

                    Element baseInfo = document.getElementsByTag("table").first();
                    String imageSrc = baseInfo.getElementsByTag("img").attr("src");
                    Elements info = baseInfo.getElementsByTag("tr");

                    String all = info.text();//参数
                    all.trim();

                    String[] str = all.split(" ");

                    sname = str[1].toString();//式神名字
                    scvName = str[3].toString();//CV名字
                    srarity = str[5].toString();//稀有度
                    stype = str[7].toString();//式神类型
                    spvpPower = str[9].toString();//斗技指数

                    name.setText(sname);//式神名字
                    cvName.setText(scvName);//CV名字
                    rarity.setText(srarity);//稀有度
                    type.setText(stype);//式神类型
                    pvpPower.setText(spvpPower);//斗技指数

                    Glide.with(ShishenInfoActivity.this)
                            .load("http:"+imageSrc)
                            .into(imageView);



//                    Log.v("iiii", "name" + sname + "/cvname" + scvName + "/rarity" + srarity + "/type" + stype + "/pvpPower" + spvpPower);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.v("false", "xxxx");
            }

        });


    }
}
