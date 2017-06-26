package com.tarena.groupon.app;

import android.app.Application;

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

    @Override
    public void onCreate() {
        super.onCreate();
        CONTEXT = this;
        SPUtil spUtil = new SPUtil(this);
        spUtil.setCloseBanner(false);
    }
}
