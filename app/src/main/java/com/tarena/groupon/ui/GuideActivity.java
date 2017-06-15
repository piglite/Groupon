package com.tarena.groupon.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.View;

import com.tarena.groupon.R;
import com.tarena.groupon.adapter.MyPagerAdapter;
import com.viewpagerindicator.CirclePageIndicator;
import com.viewpagerindicator.PageIndicator;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GuideActivity extends FragmentActivity {

    @BindView(R.id.vp_main)
    ViewPager viewPager;
    MyPagerAdapter adapter;

    @BindView(R.id.indicator)
    CirclePageIndicator indicator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        ButterKnife.bind(this);
        initViewPager();
    }

    private void initViewPager() {
        adapter = new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        indicator.setViewPager(viewPager);
        //当前运行程序所使用的设备的屏幕密度
        //低密度 ldpi  120px/1inch(2.54cm)
        //中密度 mdpi  160px/1inch
        //高密度 hdpi  240px/1inch
        //很高密度 xhdpi 320px/1inch
        //非常高密度 xxhdpi 480px/1inch

        //dp绝对单位 160dp= 1inch
        //1dp 在低密度屏幕上 0.75px
        //1dp 在中密度屏幕上 1px
        //1dp 在高密度屏幕上 1.5px
        //1dp 在很高密度屏幕上 2px
        //1dp 在非常高密度屏幕上 3px

        //另外一种获得10dp在当前设备屏幕密度上的像素值的方式
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,10,getResources().getDisplayMetrics());

        final float density = getResources().getDisplayMetrics().density;
        //indicator.setBackgroundColor(0xFFCCCCCC);
        //10dp在当前设备上所对应的像素值(px)
        indicator.setRadius(10 * density);
        indicator.setPageColor(0xFFFFFFFF);
        indicator.setFillColor(0xFFFF6633);
        indicator.setStrokeColor(0xFFFF6633);
        indicator.setStrokeWidth(2 * density);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //NO_OP
            }

            @Override
            public void onPageSelected(int position) {
                if(position==3){
                    indicator.setVisibility(View.INVISIBLE);
                }else{
                    indicator.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                //NO_OP
            }
        });
    }
}
