package com.tarena.groupon.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.tarena.groupon.R;
import com.tarena.groupon.adapter.CityAdapter;
import com.tarena.groupon.bean.CityBean;
import com.tarena.groupon.bean.CitynameBean;
import com.tarena.groupon.util.HttpUtil;
import com.tarena.groupon.util.PinYinUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CityActivity extends Activity {

    @BindView(R.id.rv_city_cities)
    RecyclerView recyclerView;
    //适配器
    CityAdapter adapter;
    //数据源
    List<CitynameBean> datas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);

        ButterKnife.bind(this);

        initRecyclerView();


    }

    private void initRecyclerView() {

        //初始化数据源，适配器

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        datas = new ArrayList<CitynameBean>();
        adapter = new CityAdapter(this, datas);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        refresh();
    }

    private void refresh() {
        //调用HttpUtil获取城市信息
        HttpUtil.getCitiesByRetrofit(new Callback<CityBean>() {
            @Override
            public void onResponse(Call<CityBean> call, Response<CityBean> response) {
                CityBean cityBean = response.body();
                //"全国，上海，杭州，北京，其它城市..."
                List<String> list = cityBean.getCities();
                //根据List<String>创建一个List<CitynameBean>
                //将List<CitynameBean>放到RecyclerView中显示

                List<CitynameBean> citynameBeanList = new ArrayList<CitynameBean>();
                for (String name : list) {

                    if (!name.equals("全国") && !name.equals("其它城市")&&!name.equals("点评实验室")) {
                        CitynameBean citynameBean = new CitynameBean();
                        citynameBean.setCityName(name);
                        citynameBean.setPyName(PinYinUtil.getPinYin(name));
                        citynameBean.setLetter(PinYinUtil.getLetter(name));
                        Log.d("TAG", "创建的CitynameBean内容： "+citynameBean);

                        citynameBeanList.add(citynameBean);
                    }

                }

                Collections.sort(citynameBeanList, new Comparator<CitynameBean>() {
                    @Override
                    public int compare(CitynameBean t1, CitynameBean t2) {
                        return t1.getPyName().compareTo(t2.getPyName());
                    }
                });


                adapter.addAll(citynameBeanList, true);
            }

            @Override
            public void onFailure(Call<CityBean> call, Throwable throwable) {

            }
        });
    }
}
