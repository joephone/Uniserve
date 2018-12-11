package com.transcendence.universe.abp.ui.customview.flowlayout.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.transcendence.universe.R;
import com.transcendence.universe.abp.main.fragments.BaseFragment;
import com.transcendence.universe.utils.Logs;
import com.zhy.flowlayout.view.FlowLayout;
import com.zhy.flowlayout.view.TagAdapter;
import com.zhy.flowlayout.view.TagFlowLayout;

/**
 * Created by Joephone on 2018/10/29 14:11
 * E-Mail Address：joephonechen@gmail.com
 */

public class MulSeletedFragment extends BaseFragment {
    TagFlowLayout mFlowLayout;

    private String[] mVals = new String[]
            {"强迫症", "强迫症1", "强迫症2 强迫症45 ", "强迫症1232123", "强迫症123123123123123123", "强迫症1",
                    "强迫症4"};

//    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.subject_tag_fragment_simple, container, false);
//        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
    }


    private void init(View view) {
        final LayoutInflater mInflater = LayoutInflater.from(getActivity());
        mFlowLayout = (TagFlowLayout) view.findViewById(R.id.id_flowlayout);

//        mFlowLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

        mFlowLayout.setAdapter(new TagAdapter(mVals) {
            @Override
            public View getView(FlowLayout parent, int position, Object s) {
                TextView tv = (TextView) mInflater.inflate(R.layout.subject_tag_tv,
                        mFlowLayout, false);
                Logs.logE(" 点击"+ (String) s +"---position-------"+position);
                tv.setText((String)s);
                return tv;
            }
        });
    }

}
