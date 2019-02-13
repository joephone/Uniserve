package com.transcendence.universe.abp.ui.widget.recycler.basequickadapter.act;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;


import com.transcendence.universe.R;
import com.transcendence.universe.abp.ui.widget.recycler.basequickadapter.adapter.MovieQuickAdapter;
import com.transcendence.universe.abp.ui.widget.recycler.basequickadapter.bean.MovieEntity;
import com.transcendence.universe.abp.ui.widget.recycler.basequickadapter.http.RetrofitManager;
import com.transcendence.universe.abp.ui.widget.recycler.basequickadapter.quickadapter.BaseQuickAdapter;
import com.transcendence.universe.abp.ui.widget.recycler.basequickadapter.quickadapter.EndlessScrollListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaseQuickActivity extends AppCompatActivity implements Callback<MovieEntity> {
    SwipeRefreshLayout srl;
    RecyclerView rcv;
    List<MovieEntity.SubjectsBean> mBeanList;
    static int CURRENT_PAGE = 0;//起始页第一页
    static final int size = 5;//每页有50条数据

    MovieQuickAdapter mQuickAdapter;
    LinearLayoutManager mLinearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_quick_activity);

        //init RecyclerView
        rcv = (RecyclerView) findViewById(R.id.rcv);
        mLinearLayoutManager = new LinearLayoutManager(this);
        rcv.setLayoutManager(mLinearLayoutManager);
        rcv.addOnScrollListener(new EndlessScrollListener());

        srl = (SwipeRefreshLayout) findViewById(R.id.srl);
        srl.setColorSchemeColors(Color.RED, Color.GREEN, Color.YELLOW);
        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mBeanList.clear();
                        getMovies(CURRENT_PAGE);
                    }
                }, 1000);
            }
        });
        //init Adapter
        mBeanList = new ArrayList<>();
        mQuickAdapter = new MovieQuickAdapter(this, mBeanList);

        //set EmptyView
        mQuickAdapter.setEmptyView(R.layout.rcv_empty);
        mQuickAdapter.setOnPageLoadListener(new BaseQuickAdapter.OnPageLoadListener() {
            @Override
            public void onPageLoad() {
                Log.e("jia","onPageLoad");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        CURRENT_PAGE++;
                        getMovies(CURRENT_PAGE);
                    }
                }, 2000);
            }
        }, size);
        //set OnItemClickListener
        mQuickAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
//                Toasty.info(MainActivity.this, mBeanList.get(position).getTitle()).show();
            }
        });
        getMovies(CURRENT_PAGE);
    }

    private void getMovies(int page) {
        //1,11,21 (page-1)*PAGE_COUNT+1
        srl.setRefreshing(true);
        Log.e("jia","page---"+page);
        RetrofitManager.getService().getTopMovie(page * size , size).enqueue(this);
    }

    @Override
    public void onResponse(Call<MovieEntity> call, Response<MovieEntity> response) {
        if (rcv.getAdapter() == null) {
            mBeanList.addAll(response.body().getSubjects());
            rcv.setAdapter(mQuickAdapter);
        } else {
            //mQuickAdapter.notifyDataSetChanged();
            mQuickAdapter.appendList(response.body().getSubjects());
        }
        srl.setRefreshing(false);
    }

    @Override
    public void onFailure(Call<MovieEntity> call, Throwable t) {
        srl.setRefreshing(false);
    }
}
