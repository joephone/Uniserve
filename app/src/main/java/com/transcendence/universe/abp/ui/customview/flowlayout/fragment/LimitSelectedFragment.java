package com.transcendence.universe.abp.ui.customview.flowlayout.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.transcendence.universe.R;
import com.zhy.flowlayout.view.FlowLayout;
import com.zhy.flowlayout.view.TagAdapter;
import com.zhy.flowlayout.view.TagFlowLayout;


/**
 * Created by Joephone on 2018/10/30 11:02
 * E-Mail Address：joephonechen@gmail.com
 */


public class LimitSelectedFragment extends Fragment {
    private String[] mVals = new String[]
            {"Hello", "Android", "Weclome Hi ", "Button", "TextView", "Hello",
                    "Android", "Weclome", "Button ImageView", "TextView", "Helloworld",
                    "Android", "Weclome Hello", "Button Text", "TextView", "Hello", "Android", "Weclome Hi ", "Button", "TextView", "Hello",
                    "Android", "Weclome", "Button ImageView"};

    private TagFlowLayout mFlowLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.subject_tag_fragment_simple, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        final LayoutInflater mInflater = LayoutInflater.from(getActivity());
        mFlowLayout = (TagFlowLayout) view.findViewById(R.id.id_flowlayout);
        mFlowLayout.setMaxSelectCount(3);
        mFlowLayout.setAdapter(new TagAdapter(mVals) {

            @Override
            public View getView(FlowLayout parent, int position, Object s) {
                TextView tv = (TextView) mInflater.inflate(R.layout.subject_tag_tv,
                        mFlowLayout, false);
                tv.setText((String)s);
                return tv;
            }
        });
    }
}
