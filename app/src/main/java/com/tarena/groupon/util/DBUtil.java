package com.tarena.groupon.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.dao.Dao;
import com.tarena.groupon.bean.CitynameBean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by pjy on 2017/6/22.
 */

public class DBUtil {

    Dao<CitynameBean, String> dao;
    DBHelper dbHelper;

    public DBUtil(Context context) {
        dbHelper = DBHelper.getInstance(context);
        try {
            dao = dbHelper.getDao(CitynameBean.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insert(CitynameBean citynameBean) {
        try {
            dao.createIfNotExists(citynameBean);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertAll(List<CitynameBean> list) {

        for (CitynameBean bean : list) {
            insert(bean);
        }

    }

    public void insertBatch(final List<CitynameBean> list) {
        //建立连接后，一次性将数据全部写入后，再断开连接
        try {
            dao.callBatchTasks(new Callable<Object>() {


                @Override
                public Object call() throws Exception {

                    for (CitynameBean bean : list) {
                        insert(bean);
                    }

                    return null;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public List<CitynameBean> query() {
        try {
            List<CitynameBean> citynameBeanList = dao.queryForAll();
            return citynameBeanList;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("查询数据库时出现异常");
        }
    }


}
