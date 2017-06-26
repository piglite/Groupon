package com.tarena.groupon.util;

import com.tarena.groupon.bean.BusinessBean;
import com.tarena.groupon.bean.CityBean;
import com.tarena.groupon.bean.DistrictBean;
import com.tarena.groupon.bean.TuanBean;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by pjy on 2017/6/19.
 */

public interface NetService {
    @GET("business/find_businesses")
    public Call<String> test(@Query("appkey") String appkey, @Query("sign") String sign, @QueryMap Map<String,String> params);

    @GET("deal/get_daily_new_id_list")
    public Call<String> getDailyIds(@Query("appkey") String appkey,@Query("sign") String sign,@QueryMap Map<String,String> params);

    @GET("deal/get_daily_new_id_list")
    public Call<String> getDailyIds2(@QueryMap Map<String,String> params);

    @GET("deal/get_batch_deals_by_id")
    public Call<TuanBean> getDailyDeals3(@QueryMap Map<String,String> params);

    @GET("deal/get_batch_deals_by_id")
    public Call<String> getDailyDeals(@Query("appkey") String appkey,@Query("sign") String sign,@QueryMap Map<String,String> params);

    @GET("deal/get_batch_deals_by_id")
    public Call<TuanBean> getDailyDeals2(@Query("appkey") String appkey,@Query("sign") String sign,@QueryMap Map<String,String> params);

    @GET("metadata/get_cities_with_businesses")
    public Call<CityBean> getCities();

    @GET("business/find_businesses")
    public Call<BusinessBean> getFoods(@QueryMap Map<String,String> params);

    @GET("metadata/get_regions_with_businesses")
    public Call<DistrictBean> getDistricts(@QueryMap Map<String,String> params);
}
