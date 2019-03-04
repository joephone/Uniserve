package com.transcendence.universe.abp.lofter.label.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.transcendence.universe.R;
import com.transcendence.universe.abp.main.fragments.BaseFragment;
import com.transcendence.universe.utils.KeyBoardUtils;
import com.transcendence.universe.utils.Logs;
import com.transcendence.universe.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joephone on 2019/3/1 13:50
 * E-Mail Address：joephonechen@gmail.com
 */

public class LabelFragment extends BaseFragment implements View.OnClickListener, TextWatcher {
    /**
     * 设置点击是否搜集的标记
     */
    private int searchFlag = 1;
    //圆角矩形背景的相对布局
    private RelativeLayout rlLabelTopLeftBg;
    //搜索框
    private EditText etSearch;
    //取消按钮
    private TextView tvCancel;
    //添加关注按钮
    private ImageView iv_label_add_attention;

    //搜索框的tv  为了实现动画效果
    private TextView tvHintSearchText;
    // 包含搜索icon和tv的相对布局  为了实现动画效果
    private RelativeLayout rlLabelTopLeft;

    private View rootView;  //缓存Fragment view

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Logs.logE("LabelSearchFragment onCreateView");
        if(rootView==null){
            rootView = inflater.inflate(R.layout.fragment_label, container, false);
            init(rootView);
        }else {  //缓存的rootView需要判断是否已经被加过parent， 如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if(parent!=null){
                parent.removeView(rootView);
            }
        }
        return rootView;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Logs.logE("LabelSearchFragment onViewCreated");
    }

    private void init(View rootView) {
        etSearch = (EditText) rootView.findViewById(R.id.etSearch);
        rlLabelTopLeftBg = (RelativeLayout) rootView.findViewById(R.id.rlLabelTopLeftBg);
        rlLabelTopLeftBg.setOnClickListener(this);
        replaceFragment(new LabelMainFragment(), R.id.flLabelFg);
        tvCancel = (TextView) rootView.findViewById(R.id.tvCancel);
        tvCancel.setOnClickListener(this);
        tvHintSearchText = (TextView) rootView.findViewById(R.id.tvHintSearchText);
        etSearch.addTextChangedListener(this);
        rlLabelTopLeft = (RelativeLayout) rootView.findViewById(R.id.rlLabelTopLeft);

        /**
         * 动态设置包含搜索icon和tv的相对布局宽度和规则  为了实现动画效果
         */
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ScreenUtils.getScreenWidth(getActivity()) / 2, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.CENTER_HORIZONTAL);
        lp.addRule(RelativeLayout.CENTER_VERTICAL);
        rlLabelTopLeft.setLayoutParams(lp);
        iv_label_add_attention = (ImageView) rootView.findViewById(R.id.iv_label_add_attention);
        iv_label_add_attention.setOnClickListener(this);


    }



    /**
     * 在页面消失的时候反注册广播,防止内存泄露
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
//        mContext.unregisterReceiver(rdb);
    }



    public static LabelFragment newInstance(String param1) {
        LabelFragment fragment = new LabelFragment();
        Bundle args = new Bundle();
//        args.putString(ARG_SHOW_TEXT, param1);
        fragment.setArguments(args);
        return fragment;
    }





    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            /**
             * 切换到主碎片按钮
             */
            case R.id.tvCancel:
                //替换碎片，将取消按钮设为不可见，标记+1
                replaceFragment(new LabelMainFragment(), R.id.flLabelFg);
                tvCancel.setVisibility(View.GONE);
                etSearch.setVisibility(View.GONE);
