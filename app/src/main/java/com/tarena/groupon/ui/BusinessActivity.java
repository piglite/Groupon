package com.tarena.groupon.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.tarena.groupon.R;
import com.tarena.groupon.adapter.BusinessAdapter;
import com.tarena.groupon.bean.BusinessBean;
import com.tarena.groupon.bean.DistrictBean;
import com.tarena.groupon.util.HttpUtil;
import com.tarena.groupon.util.SPUtil;
import com.tarena.groupon.view.MyBanner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BusinessActivity extends Activity {

    String city;

    @BindView(R.id.lv_business_listview)
    ListView listView;
    List<BusinessBean.Business> datas;
    BusinessAdapter adapter;
    SPUtil sputil;

    @BindView(R.id.iv_business_loading)
    ImageView ivLoading;

    @BindView(R.id.district_layout)
    View districtLayout;

    @BindView(R.id.lv_business_select_left)
    ListView leftListView;
    @BindView(R.id.lv_business_select_right)
    ListView rightListView;

    List<String> leftDatas;
    List<String> rightDatas;

    ArrayAdapter<String> leftAdapter;
    ArrayAdapter<String> rightAdapter;
    List<DistrictBean.City.District> districtList;

    @BindView(R.id.tv_business_textview1)
    TextView tvRegion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business);
        city = getIntent().getStringExtra("city");
        ButterKnife.bind(this);
        sputil = new SPUtil(this);
        initListView();

    }

    private void initListView() {

        datas = new ArrayList<BusinessBean.Business>();
        adapter = new BusinessAdapter(this, datas);

        if (!sputil.isCloseBanner()) {
            final MyBanner myBanner = new MyBanner(this, null);
            myBanner.setOnCloseBannerListener(new MyBanner.OnCloseBannerListener() {
                @Override
                public void onClose() {
                    sputil.setCloseBanner(true);
                    listView.removeHeaderView(myBanner);
                }
            });
            listView.addHeaderView(myBanner);
        }
        listView.setAdapter(adapter);

        AnimationDrawable d = (AnimationDrawable) ivLoading.getDrawable();
        d.start();

        listView.setEmptyView(ivLoading);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                BusinessBean.Business business;

                if(sputil.isCloseBanner()){
                    business = adapter.getItem(i);
                }else{
                    business = adapter.getItem(i-1);
                }

                Intent intent = new Intent(BusinessActivity.this,DetailActivity.class);
                intent.putExtra("business",business);
                startActivity(intent);

            }
        });


        leftDatas = new ArrayList<String>();
        leftAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, leftDatas);
        leftListView.setAdapter(leftAdapter);

        rightDatas = new ArrayList<String>();
        rightAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, rightDatas);
        rightListView.setAdapter(rightAdapter);

        leftListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                DistrictBean.City.District district = districtList.get(i);
                List<String> neighborhoods = new ArrayList<String>(district.getNeighborhoods());
                neighborhoods.add(0,"全部"+district.getDistrict_name());
                rightDatas.clear();
                rightDatas.addAll(neighborhoods);
                rightAdapter.notifyDataSetChanged();

            }
        });

        rightListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> adapterView, View view, int i, long l) {
                String region = rightAdapter.getItem(i);
                if(i==0){//regioin "全部xx区"
                    region = region.substring(2,region.length());
                }

                tvRegion.setText(region);
                districtLayout.setVisibility(View.INVISIBLE);
                adapter.removeAll();

                HttpUtil.getFoodsByRetrofit(city, region, new Callback<BusinessBean>() {
                    @Override
                    public void onResponse(Call<BusinessBean> call, Response<BusinessBean> response) {

                        BusinessBean businessBean = response.body();
                        List<BusinessBean.Business> list = businessBean.getBusinesses();
                        adapter.addAll(list,true);
                    }

                    @Override
                    public void onFailure(Call<BusinessBean> call, Throwable throwable) {

                    }
                });
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        refresh();
    }

    private void refresh() {

        HttpUtil.getFoodsByRetrofit(city, null, new Callback<BusinessBean>() {
            @Override
            public void onResponse(Call<BusinessBean> call, Response<BusinessBean> response) {

                BusinessBean businessBean = response.body();
                List<BusinessBean.Business> list = businessBean.getBusinesses();
                adapter.addAll(list, true);
            }

            @Override
            public void onFailure(Call<BusinessBean> call, Throwable throwable) {

            }
        });

        HttpUtil.getDistrictsByRetrofit(city, new Callback<DistrictBean>() {
            @Override
            public void onResponse(Call<DistrictBean> call, Response<DistrictBean> response) {
                DistrictBean districtBean = response.body();
                districtList = districtBean.getCities().get(0).getDistricts();

                List<String> districtNames = new ArrayList<String>();
                for(int i = 0; i< districtList.size(); i++){
                    DistrictBean.City.District district = districtList.get(i);
                    districtNames.add(district.getDistrict_name());
                }

                leftDatas.clear();
                leftDatas.addAll(districtNames);
                leftAdapter.notifyDataSetChanged();


                List<String> neighborhoods= new ArrayList<String>(districtList.get(0).getNeighborhoods());
                String districtName = districtList.get(0).getDistrict_name();
                neighborhoods.add(0,"全部"+districtName);

                rightDatas.clear();
                rightDatas.addAll(neighborhoods);
                rightAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<DistrictBean> call, Throwable throwable) {

            }
        });

    }

    @OnClick(R.id.tv_business_textview1)
    public void showDistricts(View view){
        if(districtLayout.getVisibility()!=View.VISIBLE){
            districtLayout.setVisibility(View.VISIBLE);
        }else{
            districtLayout.setVisibility(View.INVISIBLE);
        }
    }
}
