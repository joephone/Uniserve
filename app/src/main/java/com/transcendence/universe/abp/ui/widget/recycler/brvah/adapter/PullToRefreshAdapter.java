package com.transcendence.universe.abp.ui.widget.recycler.brvah.adapter;

import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.transcendence.universe.R;
import com.transcendence.universe.abp.ui.widget.recycler.brvah.bean.BrvahBean;

import java.util.List;

/**
 * Created by joephone on 2017/6/27.
 */

public class PullToRefreshAdapter extends BaseQuickAdapter<BrvahBean, BaseViewHolder> {


    public PullToRefreshAdapter(List<BrvahBean> datas) {
        super(R.layout.brvah_item, datas);
    }

    @Override
    protected void convert(BaseViewHolder helper, BrvahBean item) {
        int position = helper.getAdapterPosition();

        helper.setImageResource(R.id.img, R.mipmap.ic_launcher);

        helper.setText(R.id.tweetName, "直接设置文字" + position);

        ((TextView) helper.getView(R.id.tweetText)).setText("通过id找到TextView，再设置文字");
    }
}
