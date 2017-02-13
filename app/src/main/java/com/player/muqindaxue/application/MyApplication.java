package com.player.muqindaxue.application;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.lzy.ninegrid.NineGridView;
import com.player.muqindaxue.R;
import com.yolanda.nohttp.NoHttp;

/**
 * Created by Administrator on 2017/2/7.
 */

public class MyApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        initInternet();
        initFrendCirle();
    }

    private void initInternet() {
        NoHttp.initialize(this);
    }

    private void initFrendCirle() {
//        Glide.
        NineGridView.setImageLoader(new NineGridView.ImageLoader() {
            @Override
            public void onDisplayImage(Context context, ImageView imageView, String url) {
                Glide.with(context).load(url)
                        .placeholder(R.drawable.ic_default_color)//
                        .error(R.drawable.ic_default_color)//
                        .into(imageView);
            }

            @Override
            public Bitmap getCacheImage(String url) {
                return null;
            }
        });
    }
}
