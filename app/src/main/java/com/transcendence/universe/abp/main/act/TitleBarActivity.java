package com.transcendence.universe.abp.main.act;

import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.transcendence.universe.R;
import com.transcendence.universe.utils.AppUtils;
import com.transcendence.universe.utils.StatusBarUtil;

/**
 * Created by Joephone on 2018/10/19 16:31
 * E-Mail Address：joephonechen@gmail.com
 */

public class TitleBarActivity extends BaseActivity {
    private View titleBar;
    private ImageView back;
    private TextView title;
    protected View statusView;

    @Override
    public void setContentView(int layoutResID) {
        StatusBarUtil.setTransparentForWindow(this);
        View content = getLayoutInflater().inflate(layoutResID, null);
        prepareContentView(content, true);
    }

    public void setContentView(int layoutResID, boolean hasTitle) {
        View content = getLayoutInflater().inflate(layoutResID, null);
        prepareContentView(content, hasTitle);
    }

    @Override
    public void setContentView(View view) {
        prepareContentView(view, true);
    }

    public void setContentView(View view, boolean hasTitle) {
        prepareContentView(view, hasTitle);
    }

    public void prepareContentView(View view, boolean hasTitle) {
        if (hasTitle) {
            if (view instanceof DrawerLayout) {
                DrawerLayout drawerLayout = (DrawerLayout) view;
                View child = drawerLayout.getChildAt(0);
                if (child instanceof LinearLayout) {
                    LinearLayout layout = (LinearLayout) child;
                    titleBar = getLayoutInflater().inflate(R.layout.title, layout, false);
                    layout.addView(titleBar, 0);
                }
            } else {
                LinearLayout content = new LinearLayout(this);
                content.setOrientation(LinearLayout.VERTICAL);

                titleBar = getLayoutInflater().inflate(R.layout.title, content, false);
                content.addView(titleBar);

                // 添加原内容
                LinearLayout.LayoutParams contentParams = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        1);
                view.setLayoutParams(contentParams);
                content.addView(view);

                view = content;
            }
        }

        super.setContentView(view);

        if (hasTitle) {
            initTitle();
        }
    }

    protected void initTitle() {
        if (titleBar != null) {
            back = (ImageView) findViewById(R.id.back);
            statusView = (View) findViewById(R.id.title_view);
            title = (TextView) findViewById(R.id.title);
//            textViewRight = (TextView) findViewById(R.id.textViewRight);
//            textViewTitle = (TextView) findViewById(R.id.textViewTitle);
//            rightImageview = (ImageView) findViewById(R.id.title_right_image);
            initStateHeigt();
//            imageViewBack.setVisibility(View.INVISIBLE);
            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onBackButtonClicked();
                }
            });
//
//            textViewRight.setVisibility(View.INVISIBLE);
//            textViewRight.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    onRightButtonClicked();
//                }
//            });
        }
    }

    protected void setTitle(String title) {
        setTitle(true, title, null);
    }

    protected void setTitle(boolean showBackButton, String titleText, String rightText) {
        if (back != null) {
            back.setVisibility(showBackButton ? View.VISIBLE : View.INVISIBLE);
        }

        if (title != null) {
            title.setText(titleText);
        }

//        if (textViewRight != null) {
//            textViewRight.setVisibility(rightText == null ? View.INVISIBLE : View.VISIBLE);
//            textViewRight.setText(rightText);
//        }
    }

    protected void setTitle(boolean showBackButton, String titleText, int imageSource) {
        if (back != null) {
            back.setVisibility(showBackButton ? View.VISIBLE : View.INVISIBLE);
        }

        if (title != null) {
            title.setText(titleText);
        }

//        if (imageSource >0 ) {
//            rightImageview.setVisibility(imageSource >0 ? View.VISIBLE : View.INVISIBLE);
//            rightImageview.setBackgroundResource(imageSource);
//        }
    }

    protected void onBackButtonClicked() {
        finish();
    }

    private void initStateHeigt() {
        int statusHeight = AppUtils.getStatusHeight(this);
        LinearLayout.LayoutParams l = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, statusHeight);
        statusView.setLayoutParams(l);
    }
}
