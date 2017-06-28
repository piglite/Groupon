package com.tarena.groupon.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.utils.DistanceUtil;
import com.tarena.groupon.R;
import com.tarena.groupon.app.MyApp;
import com.tarena.groupon.bean.BusinessBean;
import com.tarena.groupon.util.HttpUtil;

import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;



/**
 * Created by pjy on 2017/6/26.
 */
public class BusinessAdapter extends MyBaseAdapter<BusinessBean.Business>{
    public BusinessAdapter(Context context, List<BusinessBean.Business> datas) {
        super(context, datas);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        BusinessAdapter.ViewHolder vh;
        if(view ==null){
            view= inflater.inflate(R.layout.item_business_layout,viewGroup,false);
            vh = new ViewHolder(view);
            view.setTag(vh);
        }else{
            vh = (ViewHolder) view.getTag();
        }

        BusinessBean.Business item = getItem(i);

        HttpUtil.loadImage(item.getPhoto_url(),vh.ivPic);

        String name = item.getName().substring(0,item.getName().indexOf("("));
        if(!TextUtils.isEmpty(item.getBranch_name())){
            name = name + "(" + item.getBranch_name()+")";
        }
        vh.tvName.setText(name);

        int[] stars = new int[]{R.drawable.movie_star10,
                R.drawable.movie_star20,
                R.drawable.movie_star30,
                R.drawable.movie_star35,
                R.drawable.movie_star40,
                R.drawable.movie_star45,
                R.drawable.movie_star50};
        Random rand = new Random();
        int idx = rand.nextInt(7);
        vh.ivRating.setImageResource(stars[idx]);

        int price = rand.nextInt(100)+50;

        vh.tvPrice.setText("￥"+price+"/人");

        StringBuilder sb = new StringBuilder();

        for(int j=0;j<item.getRegions().size();j++){

            if(j==0){
                sb.append(item.getRegions().get(j));
            }else{
                sb.append("/").append(item.getRegions().get(j));
            }


        }

        sb.append(" ");

        for(int j=0;j<item.getCategories().size();j++){
            if(j==0){
                sb.append(item.getCategories().get(j));
            }else{
                sb.append("/").append(item.getCategories().get(j));
            }
        }

        vh.tvInfo.setText(sb.toString());

        //TODO vh.tvDistance 学习完了定位之后再回来改写
        if(MyApp.myLocation!=null){
            //double distance = DistanceUtil.getDistance(item.getLongitude(), item.getLatitude(), MyApp.myLocation.longitude, MyApp.myLocation.latitude);
            double distance = DistanceUtil.getDistance(new LatLng(item.getLatitude(),item.getLongitude()),MyApp.myLocation);
            vh.tvDistance.setText(distance+"米");
        }else{
            vh.tvDistance.setText("");
        }



        return view;
    }

    public class ViewHolder{

        @BindView(R.id.iv_business_item)
        ImageView ivPic;
        @BindView(R.id.tv_business_item_name)
        TextView tvName;
        @BindView(R.id.iv_business_item_rating)
        ImageView ivRating;
        @BindView(R.id.tv_business_item_avg_price)
        TextView tvPrice;
        @BindView(R.id.tv_business_item_info)
        TextView tvInfo;
        @BindView(R.id.tv_business_item_distance)
        TextView tvDistance;


        public ViewHolder(View view){

            ButterKnife.bind(this,view);

        }



    }
}
