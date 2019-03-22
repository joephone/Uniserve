package com.transcendence.universe.abp.main.util;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.transcendence.universe.R;
import com.transcendence.universe.abp.index.act.IndexAct;
import com.transcendence.universe.abpPublicModule.location.act.LocationActivity;
import com.transcendence.universe.utils.AppUtils;


/**
 * Created by mj
 * on 2016/10/28.
 */
public class PopupMenuUtil {

    public static PopupMenuUtil getInstance() {
        return MenuUtilHolder.INSTANCE;
    }

    private static class MenuUtilHolder {
        public static PopupMenuUtil INSTANCE = new PopupMenuUtil();
    }

    private View rootVew;
    private View statusVew;
    private PopupWindow popupWindow;

    private RelativeLayout rlClick;
    private ImageView ivBtn;
    private LinearLayout llTest1, llTest2, llTest3, llTest4, llTest5, llTest6, llTest7, llTest8;

    /**
     * 动画执行的 属性值数组
     */
    float animatorProperty[] = null;
    /**
     * 第一排图 距离屏幕底部的距离
     */
    int top = 0;
    /**
     * 第二排图 距离屏幕底部的距离
     */
    int bottom = 0;

    /**
     * 创建 popupWindow 内容
     *
     * @param context context
     */
    private void createView(final Context context) {
        rootVew = LayoutInflater.from(context).inflate(R.layout.popup_menu, null);
        popupWindow = new PopupWindow(rootVew,
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        //设置为失去焦点 方便监听返回键的监听
        popupWindow.setFocusable(false);

        // 如果想要popupWindow 遮挡住状态栏可以加上这句代码
        //popupWindow.setClippingEnabled(false);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(false);

        if (animatorProperty == null) {
            top = dip2px(context, 310);
            bottom = dip2px(context, 210);
            animatorProperty = new float[]{bottom, 60, -30, -20 - 10, 0};
        }

        initLayout(context);
    }

    /**
     * dp转化为px
     *
     * @param context  context
     * @param dipValue dp value
     * @return 转换之后的px值
     */
    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * 初始化 view
     */
    private void initLayout(Context context) {
        statusVew = (View) rootVew.findViewById(R.id.statusVew);
        rlClick = (RelativeLayout) rootVew.findViewById(R.id.layoutAdd);
        ivBtn = (ImageView) rootVew.findViewById(R.id.ivAdd);
        llTest1 = (LinearLayout) rootVew.findViewById(R.id.test1);
        llTest2 = (LinearLayout) rootVew.findViewById(R.id.test2);
        llTest3 = (LinearLayout) rootVew.findViewById(R.id.test3);
        llTest4 = (LinearLayout) rootVew.findViewById(R.id.test4);
        llTest5 = (LinearLayout) rootVew.findViewById(R.id.test5);
        llTest6 = (LinearLayout) rootVew.findViewById(R.id.test6);
        llTest7 = (LinearLayout) rootVew.findViewById(R.id.test7);
        llTest8 = (LinearLayout) rootVew.findViewById(R.id.test8);

        rlClick.setOnClickListener(new MViewClick(0, context));

        llTest1.setOnClickListener(new MViewClick(1, context));
        llTest2.setOnClickListener(new MViewClick(2, context));
        llTest3.setOnClickListener(new MViewClick(3, context));
        llTest4.setOnClickListener(new MViewClick(4, context));
        llTest5.setOnClickListener(new MViewClick(5, context));
        llTest6.setOnClickListener(new MViewClick(6, context));
        llTest7.setOnClickListener(new MViewClick(7, context));
        llTest8.setOnClickListener(new MViewClick(8, context));

        initStateHeigt(context);
    }

    private void initStateHeigt(Context context) {
        int statusHeight = AppUtils.getStatusHeight(context);
        LinearLayout.LayoutParams l = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, statusHeight);
        statusVew.setLayoutParams(l);
    }

    /**
     * 点击事件
     */
    private class MViewClick implements View.OnClickListener {

