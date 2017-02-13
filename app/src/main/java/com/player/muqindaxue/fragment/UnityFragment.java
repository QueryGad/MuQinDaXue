package com.player.muqindaxue.fragment;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.player.muqindaxue.R;
import com.player.muqindaxue.activity.MainActivity;

/**
 * Created by Administrator on 2017/2/7.
 * 社区
 */

public class UnityFragment extends Fragment implements Animator.AnimatorListener{

    private View view;
    private ListView lv_unity;
    private RelativeLayout rl_unity;
    protected Context mContext;
    private View ll_unity;
    private boolean mIsTitleHide = false;
    private boolean mIsAnim = false;
    private float lastX = 0;
    private float lastY = 0;
    private boolean isDown = false;
    private boolean isUp = false;
    private ImageView iv_unity_title;
    private MainActivity.MyOnTouchListener myOnTouchListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frg_unity,null);
        initView();
        initListener();
        initData();
        return view;
    }

    private void initView() {
        lv_unity = (ListView) view.findViewById(R.id.lv_unity);
        ll_unity = view.findViewById(R.id.ll_unity);
        rl_unity = (RelativeLayout) view.findViewById(R.id.rl_unity);
        iv_unity_title = (ImageView) view.findViewById(R.id.iv_unity_title);
    }

    private void initListener() {
        myOnTouchListener = new MainActivity.MyOnTouchListener() {
            @Override
            public boolean dispatchTouchEvent(MotionEvent ev) {
                return dispathTouchEvent(ev);
            }
        };
        ((MainActivity) getActivity()).registerMyOnTouchListener(myOnTouchListener);
    }

    private void initData() {

    }

    private boolean dispathTouchEvent(MotionEvent event){
        if (mIsAnim) {
            return false;
        }
        final int action = event.getAction();

        float x = event.getX();
        float y = event.getY();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                lastY = y;
                lastX = x;
                return false;
            case MotionEvent.ACTION_MOVE:
                float dY = Math.abs(y - lastY);
                float dX = Math.abs(x - lastX);
                boolean down = y > lastY ? true : false;
                lastY = y;
                lastX = x;
                isUp = dX < 8 && dY > 8 && !mIsTitleHide && !down ;
                isDown = dX < 8 && dY > 8 && mIsTitleHide && down;
                if (isUp) {
                    View view = this.ll_unity;
                    float[] f = new float[2];
                    f[0] = 0.0F;
                    f[1] = -iv_unity_title.getHeight();
                    ObjectAnimator animator1 = ObjectAnimator.ofFloat(view, "translationY", f);
                    animator1.setInterpolator(new AccelerateDecelerateInterpolator());
                    animator1.setDuration(400);
                    animator1.start();
                    animator1.addListener(this);

                    setMarginTop(ll_unity.getHeight() - iv_unity_title.getHeight());
                } else if (isDown) {
                    View view = this.ll_unity;
                    float[] f = new float[2];
                    f[0] = -ll_unity.getHeight();
                    f[1] = 0F;
                    ObjectAnimator animator1 = ObjectAnimator.ofFloat(view, "translationY", f);
                    animator1.setDuration(600);
                    animator1.setInterpolator(new AccelerateDecelerateInterpolator());
                    animator1.start();
                    animator1.addListener(this);
                } else {
                    return false;
                }
                mIsTitleHide = !mIsTitleHide;
                mIsAnim = true;
                break;
            default:
                return false;
        }
        return false;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context.getApplicationContext();
    }

    public void setMarginTop(int page){
        RelativeLayout.LayoutParams layoutParam = new RelativeLayout.LayoutParams( RelativeLayout.LayoutParams.FILL_PARENT, RelativeLayout.LayoutParams.FILL_PARENT);
        layoutParam.setMargins(0, page, 0, 0);
        lv_unity.setLayoutParams(layoutParam);
        lv_unity.invalidate();
    }


    @Override
    public void onAnimationStart(Animator animator) {

    }

    @Override
    public void onAnimationEnd(Animator animator) {
        if(isDown){
            setMarginTop(ll_unity.getHeight());
        }
        mIsAnim = false;
    }

    @Override
    public void onAnimationCancel(Animator animator) {

    }

    @Override
    public void onAnimationRepeat(Animator animator) {

    }
}
