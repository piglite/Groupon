/*******************************************************************************
 * Copyright 2011, 2012 Chris Banes.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.handmark.pulltorefresh.library.internal;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ScaleDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView.ScaleType;

import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Orientation;
import com.handmark.pulltorefresh.library.R;

public class RotateLoadingLayout extends LoadingLayout {

    int[] resIds = new int[]{
            R.drawable.dropdown_anim_00,
            R.drawable.dropdown_anim_01,
            R.drawable.dropdown_anim_02,
            R.drawable.dropdown_anim_03,
            R.drawable.dropdown_anim_04,
            R.drawable.dropdown_anim_05,
            R.drawable.dropdown_anim_06,
            R.drawable.dropdown_anim_07,
            R.drawable.dropdown_anim_08,
            R.drawable.dropdown_anim_09,
            R.drawable.dropdown_anim_10
    };

    /**
     * 如果声明了自有属性，则在构造器中完成属性的初始化
     * @param context
     * @param mode
     * @param scrollDirection
     * @param attrs
     */
    public RotateLoadingLayout(Context context, Mode mode, Orientation scrollDirection, TypedArray attrs) {
        super(context, mode, scrollDirection, attrs);
    }

    /**
     * 原生：对自有属性中旋转动画的中心点进行赋值
     * 咱们：no-op
     * @param imageDrawable
     */
    public void onLoadingDrawableSet(Drawable imageDrawable) {
        //NO-OP
    }

    /**
     * 回调方法
     * 只要处于下拉动作，该方法会被不断的调用
     *
     * 原生：随着下拉的距离，计算一个应该旋转的角度
     *      利用Matrix计算旋转该角度时，ImageView每个点的坐标
     * 咱们：随着下拉的距离，不断的切换图片
     * @param scaleOfLayout 下拉距离用该参数表示，取值范围0~无限大的一个小数
     */
    protected void onPullImpl(float scaleOfLayout) {

        //根据scaleOfLayout来切换图片？？

        int idx = (int) (scaleOfLayout * 10) + 1;

        if (idx <= 10) {
            //根据原始图片的大小创建一个缩小的图片

            int id = resIds[idx];
            Bitmap src = BitmapFactory.decodeResource(getResources(), id);
            int width = (int) (src.getWidth() * idx / 10);
            int height = (int) (src.getHeight() * idx / 10);
            Bitmap dest = Bitmap.createScaledBitmap(src, width, height, true);
            mHeaderImage.setImageBitmap(dest);
            //另外一种思路：利用原始图片创建一个ScaleDrawable
            //利用Eclipse SDK4.2环境下，以下代码测试正常
            //AS SDK6.0 无效果~~~ -.-!!!
            //Drawable drawable = getContext().getDrawable(id);
            //drawable.setLevel(100);
            //ScaleDrawable sd = new ScaleDrawable(drawable, Gravity.CENTER,0.5f,0.5f);
            //mHeaderImage.setImageDrawable(sd);


        } else {
            int resId = resIds[10];
            mHeaderImage.setImageResource(resId);
        }


    }

    /**
     * 回调方法
     * 当下拉到一定位置松手后，进入到刷新状态时被回调
     * 原生：让ImageView播放一个无限旋转的旋转补间动画
     * 咱们：让ImageView播放一个吃包子的帧动画
     */
    @Override
    protected void refreshingImpl() {
        mHeaderImage.setImageResource(R.drawable.refreshing_anim);
        Drawable drawable = mHeaderImage.getDrawable();
        ((AnimationDrawable) drawable).start();
    }

    /**
     * 回调方法
     * 当刷新完毕，头部缩起时(当PullToRefreshListView的onRefreshComplete方法被调用时)调用
     *
     * 原生：停止ImageView的旋转补间动画
     *      重置Matrix
     *
     * 咱们：没有必要让帧动画停止
     *
     */
    @Override
    protected void resetImpl() {
        //NO-OP
    }

    @Override
    protected void pullToRefreshImpl() {
        // NO-OP
    }

    @Override
    protected void releaseToRefreshImpl() {
        // NO-OP
    }

    /**
     * 返回默认使用的图片
     * 原生：双向箭头图片
     * 咱们：小孩吃包子的第一张图片
     * @return
     */
    @Override
    protected int getDefaultDrawableResId() {

        return R.drawable.dropdown_anim_00;
    }

}
