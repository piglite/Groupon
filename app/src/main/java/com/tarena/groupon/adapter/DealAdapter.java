package com.tarena.groupon.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tarena.groupon.R;
import com.tarena.groupon.bean.TuanBean;
import com.tarena.groupon.util.HttpUtil;

import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by pjy on 2017/6/20.
 */

public class DealAdapter extends MyBaseAdapter<TuanBean.Deal>{

    public DealAdapter(Context context, List<TuanBean.Deal> datas) {
        super(context, datas);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder vh;
        if(view==null){

            view = inflater.inflate(R.layout.item_deal_layout,viewGroup,false);
            vh = new ViewHolder(view);
            view.setTag(vh);
        }else{

            vh = (ViewHolder) view.getTag();

        }

        TuanBean.Deal deal = getItem(i);

        //呈现图片 GLIDE / universal-ImageLoader
        HttpUtil.displayImage(deal.getS_image_url(),vh.ivPic);
        vh.tvTitle.setText(deal.getTitle());
        vh.tvDetail.setText(deal.getDescription());
        vh.tvPrice.setText(deal.getCurrent_price()+"");
        Random random = new Random();
        int count = random.nextInt(2000)+500;
        vh.tvCount.setText("已售"+count);
        //TODO 距离 vh.tvDistance.setText("xxxx");
        return view;
    }

    public class ViewHolder{
        @BindView(R.id.iv_item_deal_image)
        ImageView ivPic;
        @BindView(R.id.tv_item_deal_name)
        TextView tvTitle;
        @BindView(R.id.tv_item_deal_detail)
        TextView tvDetail;
        @BindView(R.id.tv_item_deal_distance)
        TextView tvDistance;
        @BindView(R.id.tv_item_deal_price)
        TextView tvPrice;
        @BindView(R.id.tv_item_deal_sellcount)
        TextView tvCount;

        public ViewHolder(View view){
            ButterKnife.bind(this,view);
        }



    }
}
