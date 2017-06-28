package com.tarena.groupon.ui;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeOption;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.tarena.groupon.R;
import com.tarena.groupon.app.MyApp;
import com.tarena.groupon.bean.BusinessBean;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FindActivity extends Activity {

    BusinessBean.Business business;
    @BindView(R.id.bmapView)
    MapView mMapView;

    BaiduMap baiduMap;

    String from;//main,detail

    public LocationClient mLocationClient = null;
    public BDLocationListener myListener = new MyLocationListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_find);
        from = getIntent().getStringExtra("from");
        business = (BusinessBean.Business) getIntent().getSerializableExtra("business");
        ButterKnife.bind(this);
        baiduMap = mMapView.getMap();
        //更改地图默认的比例尺(5km--->100m)
        MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(17);
        baiduMap.animateMapStatus(msu);
        if("main".equals(from)){

            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                //判定权限
                if(checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
                    requestPermissions(new String[]{
                            Manifest.permission.READ_PHONE_STATE,
                            Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                    },100);
                }else{
                    showMyLocation();
                }
            }else{
                showMyLocation();//进行定位
            }

        }else {
            showAddress();
        }

    }

    /**
     * 对当前设备使用者的位置进行定位
     */
    private void showMyLocation() {
        //声明LocationClient类
        mLocationClient = new LocationClient(getApplicationContext());
        //注册监听函数
        mLocationClient.registerLocationListener( myListener );

        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //可选，默认高精度，设置定位模式，高精度，低功耗，仅设备

        option.setCoorType("bd09ll");
        //可选，默认gcj02，设置返回的定位结果坐标系

        int span= 0;
        option.setScanSpan(span);
        //可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的

        option.setIsNeedAddress(true);
        //可选，设置是否需要地址信息，默认不需要

        option.setOpenGps(true);
        //可选，默认false,设置是否使用gps

        option.setLocationNotify(true);
        //可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果

        option.setIsNeedLocationDescribe(true);
        //可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”

        option.setIsNeedLocationPoiList(true);
        //可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到

        option.setIgnoreKillProcess(false);
        //可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死

        option.SetIgnoreCacheException(false);
        //可选，默认false，设置是否收集CRASH信息，默认收集

        option.setEnableSimulateGps(false);
        //可选，默认false，设置是否需要过滤GPS仿真结果，默认需要

        mLocationClient.setLocOption(option);

        //真正发起定位
        mLocationClient.start();


    }

    /**
     * 在百度地图上显示某地址
     *
     */
    private void showAddress() {

        //1)根据地址查询出所对应的经纬度（地理编码查询）
        //(根据经纬度反查具体地址，称为反向地理编码查询)
        GeoCoder geoCoder = GeoCoder.newInstance();
        geoCoder.setOnGetGeoCodeResultListener(new OnGetGeoCoderResultListener() {
            @Override
            public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {
                if(geoCodeResult==null && geoCodeResult.error != SearchResult.ERRORNO.NO_ERROR){
                    Toast.makeText(FindActivity.this, "服务器繁忙，请稍后重试", Toast.LENGTH_SHORT).show();
                }else{
                    //地址所对应的经纬度
                    LatLng location = geoCodeResult.getLocation();
                    //2)在location所对应的经纬度插上一个标志物

                    MarkerOptions option = new MarkerOptions();
                    option.position(location);
                    option.icon(BitmapDescriptorFactory.fromResource(R.drawable.home_scen_icon_locate));
                    baiduMap.addOverlay(option);

                    //3)移动屏幕的中心点到location所对应的位置
                    MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(location);
                    baiduMap.animateMapStatus(msu);

                    //4)添加一个信息窗

                    TextView tv = new TextView(FindActivity.this);
                    tv.setText(business.getAddress());
                    tv.setPadding(8,8,8,8);
                    tv.setBackgroundColor(Color.GRAY);
                    tv.setTextColor(Color.BLUE);
                    tv.setTextSize(TypedValue.COMPLEX_UNIT_SP,12);
                    InfoWindow infoWindow = new InfoWindow(tv,location,-50);
                    baiduMap.showInfoWindow(infoWindow);
                }
            }

            @Override
            public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {
            }
        });
        //真正发起地理编码查询
        GeoCodeOption option = new GeoCodeOption();
        option.address(business.getAddress());
        option.city(business.getCity());
        geoCoder.geocode(option);



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mLocationClient!=null){
            mLocationClient.stop();
            mLocationClient.unRegisterLocationListener(myListener);
            mLocationClient = null;
        }

        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }
    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }

    public class MyLocationListener implements BDLocationListener{

        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            int type = bdLocation.getLocType();
            double lat = -1;
            double lng = -1;

            if(type==61||type==65||type==66||type==161){
                //定位成功了
                lat = bdLocation.getLatitude();
                lng = bdLocation.getLongitude();
            }else{
                //定位失败了
                //手动指定一个位置 潘家园建业苑写字楼
                lng = 116.465037;
                lat = 39.876425;

            }

            //1)添加标志物

            LatLng location = new LatLng(lat,lng);

            MyApp.myLocation = location;

            MarkerOptions option = new MarkerOptions();
            option.position(location);
            option.icon(BitmapDescriptorFactory.fromResource(R.drawable.home_scen_icon_locate));
            baiduMap.addOverlay(option);

            //2)移动屏幕中心点
            MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(location);
            baiduMap.animateMapStatus(msu);
            //3)信息窗("我在这")
            TextView tv = new TextView(FindActivity.this);
            tv.setText("我在这");
            tv.setPadding(8,8,8,8);
            tv.setBackgroundColor(Color.RED);
            tv.setTextColor(Color.WHITE);
            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP,12);
            InfoWindow infoWindow = new InfoWindow(tv,location,-50);
            baiduMap.showInfoWindow(infoWindow);
            //4)停止定位
            mLocationClient.stop();
            mLocationClient.unRegisterLocationListener(this);
            mLocationClient = null;




        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==100 ){

            showMyLocation();

        }
    }
}
