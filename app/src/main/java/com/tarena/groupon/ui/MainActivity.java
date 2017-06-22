package com.tarena.groupon.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.tarena.groupon.R;
import com.tarena.groupon.adapter.DealAdapter;
import com.tarena.groupon.bean.TuanBean;
import com.tarena.groupon.util.HttpUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends Activity {

    //头部
    @BindView(R.id.ll_header_left_container)
    LinearLayout cityContainer;
    @BindView(R.id.tv_header_main_city)
    TextView tvCity;//显示城市名称
    @BindView(R.id.iv_header_main_add)
    ImageView ivAdd;
    @BindView(R.id.menu_layout)
    View menuLayout;


    //中段
    @BindView(R.id.ptrlv_main)
    PullToRefreshListView ptrListView;

    ListView listView;
    List<TuanBean.Deal> datas;
    DealAdapter adapter;

    //脚部
    @BindView(R.id.rg_main_footer)
    RadioGroup rg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(
                R.layout.activity_main);
        ButterKnife.bind(this);
        initListView();

    }

    @OnClick(R.id.ll_header_left_container)
    public void jumpToCity(View view) {
        Intent intent = new Intent(this, CityActivity.class);
        startActivityForResult(intent,101);
    }

    @OnClick(R.id.iv_header_main_add)
    public void toggleMenu(View view) {
        if (menuLayout.getVisibility() == View.VISIBLE) {
            menuLayout.setVisibility(View.INVISIBLE);
        } else {
            menuLayout.setVisibility(View.VISIBLE);
        }

    }


    private void initListView() {

        listView = ptrListView.getRefreshableView();
        datas = new ArrayList<TuanBean.Deal>();
        adapter = new DealAdapter(this, datas);
        //为ListView添加若干个头部
        LayoutInflater inflater = LayoutInflater.from(this);

        View listHeaderIcons = inflater.inflate(R.layout.header_list_icons, listView, false);
        View listHeaderSquares = inflater.inflate(R.layout.header_list_square, listView, false);
        View listHeaderAds = inflater.inflate(R.layout.header_list_ads, listView, false);
        View listHeaderCategories = inflater.inflate(R.layout.header_list_categories, listView, false);
        View listHeaderRecommend = inflater.inflate(R.layout.header_list_recommend, listView, false);

        listView.addHeaderView(listHeaderIcons);
        listView.addHeaderView(listHeaderSquares);
        listView.addHeaderView(listHeaderAds);
        listView.addHeaderView(listHeaderCategories);
        listView.addHeaderView(listHeaderRecommend);

        listView.setAdapter(adapter);

        initListHeaderIcons(listHeaderIcons);

        //添加下拉松手后的刷新
        ptrListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {

               /* new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        datas.add(0,"新增内容");
                        adapter.notifyDataSetChanged();
                        ptrListView.onRefreshComplete();
                    }
                }, 1500);*/

                refresh();

            }
        });


        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {
                if (i == 0) {
                    cityContainer.setVisibility(View.VISIBLE);
                    ivAdd.setVisibility(View.VISIBLE);
                } else {
                    cityContainer.setVisibility(View.GONE);
                    ivAdd.setVisibility(View.GONE);
                }
            }
        });

    }

    private void initListHeaderIcons(View listHeaderIcons) {
        final ViewPager viewPager = (ViewPager) listHeaderIcons.findViewById(R.id.vp_header_list_icons);

        PagerAdapter adapter = new PagerAdapter() {

            int[] resIDs = new int[]{
                    R.layout.icons_list_1,
                    R.layout.icons_list_2,
                    R.layout.icons_list_3
            };

            @Override
            public int getCount() {
                return 30000;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {

                int layoutId = resIDs[position % 3];
                View view = LayoutInflater.from(MainActivity.this).inflate(layoutId, viewPager, false);
                container.addView(view);
                return view;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }
        };

        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(15000);

        final ImageView iv1 = (ImageView) listHeaderIcons.findViewById(R.id.iv_header_list_icons_indicator1);
        final ImageView iv2 = (ImageView) listHeaderIcons.findViewById(R.id.iv_header_list_icons_indicator2);
        final ImageView iv3 = (ImageView) listHeaderIcons.findViewById(R.id.iv_header_list_icons_indicator3);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                iv1.setImageResource(R.drawable.banner_dot);
                iv2.setImageResource(R.drawable.banner_dot);
                iv3.setImageResource(R.drawable.banner_dot);

                switch (position % 3) {
                    case 0:
                        iv1.setImageResource(R.drawable.banner_dot_pressed);
                        break;
                    case 1:
                        iv2.setImageResource(R.drawable.banner_dot_pressed);
                        break;
                    case 2:
                        iv3.setImageResource(R.drawable.banner_dot_pressed);
                        break;
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
//        String city = getIntent().getStringExtra("city");
//        if(!TextUtils.isEmpty(city)) {
//            tvCity.setText(city);
//        }else{
//            tvCity.setText("北京");
//        }
        refresh();

    }

    /**
     * 向ListView中填充数据
     */
    private void refresh() {

        //1)发起一个请求，服务器响应
        //以GET的方式发起请求
        //请求格式：http://xxx.xxxx.com/xxx？key=14xxxxxxx&city=%e8%f8%c6%xx%xx%xx
        //利用HttpClient(apache)
        //HttpURLConnection

        //Volley

        /*HttpUtil.getDailyDealsByVolley(tvCity.getText().toString(), new Response.Listener<TuanBean>() {
            @Override
            public void onResponse(TuanBean s) {
                if (s != null) {
                    List<TuanBean.Deal> deals = s.getDeals();
                    //将deals放到ListView中呈现
                    adapter.addAll(deals, true);
                } else {
                    //今日无新增团购内容
                    Toast.makeText(MainActivity.this, "今日无新增团购内容", Toast.LENGTH_SHORT).show();
                }
                ptrListView.onRefreshComplete();


            }
        });*/

        //Retrofit+OKHttp

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                HttpUtil.getDailyDealsByRetrofit(tvCity.getText().toString(), new Callback<TuanBean>() {
                    @Override
                    public void onResponse(Call<TuanBean> call, retrofit2.Response<TuanBean> response) {
                        if(response!=null){
                            TuanBean tuanBean = response.body();
                            List<TuanBean.Deal> deals = tuanBean.getDeals();
                            adapter.addAll(deals,true);
                        }else{
                            Toast.makeText(MainActivity.this, "今日无新增团购内容", Toast.LENGTH_SHORT).show();
                        }
                        ptrListView.onRefreshComplete();
                    }

                    @Override
                    public void onFailure(Call<TuanBean> call, Throwable throwable) {
                        Log.d("TAG", "onFailure: "+throwable.getMessage());
                        ptrListView.onRefreshComplete();
                    }
                });
            }
        }, 2000);





        //2)根据服务器响应的内容进行解析
        // JSON字符串 / XML文档
        // 解析JSON字符串：
        // JSONLib(JsonObject)
        // GSON
        // fastJson
        // jackson
        // 解析XML文档
        // XMLPull
        // SAX

        //3)将解析结果放到View中显示
        //放到ListView中显示需要适配器、条目布局

        //HttpUtil.testHttpURLConnection();
        //HttpUtil.testVolley();
        //HttpUtil.testRetrofit();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK && requestCode==101){
            String city = data.getStringExtra("city");
            tvCity.setText(city);
        }
    }
}
