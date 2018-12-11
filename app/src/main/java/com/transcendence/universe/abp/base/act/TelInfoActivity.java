package com.transcendence.universe.abp.base.act;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.widget.TextView;

import com.transcendence.universe.R;
import com.transcendence.universe.abp.main.act.TitleBarActivity;
import com.transcendence.universe.utils.Global;
import com.transcendence.universe.utils.base.TelephoneUtil;
import com.transcendence.universe.utils.system.ToastHelper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Joephone on 2018/10/24 17:41
 * E-Mail Address：joephonechen@gmail.com
 * 电话工具类，手机号、运营商、IMEI、IMSI等信息
 */

public class TelInfoActivity extends TitleBarActivity {

    @Bind(R.id.tvIMSI)
    TextView tvIMSI;
    @Bind(R.id.tvIMEI)
    TextView tvIMEI;
    @Bind(R.id.tvTelInfo)
    TextView tvTelInfo;
    @Bind(R.id.tvInfo1)
    TextView tvInfo1;
    @Bind(R.id.tvInfo2)
    TextView tvInfo2;

    String ISDOUBLE;
    String SIMCARD;
    String SIMCARD_1;
    String SIMCARD_2;
    boolean isDouble;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tel_info);
        ButterKnife.bind(this);
        setTitle("TelInfo信息");
        init();
    }

    protected void init() {
        tvIMSI.setText(TelephoneUtil.getIMSI(this));
        tvIMEI.setText(TelephoneUtil.getIMEI(this));
        tvTelInfo.setText(TelephoneUtil.printTelephoneInfo(this));

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN) {
            if (checkPermission(Manifest.permission.READ_PHONE_STATE)) {
                getNumber();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, Global.REQUEST_PERMISSION.REQUEST_PERMISSION_READ_PHONE_STATE);
            }
        } else {
            getNumber();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == Global.REQUEST_PERMISSION.REQUEST_PERMISSION_READ_PHONE_STATE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getNumber();
            } else {
                ToastHelper.getInstance().showToast("权限被禁止，无法选择本地图片");
            }
        }
    }


    private void getNumber() {

        TelephonyManager tm = (TelephonyManager) this.getSystemService(this.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
            String phoneNumber1 = tm.getLine1Number();
            initIsDoubleTelephone(this);
            if (isDouble) {
                tvInfo1.setText("本机号码是：" + "   " + phoneNumber1 + "   " + "这是双卡手机！");
            } else {
                tvInfo1.setText("本机号码是：" + "   " + phoneNumber1 + "   " + "这是单卡手机");
            }
            return;
        }
        // String phoneNumber2 = tm.getGroupIdLevel1();
    }


    public void initIsDoubleTelephone(Context context) {
        isDouble = true;
        Method method = null;
        Object result_0 = null;
        Object result_1 = null;
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        try {
            // 只要在反射getSimStateGemini 这个函数时报了错就是单卡手机（这是我自己的经验，不一定全正确）
            method = TelephonyManager.class.getMethod("getSimStateGemini", new Class[]
                    {int.class});
            // 获取SIM卡1
            result_0 = method.invoke(tm, new Object[]
                    {new Integer(0)});
            // 获取SIM卡2
            result_1 = method.invoke(tm, new Object[]
                    {new Integer(1)});
        } catch (SecurityException e) {
            isDouble = false;
            e.printStackTrace();
            // System.out.println("1_ISSINGLETELEPHONE:"+e.toString());
        } catch (NoSuchMethodException e) {
            isDouble = false;
            e.printStackTrace();
            // System.out.println("2_ISSINGLETELEPHONE:"+e.toString());
        } catch (IllegalArgumentException e) {
            isDouble = false;
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            isDouble = false;
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            isDouble = false;
            e.printStackTrace();
        } catch (Exception e) {
            isDouble = false;
            e.printStackTrace();
            // System.out.println("3_ISSINGLETELEPHONE:"+e.toString());
        }
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sp.edit();
        if (isDouble) {
            // 保存为双卡手机
            editor.putBoolean(ISDOUBLE, true);
            // 保存双卡是否可用
            // 如下判断哪个卡可用.双卡都可以用
            if (result_0.toString().equals("5") && result_1.toString().equals("5")) {
                if (!sp.getString(SIMCARD, "2").equals("0") && !sp.getString(SIMCARD, "2").equals("1")) {
                    editor.putString(SIMCARD, "0");
                }
                editor.putBoolean(SIMCARD_1, true);
                editor.putBoolean(SIMCARD_2, true);

                tvInfo2.setText("双卡可用");

            } else if (!result_0.toString().equals("5") && result_1.toString().equals("5")) {// 卡二可用
                if (!sp.getString(SIMCARD, "2").equals("0") && !sp.getString(SIMCARD, "2").equals("1")) {
                    editor.putString(SIMCARD, "1");
                }
                editor.putBoolean(SIMCARD_1, false);
                editor.putBoolean(SIMCARD_2, true);

                tvInfo2.setText("卡二可用");

            } else if (result_0.toString().equals("5") && !result_1.toString().equals("5")) {// 卡一可用
                if (!sp.getString(SIMCARD, "2").equals("0") && !sp.getString(SIMCARD, "2").equals("1")) {
                    editor.putString(SIMCARD, "0");
                }
                editor.putBoolean(SIMCARD_1, true);
                editor.putBoolean(SIMCARD_2, false);

                tvInfo2.setText("卡一可用");

            } else {// 两个卡都不可用(飞行模式会出现这种种情况)
                editor.putBoolean(SIMCARD_1, false);
                editor.putBoolean(SIMCARD_2, false);

                tvInfo2.setText("飞行模式");
            }
        } else {
            // 保存为单卡手机
            editor.putString(SIMCARD, "0");
            editor.putBoolean(ISDOUBLE, false);
        }
        editor.commit();
    }
}