//                iv_label_add_attention.setVisibility(View.VISIBLE);
                etSearch.setText("");
                startToRightAnim();
                //弹回软键盘
                KeyBoardUtils.closeKeybord(etSearch, getActivity());
                searchFlag++;
                break;
            /**
             * 切换到搜索碎片按钮
             */
            case R.id.rlLabelTopLeftBg:
                if (searchFlag % 2 == 1) {
                    //表示点击切换到搜索碎片
                    replaceFragment(new LabelSearchFragment(), R.id.flLabelFg);
                    tvCancel.setVisibility(View.VISIBLE);
                    etSearch.setVisibility(View.VISIBLE);
//                    iv_label_add_attention.setVisibility(View.GONE);
                    /**
                     * 平移动画
                     */
                    startToLeftAnim();
                    //弹起软键盘
                    KeyBoardUtils.openKeybord(etSearch, getActivity());
                    searchFlag++;
                }
                break;
            /**
             * 添加关注按钮
             */
            case R.id.iv_label_add_attention:
                //判断是否登陆
                //跳转添加关注页面
//                toast("啊啊啊啊");
                break;
        }
    }

    /**
     * 顶部文字和图片水平向右移动动画
     */
    private void startToRightAnim() {
        AnimationSet animationSet = new AnimationSet(true);
        TranslateAnimation translateAnimation = new TranslateAnimation(
                //X轴初始位置
                Animation.RELATIVE_TO_SELF, -0.42f,
                //X轴移动的结束位置
                Animation.RELATIVE_TO_SELF, 0.0f,
                //y轴开始位置
                Animation.RELATIVE_TO_SELF, 0.0f,
                //y轴移动后的结束位置
                Animation.RELATIVE_TO_SELF, 0.0f);
        //0.4秒完成动画
        translateAnimation.setDuration(400);
        //如果fillAfter的值为真的话，动画结束后，控件停留在执行后的状态
        animationSet.setFillAfter(true);
        //将AlphaAnimation这个已经设置好的动画添加到 AnimationSet中
        animationSet.addAnimation(translateAnimation);
        //启动动画
        rlLabelTopLeft.startAnimation(animationSet);
    }

    /**
     * 顶部文字和图片水平向左移动动画
     */
    private void startToLeftAnim() {

        /*??????????????????????????????????????????????????????????????
        //相对布局整体控件的宽度
        int tv_width = ScreenUtils.getScreenWidth(mContext)/2;
        //移动自身一半到达屏幕最左端  0.5倍
        //但是需要留出一定的margin   距离左边17dp
        //总共需要移动的px
        int moveX = tv_width - DensityUtils.dp2px(mContext,8);
        //算出比例
        float topPro =  (float) moveX/((float) (tv_width*2));
        //0.04  8dp
        */

        AnimationSet animationSet = new AnimationSet(true);
        TranslateAnimation translateAnimation = new TranslateAnimation(
                //X轴初始位置
                Animation.RELATIVE_TO_SELF, 0.0f,
                //X轴移动的结束位置
                Animation.RELATIVE_TO_SELF, -0.42f,
                //y轴开始位置
                Animation.RELATIVE_TO_SELF, 0.0f,
                //y轴移动后的结束位置
                Animation.RELATIVE_TO_SELF, 0.0f
        );
        //0.4秒完成动画
        translateAnimation.setDuration(400);
        //如果fillAfter的值为真的话，动画结束后，控件停留在执行后的状态
        animationSet.setFillAfter(true);
        //将AlphaAnimation这个已经设置好的动画添加到 AnimationSet中
        animationSet.addAnimation(translateAnimation);
        //启动动画
        rlLabelTopLeft.startAnimation(animationSet);
    }

    /**
     * @param fg 需要替换的碎片
     * @param id 需要替换的位置
     */
    private void replaceFragment(Fragment fg, int id) {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(id, fg);
        ft.commit();
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if (!"".equals(etSearch.getText().toString())) {
            tvHintSearchText.setText("");
            /**
             * 发送广播，通知请求数据
             */
//            Intent intent = new Intent(LabelSearchFragment.REQUEST_SEARCH_DATA_BROAD);
//            intent.putExtra("contnet",et_label_search.getText().toString());
//            getActivity().sendBroadcast(intent);
        } else {
            tvHintSearchText.setText("搜索标签、用户、标题");
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
