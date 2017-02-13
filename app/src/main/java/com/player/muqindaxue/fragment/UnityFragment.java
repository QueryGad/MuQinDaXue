package com.player.muqindaxue.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

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
    private RadioGroup.OnCheckedChangeListener UnityCheckListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            switch (i){
                case R.id.rb_unity_cirle:
                    //圈子
                    Intent intent1 = new Intent(getActivity(), MyCirleActivity.class);
                    startActivity(intent1);
                    break;
                case R.id.rb_unity_consult:
                    //咨询
                    Intent intent2 = new Intent(getActivity(), ConsultActivity.class);
                    startActivity(intent2);
                    break;
                case R.id.rb_unity_activity:
                    //活动
                    Intent intent3 = new Intent(getActivity(), ActivityActivity.class);
                    startActivity(intent3);
                    break;
                case R.id.rb_unity_frend:
                    //好友
                    Intent intent4 = new Intent(getActivity(), FrendActivity.class);
                    startActivity(intent4);
                    break;

            }
        }
    };


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
        RadioGroup rg_unity = (RadioGroup) headView.findViewById(R.id.rg_unity);
        RadioButton rb_unity_cirle = (RadioButton) headView.findViewById(R.id.rb_unity_cirle);
        RadioButton rb_unity_consult = (RadioButton) headView.findViewById(R.id.rb_unity_consult);
        RadioButton rb_unity_activity = (RadioButton) headView.findViewById(R.id.rb_unity_activity);
        RadioButton rb_unity_frend = (RadioButton) headView.findViewById(R.id.rb_unity_frend);
        rg_unity.setOnCheckedChangeListener(UnityCheckListener);

        lv_unity.addHeaderView(headView);
    }
}
