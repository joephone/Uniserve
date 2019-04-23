package com.transcendence.universe.utils;

import android.widget.Toast;

import com.transcendence.universe.service.AppAplication;


/**
 * Created by Administrator on 2016/5/9.
 */
public class T {
    public static void toast(String s){
        if(AppAplication.getInstance()!=null){
            Toast.makeText(AppAplication.getInstance(),s, Toast.LENGTH_SHORT).show();
        }
    }

    public static void toast(int string){
        if(AppAplication.getInstance()!=null){
            Toast.makeText(AppAplication.getInstance(), AppAplication.getInstance().getResources().getString(string), Toast.LENGTH_SHORT).show();
        }
    }

//    public static Toast makeText(Context context, CharSequence text1, CharSequence text2)
//    {
//
//        if(context==null){
//            return null;
//        }
//        Toast result = new Toast(context);
//
//        LayoutInflater inflate = (LayoutInflater) context
//                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View v = inflate.inflate(R.layout.custom_toast, null);
//        TextView tv1 = (TextView) v.findViewById(R.id.tv_toast1);
//        tv1.setText(text1);
//        if(text2!=null){
//            TextView tv2 = (TextView) v.findViewById(R.id.tv_toast2);
//            tv2.setVisibility(View.VISIBLE);
//            tv2.setText(text2);
//        }
//        result.setGravity(Gravity.CENTER, 0, 0);
//        result.setView(v);
//        result.setDuration(Toast.LENGTH_SHORT);
//        result.show();
//        return result;
//    }
}
