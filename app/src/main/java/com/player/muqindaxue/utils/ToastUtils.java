package com.player.muqindaxue.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/2/7.
 */

public class ToastUtils {
    public static void showToast(Context ctx,String info){
        if (info==null){
            return;
        }
        Toast toast = Toast.makeText(ctx,info,Toast.LENGTH_SHORT);
//        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }
}
