package com.transcendence.universe.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;



/**
 * Created by Administrator on 2016/5/9.
 */
public class ToastUtils {
    public static void toast(Context context, String s){
        if(context!=null){
            Toast.makeText(context,s, Toast.LENGTH_SHORT).show();
        }
    }


}
