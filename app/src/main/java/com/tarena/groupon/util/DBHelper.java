package com.tarena.groupon.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.tarena.groupon.bean.CitynameBean;

import java.sql.SQLException;

/**
 * ORM: Object Relation Mapper
 * Object JAVA
 * Relation DB
 * Mappter 映射
 *
 * 1.一个JAVA类对应了数据库中的一种数据表
 *   Person类 <-----> Person表
 * 2.该类中的属性对应数据表中的字段
 *   int age <-----> integer age
 *   String name <-----> text name
 * 3.每一个类型的对象对应数据表中的一条数据记录
 *   p1(35,"王五")<----->age:35,name:"王五"
 *
 * Created by pjy on 2017/6/22.
 */

public class DBHelper extends OrmLiteSqliteOpenHelper {

    private static DBHelper INSTANCE;

    public static DBHelper getInstance(Context  context){

        if(INSTANCE==null){
            synchronized (DBHelper.class){
                if(INSTANCE==null){

                    INSTANCE = new DBHelper(context);

                }
            }

        }


        return INSTANCE;

    }

    private DBHelper(Context context) {
        super(context, "city.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        //在第一次创建city.db数据库时，该方法会被调用
        //创建存储数据的数据表
        try {
            TableUtils.createTableIfNotExists(connectionSource, CitynameBean.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {

        try {
            TableUtils.dropTable(connectionSource,CitynameBean.class,true);
            onCreate(sqLiteDatabase,connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
