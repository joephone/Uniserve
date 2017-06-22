package com.transcendence.universe.abp.ui.anim.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.transcendence.universe.R;
import com.transcendence.universe.util.Loger;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by joephone on 2017/5/12.
 */
public class AnimAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private String tag = this.getClass().getName();
    private Context mContext;
    private List<String> sourceList = new ArrayList<>();
    private AnimEvent event;
    private LayoutInflater mLayoutInflater;
    private String[] animName = new String[]{"a1","a2","alpha"};


    public AnimAdapter(Context context, List<String> sourceList) {
        this.mContext = context;
        this.sourceList = sourceList;
        mLayoutInflater = LayoutInflater.from(context);
        Loger.i(tag, "size---" + sourceList.size());
    }

    public void setEvent(AnimEvent event) {
        this.event = event;
    }

    @Override
    public int getItemCount() {
        return null == animName ? 0 : animName.length;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AnimViewHolder(mLayoutInflater.inflate(R.layout.anim_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
//        final String name = sourceList.get(position);
        if (holder instanceof AnimViewHolder) {
            AnimViewHolder viewHolder = (AnimViewHolder) holder;
//            if (!StringUtils.isStringNull(name)) {
//                viewHolder.tvMainIndex.setText(name);
//            }

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    event.onItemClick(position);
                }
            });


            viewHolder.textViewName.setText(animName[position]);

            if(position%2==0){
                viewHolder.ll.setBackgroundResource(R.drawable.call_locate_gray);
            }else{
                viewHolder.ll.setBackgroundResource(R.drawable.call_locate_green);
            }
        }
    }


    public class AnimViewHolder extends RecyclerView.ViewHolder {


        @Bind(R.id.textView_num)
        TextView textViewNum;
        @Bind(R.id.textView_name)
        TextView textViewName;
        @Bind(R.id.ll)
        LinearLayout ll;

        public AnimViewHolder(View itemView) {
            super(itemView);
//            R.layout.anim_item;
            ButterKnife.bind(this, itemView);
        }
    }

    public interface AnimEvent {
        void onItemClick(int position);
    }
}
