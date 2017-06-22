package com.transcendence.universe.abp.main.advancedpagerslidingtabstrip;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

import com.transcendence.universe.R;


public class APSMainAct extends ActionBarActivity {

    private Button mNormalTab;
    private Button mIconTab;
    private Button mCustomTab;
    private Button mViewTab;
    private Button mWeiboTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.asp_main);
        findViews();
        setButton();
    }

    private void findViews(){
        mIconTab = (Button)findViewById(R.id.icontab);
        mNormalTab = (Button)findViewById(R.id.noramltab);
        mCustomTab = (Button)findViewById(R.id.customtab);
        mViewTab = (Button)findViewById(R.id.viewtab);
        mWeiboTab = (Button)findViewById(R.id.weibotab);
    }

    private void setButton(){
        mIconTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                APSMainAct.this.startActivity(new Intent(APSMainAct.this,IconTabActivity.class));
            }
        });
        mNormalTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                APSMainAct.this.startActivity(new Intent(APSMainAct.this,NormalTabActivity.class));
            }
        });
        mCustomTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                APSMainAct.this.startActivity(new Intent(APSMainAct.this,CustomTabActivity.class));
            }
        });
        mViewTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                APSMainAct.this.startActivity(new Intent(APSMainAct.this,ViewTabActivity.class));

            }
        });
        mWeiboTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                APSMainAct.this.startActivity(new Intent(APSMainAct.this,WeiboTabActivity.class));
            }
        });
    }

}
