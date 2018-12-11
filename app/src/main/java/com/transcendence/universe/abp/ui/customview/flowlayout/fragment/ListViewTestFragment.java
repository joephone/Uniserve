package com.transcendence.universe.abp.ui.customview.flowlayout.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ListView;
import android.widget.TextView;


import com.transcendence.universe.R;
import com.transcendence.universe.abp.ui.customview.flowlayout.adapter.CommonAdapter;
import com.transcendence.universe.abp.ui.customview.flowlayout.adapter.ViewHolder;
import com.zhy.flowlayout.view.FlowLayout;
import com.zhy.flowlayout.view.TagAdapter;
import com.zhy.flowlayout.view.TagFlowLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 *
 * Created by Joephone on 2018/10/30 11:49
 * E-Mail Address：joephonechen@gmail.com
 */

public class ListViewTestFragment extends Fragment {

    private List<List<String>> mDatas = new ArrayList<>();
    private ListView mListView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.subject_tag_fragment_listview, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        initDatas();

        mListView = (ListView) view.findViewById(R.id.id_listview);
        mListView.setAdapter(new CommonAdapter(getActivity(), R.layout.subject_tag_item_for_listview, mDatas) {
            Map<Integer, Set<Integer>> selectedMap = new HashMap<>();


            @Override
            public void convert(final ViewHolder viewHolder, Object strings) {  //List<String> strings
                TagFlowLayout tagFlowLayout = viewHolder.getView(R.id.id_tagflowlayout);

                TagAdapter<String> tagAdapter = new TagAdapter<String>((List<String>) strings) {
                    @Override
                    public View getView(FlowLayout parent, int position, String o) {
                        //can use viewholder
                        TextView tv = (TextView) mInflater.inflate(R.layout.subject_tag_tv,
                                parent, false);
                        tv.setText(o);
                        return tv;
                    }
                };
                tagFlowLayout.setAdapter(tagAdapter);
                //重置状态
                tagAdapter.setSelectedList(selectedMap.get(viewHolder.getItemPosition()));

                tagFlowLayout.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
                    @Override
                    public void onSelected(Set<Integer> selectPosSet) {
                        selectedMap.put(viewHolder.getItemPosition(), selectPosSet);
                    }
                });
            }
        });

    }

    private void initDatas() {
        for (int i = 'A'; i < 'z'; i++) {
            List<String> itemData = new ArrayList<String>(3);
            for (int j = 0; j < 3; j++) {
                itemData.add((char) i + "");
            }
            mDatas.add(itemData);
        }
    }
}
