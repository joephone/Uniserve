package com.transcendence.universe.abp.classic.fringerprint.act;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.transcendence.universe.R;
import com.transcendence.universe.abp.classic.fringerprint.core.FingerprintCore;
import com.transcendence.universe.abp.classic.fringerprint.core.KeyguardLockScreenManager;
import com.transcendence.universe.abp.main.act.TitleBarActivity;
import com.transcendence.universe.utils.T;

/**
 * Created by Joephone on 2018/10/26 14:49
 * E-Mail Address：joephonechen@gmail.com
 */

public class FingerprintMainActivity extends TitleBarActivity implements View.OnClickListener {

    private ImageView ivGuide;
    private TextView tvTip;

    private FingerprintCore mFingerprintCore;
    private KeyguardLockScreenManager mKeyguardLockScreenManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fingerprint_main);
        setTitle("指纹");
        initViews();
        initViewListeners();
        initFingerprintCore();
    }


    private void initViews() {
        ivGuide = (ImageView) findViewById(R.id.ivGuide);
        tvTip = (TextView) findViewById(R.id.tvTip);
    }

    private void initViewListeners() {
        findViewById(R.id.fingerprint_recognition_start).setOnClickListener(this);
        findViewById(R.id.fingerprint_recognition_cancel).setOnClickListener(this);
        findViewById(R.id.fingerprint_recognition_sys_unlock).setOnClickListener(this);
        findViewById(R.id.fingerprint_recognition_sys_setting).setOnClickListener(this);
    }

    private void initFingerprintCore() {
        mFingerprintCore = new FingerprintCore(this);
        mFingerprintCore.setFingerprintManager(mResultListener);
        mKeyguardLockScreenManager = new KeyguardLockScreenManager(this);
    }

    @Override
    public void onClick(View v) {
        final int viewId = v.getId();
        switch (viewId) {
            case R.id.fingerprint_recognition_start:
                startFingerprintRecognition();
                break;
            case R.id.fingerprint_recognition_cancel:
//                cancelFingerprintRecognition();
                break;
            case R.id.fingerprint_recognition_sys_unlock:
//                startFingerprintRecognitionUnlockScreen();
                break;
            case R.id.fingerprint_recognition_sys_setting:
//                enterSysFingerprintSettingPage();
                break;
        }
    }


    /**
     * 开始指纹识别
     */
    private void startFingerprintRecognition() {
        if (mFingerprintCore.isSupport()) {
            if (!mFingerprintCore.isHasEnrolledFingerprints()) {
                T.toast(R.string.fingerprint_recognition_not_enrolled);
                openFingerPrintSettingPage(this);
                return;
            }
            T.toast(R.string.fingerprint_recognition_tip);
            tvTip.setText(R.string.fingerprint_recognition_tip);
            ivGuide.setBackgroundResource(R.mipmap.fingerprint_guide);
            if (mFingerprintCore.isAuthenticating()) {
                T.toast(R.string.fingerprint_recognition_authenticating);
            } else {
                mFingerprintCore.startAuthenticate();
            }
        } else {
            T.toast(R.string.fingerprint_recognition_not_support);
            tvTip.setText(R.string.fingerprint_recognition_tip);
        }
    }



    public void openFingerPrintSettingPage(Context context) {
        Intent intent = new Intent("android.settings.SETTINGS");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            context.startActivity(intent);
        } catch (Exception e) {
        }
    }

    private void resetGuideViewState() {
        tvTip.setText(R.string.fingerprint_recognition_guide_tip);
        ivGuide.setBackgroundResource(R.mipmap.fingerprint_normal);
    }

    private FingerprintCore.IFingerprintResultListener mResultListener = new FingerprintCore.IFingerprintResultListener() {
        @Override
        public void onAuthenticateSuccess() {
            T.toast(R.string.fingerprint_recognition_success);
            resetGuideViewState();
        }

        @Override
        public void onAuthenticateFailed(int helpId) {
            T.toast(R.string.fingerprint_recognition_failed);
            tvTip.setText(R.string.fingerprint_recognition_failed);
        }

        @Override
        public void onAuthenticateError(int errMsgId) {
            resetGuideViewState();
            T.toast(R.string.fingerprint_recognition_error);
        }

        @Override
        public void onStartAuthenticateResult(boolean isSuccess) {

        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == KeyguardLockScreenManager.REQUEST_CODE_CONFIRM_DEVICE_CREDENTIALS) {
            // Challenge completed, proceed with using cipher
            if (resultCode == RESULT_OK) {
                T.toast(R.string.sys_pwd_recognition_success);
            } else {
                T.toast(R.string.sys_pwd_recognition_failed);
            }
        }
    }

//    private Runnable mShowToastRunnable = new Runnable() {
//        @Override
//        public void run() {
//            mToast.show();
//        }
//    };

    @Override
    protected void onDestroy() {
        if (mFingerprintCore != null) {
            mFingerprintCore.onDestroy();
            mFingerprintCore = null;
        }
        if (mKeyguardLockScreenManager != null) {
            mKeyguardLockScreenManager.onDestroy();
            mKeyguardLockScreenManager = null;
        }
        mResultListener = null;
//        mShowToastRunnable = null;
//        mToast = null;
        super.onDestroy();
    }
}
