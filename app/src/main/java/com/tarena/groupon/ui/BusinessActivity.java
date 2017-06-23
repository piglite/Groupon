package com.tarena.groupon.ui;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.tarena.groupon.R;

public class BusinessActivity extends Activity {

    String city;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business);
        city = getIntent().getStringExtra("city");
        Log.d("TAG", "onCreate: 城市--->"+city);

    }
}
