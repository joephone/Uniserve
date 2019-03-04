package com.transcendence.universe.abp.lofter.label.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.transcendence.universe.R;
import com.transcendence.universe.abp.main.fragments.BaseFragment;

/**
 * Created by Joephone on 2019/2/28 18:12
 * E-Mail Address：joephonechen@gmail.com
 */

public class LabelSearchTagDetailFragment extends BaseFragment implements AdapterView.OnItemClickListener {

    private ListView lvSearchTag;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_label_search, container, false);
//        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
    }

    private void init(View rootView) {
        lvSearchTag = (ListView) rootView.findViewById(R.id.lvSearchTag);
//        lvSearchTag.setOnItemClickListener(this);
//        initList();
//        initTabLayout();
    }

    /**
     * 请求数据
//     * @param content 用户输入的内容
     */
//    private void requestSearchContent(String content) {
//        /**
//         * 搜索时，显示出avi布局，隐藏 listview
//         * 显示avi（在没得到结果时会隐藏avi，现在在显示出来）
//         */
////        avi.setVisibility(View.VISIBLE);
////        rl_search_detail_tag_top.setVisibility(View.VISIBLE);
////        lvSearchTag.setVisibility(View.GONE);
//
//        String url = URLConstants.BASE_URL + URLConstants.SEARCH_CONTENT + 1 + "/" + content;
//        BeanParser parser = new TagParser();
//        HttpServer.sendPostRequest(HttpServer.HTTPSERVER_GET, null, parser, url, new Callback() {
//            @Override
//            public void success(BeanData beanData) {
//                LabelTagData data = (LabelTagData) beanData;
//                tagList = data.getList();
//                /**
//                 * 得到数据之后，设置liewview
//                 */
//                updateUI();
//            }
//
//            @Override
//            public void failure(String error) {
//
//            }
//        });
//    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}
