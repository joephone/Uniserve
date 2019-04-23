package com.transcendence.recyclerviewhelperlib.listener;

/**
 * Created by Joephone on 2019/4/16 16:42
 * E-Mail Address：joephonechen@gmail.com
 */

public interface IRecyclerViewListener {
    /**
     * 下拉刷新
     */
    void onRefresh();

    /**
     * 上拉加载更多
     */
    void onLoadMore();
}
