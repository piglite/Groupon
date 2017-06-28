package com.tarena.groupon.adapter;

import android.content.Context;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tarena.groupon.R;
import com.tarena.groupon.bean.Comment;
import com.tarena.groupon.util.HttpUtil;
import com.tarena.groupon.view.CircleImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by pjy on 2017/6/28.
 */
public class CommentAdapter extends MyBaseAdapter<Comment> {

    public CommentAdapter(Context context, List<Comment> datas) {
        super(context, datas);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final ViewHolder vh;
        if (view == null) {
            view = inflater.inflate(R.layout.item_comment_layout, viewGroup, false);
            vh = new ViewHolder(view);
            vh.llContainer.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {

                    int width = vh.llContainer.getWidth();
                    int margin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,3,context.getResources().getDisplayMetrics());
                    int size = (width - 2*margin)/3;
                    for(int i=0;i<vh.llContainer.getChildCount();i++){
                        ImageView iv = (ImageView) vh.llContainer.getChildAt(i);
                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(size,size);
                        if(i!=0) {
                            params.setMargins(margin, 0, 0, 0);
                        }
                        iv.setLayoutParams(params);
                        iv.setScaleType(ImageView.ScaleType.FIT_XY);

                    }
                    vh.llContainer.requestLayout();

                }
            });

            view.setTag(vh);
        } else {
            vh = (ViewHolder) view.getTag();
        }

        Comment comment = getItem(i);

        HttpUtil.loadImage(comment.getAvatar(), vh.ivAvatar);

        vh.tvName.setText(comment.getName());
        vh.tvDate.setText(comment.getDate());
        vh.tvPrice.setText(comment.getPrice());

        int[] resIds = new int[]{
                R.drawable.movie_star10,
                R.drawable.movie_star20,
                R.drawable.movie_star30,
                R.drawable.movie_star35,
                R.drawable.movie_star40,
                R.drawable.movie_star45,
                R.drawable.movie_star50
        };
        //star40
        int resId = 0;
        String rating = comment.getRating();
        if (rating.contains("20")) {
            resId = 1;
        } else if (rating.contains("30")) {
            resId = 2;
        } else if (rating.contains("35")) {
            resId = 3;
        } else if (rating.contains("40")) {
            resId = 4;
        } else if (rating.contains("45")) {
            resId = 5;
        } else if (rating.contains("50")) {
            resId = 6;
        }

        vh.ivRating.setImageResource(resIds[resId]);

        vh.tvContent.setText(comment.getContent());

        String[] imgs = comment.getImgs();
        ImageView[] ivs = new ImageView[3];
        ivs[0] = vh.ivImg1;
        ivs[1] = vh.ivImg2;
        ivs[2] = vh.ivImg3;
        for (int idx = 0; idx < 3; idx++) {
            ivs[idx].setVisibility(View.GONE);
        }
        for (int idx = 0; idx < 3; idx++) {
            ivs[idx].setVisibility(View.VISIBLE);
            HttpUtil.loadImage(imgs[idx], ivs[idx]);
        }


        return view;
    }

    public class ViewHolder {

        @BindView(R.id.iv_comment_item_avatar)
        CircleImageView ivAvatar;
        @BindView(R.id.tv_comment_item_username)
        TextView tvName;
        @BindView(R.id.tv_comment_item_date)
        TextView tvDate;
        @BindView(R.id.tv_comment_item_avg_price)
        TextView tvPrice;
        @BindView(R.id.iv_comment_item_rating)
        ImageView ivRating;
        @BindView(R.id.tv_comment_item_content)
        TextView tvContent;
        @BindView(R.id.iv_comment_item_img1)
        ImageView ivImg1;
        @BindView(R.id.iv_comment_item_img2)
        ImageView ivImg2;
        @BindView(R.id.iv_comment_item_img3)
        ImageView ivImg3;
        @BindView(R.id.ll_comment_item_container)
        LinearLayout llContainer;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

    }
}
