package com.player.muqindaxue.application;

import android.app.Application;

import com.yolanda.nohttp.NoHttp;

/**
 * Created by Administrator on 2017/2/7.
 */

public class MyApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        initInternet();
    }

    private void initInternet() {
        NoHttp.initialize(this);
    }
}
