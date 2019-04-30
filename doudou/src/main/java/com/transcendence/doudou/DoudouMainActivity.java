package com.transcendence.doudou;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DoudouMainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView tvCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doudou_main);

        tvCity = findViewById(R.id.tvCity);
        tvCity.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvCity:
                Intent intent = new Intent(DoudouMainActivity.this, DoudouMainActivity.class);
                startActivityForResult(intent, 1);
                break;
            case R.id.llSearch:
//                SearchActivity.startActivity(this.getActivity());
                break;
            default:
                break;
        }
    }
}
