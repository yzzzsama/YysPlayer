package com.example.yzzz.retrofit2use.server;

import com.example.yzzz.retrofit2use.YysService;

import retrofit2.Retrofit;

public class HttpClientGenerator {
    private static Retrofit retrofit;
    private static YysService yysService;

    public static YysService getYysService(String url){
        if (yysService==null){
            yysService=getRetrofit(url).create(YysService.class);
        }
        return yysService;
    }

    private static Retrofit getRetrofit(String url){
        if (retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .build();
        }
        return retrofit;
    }
}
