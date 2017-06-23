package com.tarena.groupon.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.tarena.groupon.R;
import com.tarena.groupon.bean.CitynameBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by pjy on 2017/6/21.
 */
public class CityAdapter extends RecyclerView.Adapter<CityAdapter.MyViewHolder> implements SectionIndexer{

    //声明基本的属性
    //上下文
    Context context;
    //数据源
    List<CitynameBean> datas;
    //LayoutInflater
    LayoutInflater inflater;

    //为RecyclerView添加的条目监听器
    OnItemClickListener listener;

    //为RecyclerView添加的一个头部视图
    View headerView;

    private static final int HEADER = 0;
    private static final int ITEM = 1;


    //构造器中完成对属性的初始化
    public CityAdapter(Context context,List<CitynameBean> datas){

        this.context = context;
        this.datas = datas;
        this.inflater = LayoutInflater.from(context);

    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

    public void addHeaderView(View v){
        if(this.headerView==null) {
            this.headerView = v;
            notifyItemChanged(0);
        }else{
            throw new RuntimeException("不允许添加多个头部");
        }
    }

    public View getHeaderView(){
        return headerView;
    }


    @Override
    public int getItemViewType(int position) {

        if(this.headerView!=null){

            if(position==0){
                return HEADER;
            }else{
                return ITEM;
            }

        }else{
            return ITEM;
        }

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if(viewType==HEADER){

            MyViewHolder myViewHolder = new MyViewHolder(headerView);
            return myViewHolder;
        }

        //创建ViewHolder
        View view = inflater.inflate(R.layout.item_cityname_layout,parent,false);

        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        if(headerView!=null && position==0){
            return;
        }

        final int dataIndex = getDataIndex(position);

        //将第position位置的数据放到ViewHolder中显示
        CitynameBean citynameBean = datas.get(dataIndex);
        holder.tvName.setText(citynameBean.getCityName());
        holder.tvLetter.setText(citynameBean.getLetter()+"");
        //position这个位置的数据是不是该数据所属分组的起始位置
        if(dataIndex == getPositionForSection(getSectionForPosition(dataIndex))){
            holder.tvLetter.setVisibility(View.VISIBLE);
        }else{
            holder.tvLetter.setVisibility(View.GONE);
        }

        View itemView = holder.itemView;
        if(this.listener!=null){
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.OnItemClick(view,dataIndex);
                }
            });
        }
    }

    private int getDataIndex(int position) {

        return headerView==null?position:position-1;
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

    @Override
    public Object[] getSections() {
        return null;
    }

    /**
     * 某一个分组的起始位置是什么
     * @param section
     * @return
     */
    @Override
    public int getPositionForSection(int section) {

        for(int i=0;i<datas.size();i++){

            if(datas.get(i).getLetter()==section){

                return i;
            }

        }
        //当前的数据源(datas)中没有任何一个数据属于传入的section分组
        //只要返回一个数据源中不存在的下标值即可。datas.size()或更大，-1或更小
        //TODO ???这个值如何返回
        return datas.size()+1;
    }

    /**
     * 第position位置上的数据的分组是什么
     * @param position
     * @return
     */
    @Override
    public int getSectionForPosition(int position) {

        return datas.get(position).getLetter();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        //利用ButterKnife完成对ViewHolder中控件的赋值
        //显示城市拼音首字母
        @Nullable
        @BindView(R.id.tv_item_city_letter)
        TextView tvLetter;
        //显示城市中文名称
        @Nullable
        @BindView(R.id.tv_item_city_name)
        TextView tvName;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public interface OnItemClickListener{
        void OnItemClick(View itemView,int position);
    }
}
