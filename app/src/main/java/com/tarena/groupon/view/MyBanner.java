package com.tarena.groupon.view;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.tarena.groupon.R;

/**
 * Created by pjy on 2017/6/26.
 */

public class MyBanner extends FrameLayout {

    ViewPager viewPager;
    ImageView ivClose;
    LinearLayout llContainer;
    PagerAdapter adapter;

    int[] resIds;//viewPager管理的所有图片(用户看到的图片+2)

    Handler handler = new Handler();
    boolean flag;

    OnCloseBannerListener listener;

    public MyBanner(Context context,int[] ids) {
        super(context);
        if(ids!=null&&ids.length>0){
            //创建MyBanner时，创建者传入了要显示的轮播图片
            this.resIds = new int[ids.length+2];
            this.resIds[0] = ids[ids.length-1];
            this.resIds[this.resIds.length-1] = ids[0];
            for(int i=0;i<ids.length;i++){
                this.resIds[i+1] = ids[i];

            }

        }else{
            //使用默认的轮播图片(banner_1~banner_4)
            resIds = new int[]{R.drawable.banner_4,R.drawable.banner_1,R.drawable.banner_2,R.drawable.banner_3,R.drawable.banner_4,R.drawable.banner_1};

        }
        View view = LayoutInflater.from(getContext()).inflate(R.layout.banner_layout,this,false);
        this.addView(view);
        initView(view);
        start();//开始自动轮播

    }

    public void setOnCloseBannerListener(OnCloseBannerListener listener){
        this.listener = listener;
    }

    private void stop(){
        flag = false;
        handler.removeCallbacksAndMessages(null);
    }

    private void start() {

        flag = true;

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(flag){
                    int idx = viewPager.getCurrentItem();
                    viewPager.setCurrentItem(idx+1);
                    handler.postDelayed(this,3000);
                }
            }
        }, 3000);


    }

    private void initView(View view) {

        viewPager = (ViewPager) view.findViewById(R.id.vp_banner);
        ivClose = (ImageView) view.findViewById(R.id.iv_banner);
        llContainer = (LinearLayout) view.findViewById(R.id.ll_banner);

        adapter = new PagerAdapter() {
            @Override
            public int getCount() {
                return resIds.length;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {

                ImageView iv = new ImageView(getContext());
                int resId = resIds[position];
                iv.setImageResource(resId);
                iv.setScaleType(ImageView.ScaleType.FIT_XY);
                container.addView(iv);

                return iv;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
               container.removeView((View)object);
            }
        };

        viewPager.setAdapter(adapter);

        viewPager.setCurrentItem(1);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position==0){
                    viewPager.setCurrentItem(resIds.length-2,false);
                    setIndicator(llContainer.getChildCount()-1);
                }else if(position==resIds.length-1){
                    viewPager.setCurrentItem(1,false);
                    setIndicator(0);
                }else{
                    setIndicator(position-1);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        viewPager.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                int action = motionEvent.getAction();
                if(action==MotionEvent.ACTION_DOWN){
                    stop();
                }

                if(action == MotionEvent.ACTION_UP){
                    start();
                }

                return false;
            }
        });

        //llContainer中填充对应数量的ImageView
        //作为滑动指示器
        for(int i=0;i<resIds.length-2;i++){
            ImageView iv = new ImageView(getContext());

            iv.setImageResource(R.drawable.banner_dot);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);

            int margin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,3,getResources().getDisplayMetrics());
            params.setMargins(margin,0,margin,0);

            iv.setLayoutParams(params);

            llContainer.addView(iv);

        }

        setIndicator(0);

        ivClose.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener!=null){
                    listener.onClose();
                }
            }
        });

    }

    /**
     * 设置某“指示器”中图片的显示为橘红色图片
     *
     * @param idx
     */
    private void setIndicator(int idx) {

        for(int i=0;i<llContainer.getChildCount();i++){
            ImageView iv = (ImageView) llContainer.getChildAt(i);
            if(i==idx){
                iv.setImageResource(R.drawable.banner_dot_pressed);
            }else{
                iv.setImageResource(R.drawable.banner_dot);
            }
        }

    }

    public interface OnCloseBannerListener{
        void onClose();
    }
}
