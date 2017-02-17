package com.player.muqindaxue.fragment;

import android.content.Intent;
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
import com.player.muqindaxue.me.EditActivity;
import com.player.muqindaxue.me.MessageActivity;
import com.player.muqindaxue.me.SettingActivity;
import com.youth.banner.Banner;

/**
 * Created by Administrator on 2017/2/7.
 * 我的
 */

public class MeFragment extends Fragment implements View.OnClickListener {

    private View view;
    private ImageButton ib_me_setting,ib_me_msg;
    private ImageView iv_me_icon;
    private TextView tv_me_name,tv_me_id;
    private ImageView iv_me_edit;
    private GridView gv_me_function;
    private Banner banner_me;

    private int [] iconFunction = {R.mipmap.my_btn_gz,R.mipmap.my_btn_fs,R.mipmap.my_btn_qb,
            R.mipmap.my_btn_dd,R.mipmap.my_btn_kc,R.mipmap.my_btn_zb,R.mipmap.my_btn_sc,
            R.mipmap.my_btn_shop, R.mipmap.my_btn_yqhy,R.mipmap.my_btn_ft,};

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

        ib_me_setting.setOnClickListener(this);
        ib_me_msg.setOnClickListener(this);
        iv_me_edit.setOnClickListener(this);

        MeAdapter adapter = new MeAdapter(getActivity(),iconFunction,icontext);
        gv_me_function.setAdapter(adapter);
    }

    private void initData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ib_me_setting:
                Intent intent = new Intent(getActivity(), SettingActivity.class);
                startActivity(intent);
                break;
            case R.id.ib_me_msg:
                Intent intent1 = new Intent(getActivity(), MessageActivity.class);
                startActivity(intent1);
                break;
            case R.id.iv_me_edit:
                Intent intent2 = new Intent(getActivity(), EditActivity.class);
                startActivity(intent2);
                break;
        }
    }
}
