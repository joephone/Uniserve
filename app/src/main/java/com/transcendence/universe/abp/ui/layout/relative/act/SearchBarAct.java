package com.transcendence.universe.abp.ui.layout.relative.act;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.transcendence.universe.R;
import com.transcendence.universe.abp.main.act.TitleBarActivity;
import com.transcendence.universe.abp.ui.layout.relative.view.SearchBarLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Porster on 16/6/8.
 */
public class SearchBarAct extends TitleBarActivity {
    private List<String> mListData;
    private List<String> mOriginData;

    private ArrayAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_bar);
        initUI();
    }

    private void initUI() {
        SearchBarLayout mSearchBar= (SearchBarLayout) findViewById(R.id.searchBarlayout);
        ListView mListView= (ListView) findViewById(R.id.listview);


        String[] mTestData={"MacBook Pro","Apple","iPhone 6s","Samsung","Vivo",
                "MacBook Pro1","Apple1","iPhone 6s1","Samsung1","Vivo1",
                "MacBook Pro2","Apple2","iPhone 6s2","Samsung2","Vivo2"};

        mListData=new ArrayList<>();
        mOriginData=new ArrayList<>();

        for (String ste :mTestData) {
            mListData.add(ste);
        }
        mOriginData.addAll(mListData);


        mAdapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,mListData);

        mListView.setAdapter(mAdapter);

        //Real Time Search

        mSearchBar.getEditor().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mSearchBar.getEditor().setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                String key=v.getText().toString();
                if(TextUtils.isEmpty(key)){
                    Toast.makeText(SearchBarAct.this,"Key is Null",Toast.LENGTH_SHORT).show();
                    return true;
                }
                String[] mResult={"Query Result1","Query Result2"};

                List<String> mResultList=new ArrayList<String>();
                for (String result :mResult) {
                    mResultList.add(result);
                }
                mAdapter.clear();
                mAdapter.addAll(mResultList);

                Toast.makeText(SearchBarAct.this,"Query Done",Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        //Cancel Search
        mSearchBar.setCancelSearchLayout(new SearchBarLayout.OnCancelSearchLayout() {
            @Override
            public void OnCancel() {
                filter("");
            }
        });

    }
    private void filter(String key){
        mListData.clear();
        mListData.addAll(mOriginData);
        if(!TextUtils.isEmpty(key)){
            for (int i = mListData.size()-1; i >=0; i--) {
                if(!mListData.get(i).toLowerCase(Locale.getDefault()).contains(key)){
                    mListData.remove(i);
                }
            }
        }
        mAdapter.notifyDataSetChanged();
    }
}
