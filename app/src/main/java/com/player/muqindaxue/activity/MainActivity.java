package com.player.muqindaxue.activity;

import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.player.muqindaxue.R;
import com.player.muqindaxue.fragment.CollegeFragment;
import com.player.muqindaxue.fragment.HomeFragment;
import com.player.muqindaxue.fragment.MeFragment;
import com.player.muqindaxue.fragment.UnityFragment;

/**
 * Created by Administrator on 2017/2/7.
 */

public class MainActivity extends BaseActivity{

    private FrameLayout fl_main;
    private RadioGroup rg_main;
    private RadioButton rb_main_one,rb_main_two,rb_main_three,rb_main_four;
    private HomeFragment homeFragment;
    private CollegeFragment collegeFragment;
    private UnityFragment unityFragment;
    private MeFragment meFragment;
    private RadioGroup.OnCheckedChangeListener MainCheckedListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            switch (i){
                case R.id.rb_main_one:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fl_main,homeFragment).commit();
                    break;
                case R.id.rb_main_two:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fl_main,collegeFragment).commit();
                    break;
                case R.id.rb_main_three:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fl_main,unityFragment).commit();
                    break;
                case R.id.rb_main_four:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fl_main,meFragment).commit();
                    break;
            }
        }
    };

    @Override
    public void setContentView() {
        setContentView(R.layout.act_main);
    }

    @Override
    public void initViews() {
        fl_main = (FrameLayout) findViewById(R.id.fl_main);
        rg_main = (RadioGroup) findViewById(R.id.rg_main);
        rb_main_one = (RadioButton) findViewById(R.id.rb_main_one);
        rb_main_two = (RadioButton) findViewById(R.id.rb_main_two);
        rb_main_three = (RadioButton) findViewById(R.id.rb_main_three);
        rb_main_four = (RadioButton) findViewById(R.id.rb_main_four);

    }

    @Override
    public void initListeners() {
        rg_main.setOnCheckedChangeListener(MainCheckedListener);
    }

    @Override
    public void initData() {
        homeFragment = new HomeFragment();
        collegeFragment = new CollegeFragment();
        unityFragment = new UnityFragment();
        meFragment = new MeFragment();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fl_main,homeFragment).commit();
        rb_main_one.setChecked(true);

    }
}
