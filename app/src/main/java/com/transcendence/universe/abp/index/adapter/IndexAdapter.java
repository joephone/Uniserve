package com.transcendence.universe.abp.index.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.transcendence.universe.R;
import com.transcendence.universe.utils.Loger;
import com.transcendence.universe.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by joephone on 2017/5/12.
 */
public class IndexAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private String tag = this.getClass().getName();
    private Context mContext;
    private List<String> sourceList = new ArrayList<>();
    private IndexEvent event;
    private LayoutInflater mLayoutInflater;


    public IndexAdapter(Context context, List<String> sourceList) {
        this.mContext = context;
        this.sourceList = sourceList;
        mLayoutInflater = LayoutInflater.from(context);
        Loger.i(tag,"size---"+sourceList.size());
    }

    public void setEvent(IndexEvent event) {
        this.event = event;
    }

    @Override
    public int getItemCount() {
        return null == sourceList ? 0 : sourceList.size();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new IndexViewHolder(mLayoutInflater.inflate(R.layout.index_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder,final int position) {
        final String name = sourceList.get(position);
        if (holder instanceof IndexViewHolder) {
            IndexViewHolder viewHolder = (IndexViewHolder) holder;
            if(!StringUtils.isStringNull(name)){
                viewHolder.tvMainIndex.setText(name);
            }

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    event.onItemClick(position);
                }
            });
        }
    }


    public class IndexViewHolder extends RecyclerView.ViewHolder {


        @Bind(R.id.tvMainIndex)
        TextView tvMainIndex;

        public IndexViewHolder(View itemView) {
            super(itemView);
//            R.layout.index_item;
            ButterKnife.bind(this, itemView);
        }
    }

    public interface IndexEvent{
        void onItemClick(int position);
    }
}
