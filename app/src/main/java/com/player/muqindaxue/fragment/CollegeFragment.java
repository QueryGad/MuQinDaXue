package com.player.muqindaxue.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.player.muqindaxue.R;
import com.player.muqindaxue.college.ClassListFragment;
import com.player.muqindaxue.college.LiveListFragment;
import com.player.muqindaxue.college.OriginalFragment;
import com.player.muqindaxue.college.ReadBookListFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/7.
 * 学院
 */

public class CollegeFragment extends Fragment{

    private View view;
    private TabLayout tab_unity;
    private ViewPager vp_unity;
    private List<String> list_title = new ArrayList<>();
    private List<Fragment> list_fragment = new ArrayList<>();

    private LiveListFragment liveFragment;
    private ReadBookListFragment readbookFragment;
    private ClassListFragment classFragment;
    private OriginalFragment originalFragment;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frg_college,null);
        initView();
        initListener();
        initData();
        return view;
    }

    private void initView() {
        tab_unity = (TabLayout) view.findViewById(R.id.tab_unity);
        vp_unity = (ViewPager) view.findViewById(R.id.vp_unity);
        liveFragment = new LiveListFragment();
        readbookFragment = new ReadBookListFragment();
        classFragment = new ClassListFragment();
        originalFragment = new OriginalFragment();
    }

    private void initListener() {

    }

    private void initData() {
        initViewPager();
    }

    private void initViewPager() {
        list_title.clear();
        list_fragment.clear();
        list_fragment.add(liveFragment);
        list_fragment.add(readbookFragment);
        list_fragment.add(classFragment);
        list_fragment.add(originalFragment);
        list_title.add("直播");
        list_title.add("读书");
        list_title.add("课堂");
        list_title.add("原创");
        tab_unity.setTabMode(TabLayout.MODE_FIXED);
        tab_unity.addTab(tab_unity.newTab()
                .setText(list_title.get(0)));
        tab_unity.addTab(tab_unity.newTab()
                .setText(list_title.get(1)));
        tab_unity.addTab(tab_unity.newTab()
                .setText(list_title.get(2)));
        tab_unity.addTab(tab_unity.newTab()
                .setText(list_title.get(3)));

        tab_unity.post(new Runnable() {
            @Override
            public void run() {
                tab_unity.setupWithViewPager(vp_unity);
            }
        });

        MyTabPagerAdapter adapter = new MyTabPagerAdapter(getChildFragmentManager());
        vp_unity.setAdapter(adapter);
        tab_unity.setupWithViewPager(vp_unity);
    }

    class MyTabPagerAdapter extends FragmentPagerAdapter {

        public MyTabPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return list_fragment.get(position);
        }

        @Override
        public int getCount() {
            return list_fragment.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return list_title.get(position);
        }
    }


}
