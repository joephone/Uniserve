package com.transcendence.universe.abp.classic.luckyturntable.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.transcendence.universe.R;

/**
 * Created by Joephone on 2018/12/18 11:53
 * E-Mail Address：joephonechen@gmail.com
 */

public class NineGridViewTwo extends ViewGroup {

    private static final int CHILD_COUNT = 9;
    private static final int MAX_TIMES = 8;

    private View mChildren[][];

    private float mLineSpace;

    private float mColumnSpace;

    private OnStartListener mOnStartListener;

    private boolean mIsStarted;

    private int mMaxTimes;

    private int mTimes;

    private int mIndex;

    private int mStopPos;

    private Long mDelay;

    public NineGridViewTwo(Context context) {
        super(context);
        initView();
    }

    public NineGridViewTwo(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NineGridViewTwo(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.NightGridView2);
        mLineSpace = typedArray.getDimension(R.styleable.NightGridView2_line_space, 0);
        mColumnSpace = typedArray.getDimension(R.styleable.NightGridView2_column_space, 0);
        typedArray.recycle();
        initView();
    }

    private void initView() {
        mChildren = new View[3][3];
        initSetting();
    }

    private void initSetting() {
        mTimes = 0;
        mIndex = 0;
        mMaxTimes = Integer.MAX_VALUE;
        mStopPos = 0;
        mDelay = 50L;
        mIsStarted = false;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int contentWidth = right - left - 2 * (int) mLineSpace - getPaddingLeft() - getPaddingRight();
        int contentHeight = bottom - top - 2 * (int) mColumnSpace - getPaddingTop() - getPaddingBottom();

        if (getChildCount() != CHILD_COUNT) {
            throw new RuntimeException("子控件必须为9个！");
        }
        mChildren[0][0] = getChildAt(0);
        mChildren[0][1] = getChildAt(1);
        mChildren[0][2] = getChildAt(2);
        mChildren[1][0] = getChildAt(7);
        mChildren[1][1] = getChildAt(8);
        mChildren[1][2] = getChildAt(3);
        mChildren[2][0] = getChildAt(6);
        mChildren[2][1] = getChildAt(5);
        mChildren[2][2] = getChildAt(4);
        for (int i = 0; i < mChildren.length; i++) {
            View[] views = mChildren[i];
            for (int j = 0; j < views.length; j++) {
                View child = views[j];
                child.layout(getPaddingLeft() + j * contentWidth / 3 + j * (int) mLineSpace,
                        getPaddingTop() + i * contentHeight / 3 + i * (int) mColumnSpace,
                        getPaddingLeft() + (j + 1) * contentWidth / 3 + j * (int) mLineSpace,
                        getPaddingTop() + (i + 1) * contentHeight / 3 + i * (int) mColumnSpace);
            }
        }
    }

    private void start() {
        for (int i = 0; i < getChildCount() - 1; i++) {
            View child = getChildAt(i);
            if (child != null) {
                child.setBackgroundColor(Color.WHITE);
            }
        }
        new LotteryThread().start();
    }

    public void stop(int position) {
        mMaxTimes = MAX_TIMES;
        mStopPos = position;
    }

    private class LotteryThread extends Thread {
        @Override
        public void run() {
            while (mTimes < mMaxTimes || mIndex < mStopPos) {
                post(new Runnable() {
                    @Override
                    public void run() {
                        if (mIndex == 7) {
                            getChildAt(mIndex).setBackgroundResource(0);
                            mIndex = 0;
                            getChildAt(mIndex).setBackgroundResource(R.mipmap.nine_grid_score);
                            mTimes += 1;
                        } else {
//                            getChildAt(mIndex).setBackgroundColor(Color.WHITE);
                            getChildAt(mIndex).setBackgroundResource(0);
                            getChildAt(++mIndex).setBackgroundResource(R.mipmap.nine_grid_score);
                        }
                    }
                });
                if (mTimes == 5) {
                    mDelay = 100L;
                } else if (mTimes == 7) {
                    mDelay = 200L;
                }
                SystemClock.sleep(mDelay);
            }
            initSetting();
        }
    }

    public void setOnStartListener(OnStartListener onStartListener) {
        mOnStartListener = onStartListener;
        View startBtn = getChildAt(getChildCount() - 1);
        startBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mIsStarted) {
                    mIsStarted = true;
                    start();
                    if (mOnStartListener != null) {
                        mOnStartListener.onStart();
                    }
                }
            }
        });
    }

    public float getLineSpace() {
        return mLineSpace;
    }

    public void setLineSpace(float lineSpace) {
        mLineSpace = lineSpace;
    }

    public float getColumnSpace() {
        return mColumnSpace;
    }

    public void setColumnSpace(float columnSpace) {
        mColumnSpace = columnSpace;
    }

    public int getMaxTimes() {
        return mMaxTimes;
    }

    public void setMaxTimes(int maxTimes) {
        mMaxTimes = maxTimes;
    }

    public interface OnStartListener {
        void onStart();
    }


}
