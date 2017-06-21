package com.tarena.groupon.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tarena.groupon.R;
import com.tarena.groupon.bean.CitynameBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by pjy on 2017/6/21.
 */
public class CityAdapter extends RecyclerView.Adapter<CityAdapter.MyViewHolder>{

    //声明基本的属性
    //上下文
    Context context;
    //数据源
    List<CitynameBean> datas;
    //LayoutInflater
    LayoutInflater inflater;


    //构造器中完成对属性的初始化
    public CityAdapter(Context context,List<CitynameBean> datas){

        this.context = context;
        this.datas = datas;
        this.inflater = LayoutInflater.from(context);

    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //创建ViewHolder
        View view = inflater.inflate(R.layout.item_cityname_layout,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //将第position位置的数据放到ViewHolder中显示
        CitynameBean citynameBean = datas.get(position);
        holder.tvName.setText(citynameBean.getCityName());
        holder.tvLetter.setText(citynameBean.getLetter()+"");
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public void addAll(List<CitynameBean> list,boolean isClear){
        if(isClear){

            datas.clear();

        }

        datas.addAll(list);
        notifyDataSetChanged();

    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        //利用ButterKnife完成对ViewHolder中控件的赋值
        //显示城市拼音首字母
        @BindView(R.id.tv_item_city_letter)
        TextView tvLetter;
        //显示城市中文名称
        @BindView(R.id.tv_item_city_name)
        TextView tvName;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