        public int index;
        public Context context;

        public MViewClick(int index, Context context) {
            this.index = index;
            this.context = context;
        }

        @Override
        public void onClick(View v) {
            switch (index){
                case 0:
                    //加号按钮点击之后的执行
                    rlClickAction();
                    break;
                case 1:
                    Intent intent = new Intent(context,IndexAct.class);
                    context.startActivity(intent);
                    break;
                case 2:
                    intent = new Intent(context,LocationActivity.class);
                    context.startActivity(intent);
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    break;
            }

//                showToast(context, "index=" + index);
                close();
        }
    }

    Toast toast = null;

    /**
     * 防止toast 多次被创建
     *
     * @param context context
     * @param str     str
     */
    private void showToast(Context context, String str) {
        if (toast == null) {
            toast = Toast.makeText(context, str, Toast.LENGTH_SHORT);
        } else {
            toast.setText(str);
        }
        toast.show();
    }

    /**
     * 刚打开popupWindow 执行的动画
     */
    private void openPopupWindowAction() {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(ivBtn, "rotation", 0f, 135f);
        objectAnimator.setDuration(200);
        objectAnimator.start();

        startAnimation(llTest1, 500, animatorProperty);
        startAnimation(llTest2, 430, animatorProperty);
        startAnimation(llTest3, 430, animatorProperty);
        startAnimation(llTest4, 500, animatorProperty);

        startAnimation(llTest5, 500, animatorProperty);
        startAnimation(llTest6, 430, animatorProperty);
        startAnimation(llTest7, 430, animatorProperty);
        startAnimation(llTest8, 500, animatorProperty);
    }


    /**
     * 关闭 popupWindow执行的动画
     */
    public void rlClickAction() {
        if (ivBtn != null && rlClick != null) {

            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(ivBtn, "rotation", 135f, 0f);
            objectAnimator.setDuration(300);
            objectAnimator.start();

            closeAnimation(llTest1, 300, top);
            closeAnimation(llTest2, 200, top);
            closeAnimation(llTest3, 200, top);
            closeAnimation(llTest4, 300, top);
            closeAnimation(llTest5, 300, bottom);
            closeAnimation(llTest6, 200, bottom);
            closeAnimation(llTest7, 200, bottom);
            closeAnimation(llTest8, 300, bottom);

            rlClick.postDelayed(new Runnable() {
                @Override
                public void run() {
                    close();
                }
            }, 300);

        }
    }


    /**
     * 弹起 popupWindow
     *
     * @param context context
     * @param parent  parent
     */
    public void show(Context context, View parent) {
        createView(context);
        if (popupWindow != null && !popupWindow.isShowing()) {
            popupWindow.showAtLocation(parent, Gravity.NO_GRAVITY, 0, 0);
            openPopupWindowAction();
        }
    }

    /**
     * 关闭popupWindow
     */

    public void close() {
        if (popupWindow != null && popupWindow.isShowing()) {
            popupWindow.dismiss();
            popupWindow = null;
        }
    }

    /**
     * @return popupWindow 是否显示了
     */
    public boolean isShowing() {
        if (popupWindow == null) {
            return false;
        } else {
            return popupWindow.isShowing();
        }
    }

    /**
     * 关闭 popupWindow 时的动画
     *
     * @param view     mView
     * @param duration 动画执行时长
     * @param next     平移量
     */
    private void closeAnimation(View view, int duration, int next) {
        ObjectAnimator anim = ObjectAnimator.ofFloat(view, "translationY", 0f, next);
        anim.setDuration(duration);
        anim.start();
    }

    /**
     * 启动动画
     *
     * @param view     view
     * @param duration 执行时长
     * @param distance 执行的轨迹数组
     */
    private void startAnimation(View view, int duration, float[] distance) {
        ObjectAnimator anim = ObjectAnimator.ofFloat(view, "translationY", distance);
        anim.setDuration(duration);
        anim.start();
    }


}
