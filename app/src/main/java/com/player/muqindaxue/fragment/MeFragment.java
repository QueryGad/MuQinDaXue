package com.player.muqindaxue.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.player.muqindaxue.R;
import com.player.muqindaxue.adapter.MeAdapter;
import com.youth.banner.Banner;

/**
 * Created by Administrator on 2017/2/7.
 * 我的
 */

public class MeFragment extends Fragment{

    private View view;
    private ImageButton ib_me_setting,ib_me_msg;
    private ImageView iv_me_icon;
    private TextView tv_me_name,tv_me_id;
    private ImageView iv_me_edit;
    private GridView gv_me_function;
    private Banner banner_me;

    private int [] iconFunction = {R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,
            R.mipmap.ic_launcher, R.mipmap.ic_launcher,R.mipmap.ic_launcher,};

    private String [] icontext = {"关注","粉丝","我的钱包","我的订单","我的课程","我的直播",
            "我的收藏","商城","邀请好友","我的福田",};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frg_me,null);
        initView();
        initData();
        return view;
    }

    private void initView() {
        ib_me_setting = (ImageButton) view.findViewById(R.id.ib_me_setting);
        ib_me_msg = (ImageButton) view.findViewById(R.id.ib_me_msg);
        iv_me_icon = (ImageView) view.findViewById(R.id.iv_me_icon);
        tv_me_name = (TextView) view.findViewById(R.id.tv_me_name);
        tv_me_id = (TextView) view.findViewById(R.id.tv_me_id);
        iv_me_edit = (ImageView) view.findViewById(R.id.iv_me_edit);
        gv_me_function = (GridView) view.findViewById(R.id.gv_me_function);
        banner_me = (Banner) view.findViewById(R.id.banner_me);

        MeAdapter adapter = new MeAdapter(getActivity(),iconFunction,icontext);
        gv_me_function.setAdapter(adapter);
    }

    private void initData() {

    }
}
