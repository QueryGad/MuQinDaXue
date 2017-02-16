package com.player.muqindaxue.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.facebook.rebound.SimpleSpringListener;
import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringSystem;
import com.google.gson.Gson;
import com.player.muqindaxue.R;
import com.player.muqindaxue.adapter.UnityAdapter;
import com.player.muqindaxue.bean.HotArticleBean;
import com.player.muqindaxue.unity.ActivityActivity;
import com.player.muqindaxue.unity.ConsultActivity;
import com.player.muqindaxue.unity.FrendActivity;
import com.player.muqindaxue.unity.MyCirleActivity;
import com.player.muqindaxue.utils.ConfigUtils;
import com.player.muqindaxue.utils.PrefUtils;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.rest.OnResponseListener;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.RequestQueue;
import com.yolanda.nohttp.rest.Response;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/7.
 * 社区
 */

public class UnityFragment extends Fragment{

    private static final int GET_UNITY_DATA = 001;
    private View view;
    private ListView lv_unity;
    private View headView;
    private RequestQueue requestQueue;
    private String apptoken;
    private List<HotArticleBean.TrendsBean> trendsList = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frg_unity,null);
        requestQueue = NoHttp.newRequestQueue();
        initView();
        initListener();
        initData();
        return view;
    }

    private void initView() {
        lv_unity = (ListView) view.findViewById(R.id.lv_unity);

    }

    private void initListener() {

    }

    private void initData() {
          netWork();
    }

    private void netWork() {
        apptoken = PrefUtils.getString(getActivity(), "apptoken", "");
        Request<String> request = NoHttp.createStringRequest(ConfigUtils.UNITY_URL);
        request.add("op","retie");
        request.add("apptoken",apptoken);
        request.add("lastIndex","0");
        requestQueue.add(GET_UNITY_DATA, request, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {

            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                String info = response.get();
                if (info!=null){
                    parseJson(info);
                }
            }

            @Override
            public void onFailed(int what, Response<String> response) {

            }

            @Override
            public void onFinish(int what) {

            }
        });
        initHead();
    }

    private void parseJson(String info) {
        Gson gson = new Gson();
        HotArticleBean hotArticleBean = gson.fromJson(info, HotArticleBean.class);
        trendsList = hotArticleBean.getTrends();
        UnityAdapter adapter = new UnityAdapter(getActivity(),trendsList);
        lv_unity.setAdapter(adapter);
    }

    private void initHead() {
        headView = View.inflate(getActivity(),R.layout.head_unity,null);
        Banner banner_unity = (Banner) headView.findViewById(R.id.banner_unity);

        final ImageView iv_unity_cirle = (ImageView) headView.findViewById(R.id.iv_unity_cirle);
        final ImageView iv_unity_consult = (ImageView) headView.findViewById(R.id.iv_unity_consult);
        final ImageView iv_unity_act = (ImageView) headView.findViewById(R.id.iv_unity_act);
        final ImageView iv_unity_frend = (ImageView) headView.findViewById(R.id.iv_unity_frend);

        //我的圈子
        iv_unity_cirle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SpringSystem springSystem = SpringSystem.create();
                Spring spring = springSystem.createSpring();
                spring.addListener(new SimpleSpringListener(){
                    @Override
                    public void onSpringUpdate(Spring spring) {
                        float value = (float) spring.getCurrentValue();
                        float scale = 1f - (value * 0.3f);
                        iv_unity_cirle.setScaleX(scale);
                        iv_unity_cirle.setScaleY(scale);
                    }
                    @Override
                    public void onSpringAtRest(Spring spring) {
                        float value = (float) spring.getCurrentValue();
                        iv_unity_cirle.setScaleX(value);
                        iv_unity_cirle.setScaleY(value);
                    }
                });
                spring.setEndValue(1);
                //圈子
                Intent intent1 = new Intent(getActivity(), MyCirleActivity.class);
                startActivity(intent1);
            }
        });

        //咨询
        iv_unity_consult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SpringSystem springSystem = SpringSystem.create();
                Spring spring = springSystem.createSpring();
                spring.addListener(new SimpleSpringListener(){
                    @Override
                    public void onSpringUpdate(Spring spring) {
                        float value = (float) spring.getCurrentValue();
                        float scale = 1f - (value * 0.3f);
                        iv_unity_consult.setScaleX(scale);
                        iv_unity_consult.setScaleY(scale);
                    }
                    @Override
                    public void onSpringAtRest(Spring spring) {
                        float value = (float) spring.getCurrentValue();
                        iv_unity_consult.setScaleX(value);
                        iv_unity_consult.setScaleY(value);
                    }
                });
                spring.setEndValue(1);
                //咨询
                Intent intent2 = new Intent(getActivity(), ConsultActivity.class);
                startActivity(intent2);
            }
        });

        //活动
        iv_unity_act.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SpringSystem springSystem = SpringSystem.create();
                Spring spring = springSystem.createSpring();
                spring.addListener(new SimpleSpringListener(){
                    @Override
                    public void onSpringUpdate(Spring spring) {
                        float value = (float) spring.getCurrentValue();
                        float scale = 1f - (value * 0.3f);
                        iv_unity_act.setScaleX(scale);
                        iv_unity_act.setScaleY(scale);
                    }
                    @Override
                    public void onSpringAtRest(Spring spring) {
                        float value = (float) spring.getCurrentValue();
                        iv_unity_act.setScaleX(value);
                        iv_unity_act.setScaleY(value);
                    }
                });
                spring.setEndValue(1);
                //活动
                Intent intent3 = new Intent(getActivity(), ActivityActivity.class);
                startActivity(intent3);
            }
        });

        //好友
        iv_unity_frend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SpringSystem springSystem = SpringSystem.create();
                Spring spring = springSystem.createSpring();
                spring.addListener(new SimpleSpringListener(){
                    @Override
                    public void onSpringUpdate(Spring spring) {
                        float value = (float) spring.getCurrentValue();
                        float scale = 1f - (value * 0.3f);
                        iv_unity_frend.setScaleX(scale);
                        iv_unity_frend.setScaleY(scale);
                    }
                    @Override
                    public void onSpringAtRest(Spring spring) {
                        float value = (float) spring.getCurrentValue();
                        iv_unity_frend.setScaleX(value);
                        iv_unity_frend.setScaleY(value);
                    }
                });
                spring.setEndValue(1);
                //好友
                Intent intent4 = new Intent(getActivity(), FrendActivity.class);
                startActivity(intent4);
            }
        });

        lv_unity.addHeaderView(headView);
    }
}
