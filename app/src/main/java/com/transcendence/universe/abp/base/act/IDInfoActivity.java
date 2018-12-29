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
import com.transcendence.universe.utils.IDUtil;
import com.transcendence.universe.utils.base.TelephoneUtil;
import com.transcendence.universe.utils.system.ToastHelper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.SocketException;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Joephone on 2018/10/24 17:41
 * E-Mail Address：joephonechen@gmail.com
 * 电话工具类，手机号、运营商、IMEI、IMSI等信息
 */

public class IDInfoActivity extends TitleBarActivity {

    TextView tvContent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.uui_util);
        ButterKnife.bind(this);
        setTitle("UUID信息");
        init();
    }

    protected void init() {
        tvContent = findViewById(R.id.tv_content);
//        tvIMSI.setText(TelephoneUtil.getIMSI(this));
//        tvIMEI.setText(TelephoneUtil.getIMEI(this));
//        tvTelInfo.setText(TelephoneUtil.printTelephoneInfo(this));
//
//        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN) {
//            if (checkPermission(Manifest.permission.READ_PHONE_STATE)) {
//                getNumber();
//            } else {
//                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, Global.REQUEST_PERMISSION.REQUEST_PERMISSION_READ_PHONE_STATE);
//            }
//        } else {
//            getNumber();
//        }
        update();
    }



    private void update() {
        StringBuffer build= new StringBuffer();
        build.append("Device ID:").append("\n");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && checkSelfPermission(Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
            build.append(IDUtil.getDeviceId(this));
        }
        build.append("\n\n");
        build.append("Wifi Mac Address (System API):").append("\n");
        build.append(IDUtil.getWifiMacAddress(this));
        build.append("\n\n");
        build.append("Wifi Mac Address (Scan Interface):").append("\n");
        try {
            build.append(IDUtil.getWifiMacAddress());
        } catch (SocketException e) {
            e.printStackTrace();
        }
        build.append("\n\n");
        build.append("Wifi Mac Address (Analyze IP):").append("\n");
        build.append(IDUtil.getWifiMacAddressByIp());
        build.append("\n\n");
        build.append("Android Id:").append("\n");
        build.append(IDUtil.getAndroidId(this));
        build.append("\n\n");
        build.append("Build Info:").append("\n");
        build.append(IDUtil.getBuildInfo());
        build.append("\n\n");
        build.append("Device UUID:").append("\n");
        build.append(IDUtil.getDeviceUUID(this));
        build.append("\n\n");
        build.append("=========================================");
        build.append("\n\n");
        build.append("Encrypt UUID:").append("\n");
//        String encryptedUUID = UUIDUtil.getEncryptedUUID(this);
//        Log.d("uuid", encryptedUUID)
//        build.append(encryptedUUID)
//        build.append("\n\n")
//        build.append("Hash UUID:").append("\n")
//        val hashUUID = UUIDUtil.getHashUUID(this)
//        val hashUUIDString = String(Hex.encodeHex(hashUUID))
//        build.append(hashUUIDString)

        tvContent.setText(build);
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


        // String phoneNumber2 = tm.getGroupIdLevel1();
    }



}
