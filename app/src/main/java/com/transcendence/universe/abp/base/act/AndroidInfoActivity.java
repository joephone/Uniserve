package com.transcendence.universe.abp.base.act;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.transcendence.universe.R;
import com.transcendence.universe.abp.base.util.DeviceInfoUtil;
import com.transcendence.universe.abp.base.util.DeviceSysytemInfoUtil;
import com.transcendence.universe.abp.main.act.TitleBarActivity;
import com.transcendence.universe.utils.AndroidUtil;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Joephone on 2018/10/24 17:12
 * E-Mail Address：joephonechen@gmail.com
 * android信息， 获取android手机品牌、商家、版本号等信息
 */

public class AndroidInfoActivity extends TitleBarActivity {
    @Bind(R.id.tvWifiMac)
    TextView tvWifiMac;
    @Bind(R.id.tvAndroidId)
    TextView tvAndroidId;
    @Bind(R.id.tvBootTime)
    TextView tvBootTime;
    @Bind(R.id.tvSysInfo)
    TextView tvSysInfo;
    @Bind(R.id.tvDevicInfo)
    TextView tvDevicInfo;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_info);
        ButterKnife.bind(this);
        setTitle("Android信息");

    }

    @Override
    protected void init() {
        tvWifiMac.setText(AndroidUtil.getWifiMacAddress(this));
        tvAndroidId.setText(AndroidUtil.getAndroidId(this));
        tvBootTime.setText(AndroidUtil.getBootTimeString());
        tvSysInfo.setText(AndroidUtil.printSystemInfo());
        tvDevicInfo.setText(DeviceSysytemInfoUtil.getSystemVersion()+DeviceSysytemInfoUtil.getDeviceBrand());
    }
}
