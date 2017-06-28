package com.tarena.groupon.app;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.model.LatLng;
import com.tarena.groupon.bean.CitynameBean;
import com.tarena.groupon.util.SPUtil;

import java.util.List;

/**
 * Created by pjy on 2017/6/19.
 */

public class MyApp extends Application{

    public static MyApp CONTEXT;

    //城市名称的缓存
    public static List<CitynameBean> citynameBeanList;

    public static LatLng myLocation;

    @Override
    public void onCreate() {
        super.onCreate();
        SDKInitializer.initialize(getApplicationContext());
        CONTEXT = this;
        SPUtil spUtil = new SPUtil(this);
        spUtil.setCloseBanner(false);
    }
}
