package com.tarena.groupon.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import com.tarena.groupon.R;


/**
 * 自定义View
 * 进行城市分组的快速导航
 *
 * Created by pjy on 2017/6/23.
 */

public class MyLetterView extends View {

    private String[] letters = {
            "热门","A","B","C","D","E",
            "F","G","H","I","J",
            "K","L","M","N","O",
            "P","Q","R","S","T",
            "U","V","W","X","Y","Z"

    };

    Paint paint;

    OnTouchLetterListener listener;

    int letterColor;




    public MyLetterView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray t = context.obtainStyledAttributes(attrs, R.styleable.MyLetterView);
        letterColor = t.getColor(R.styleable.MyLetterView_letter_color,Color.BLACK);
        t.recycle();

        initPaint();
    }

    public void setOnTouchLetterListener(OnTouchLetterListener listener){
        this.listener = listener;
    }

    /**
     * 画笔的初始化
     */
    private void initPaint() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //用来使用这根画笔绘制出来的文字的大小
        //20sp在当前设备上所对应的像素的大小
        float size = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,14,getResources().getDisplayMetrics());
        paint.setTextSize(size);
        paint.setColor(letterColor);

    }

    /**
     * 是用来设定MyLetterView尺寸(宽高)
     *
     * 并不一定需要重写
     * View的onMeasure已经有了很多的设定尺寸的代码，可以使用
     * 只有当View的onMeasure设定尺寸的代码逻辑不能满足实际要求时
     * 才有必要进行"改写"
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //改写时一定要保留View的onMeasure方法调用
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //进行针对WRAP_CONTENT的改写
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        if(mode == MeasureSpec.AT_MOST){
            int lPadding = getPaddingLeft();
            int rPadding = getPaddingRight();
            int contentWidth = 0;
            for(int i=0;i<letters.length;i++){
                String letter = letters[i];

                Rect bounds = new Rect();
                paint.getTextBounds(letter,0,letter.length(),bounds);

                if(bounds.width()>contentWidth){
                    contentWidth = bounds.width();
                }

            }
            int size = lPadding + contentWidth+rPadding;
            setMeasuredDimension(size,MeasureSpec.getSize(heightMeasureSpec));

        }

    }

    /**
     * 一定要重写
     *
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();//MyLetterView的宽度
        int height = getHeight();//MyLtterView的宽度

        for(int i=0;i<letters.length;i++) {
            String letter = letters[i];
            //获取文字的边界大小
            Rect bounds = new Rect();
            paint.getTextBounds(letter,0,letter.length(),bounds);
            //bounds.width() / bounds.height()
            float x = width/2 - bounds.width()/2; //分配给letter小空间的宽度一半-letter宽度的一半
            float y = height/letters.length/2 + bounds.height()/2 + i*height/letters.length; //分配给letter小空间的高度一半+letter高度的一半
            canvas.drawText(letter, x, y, paint);
        }


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //按下，还是移动，还是抬起
        int action = event.getAction();
        switch (action){
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                //改变背景
                setBackgroundColor(Color.GRAY);

                if(listener!=null){
                    //手指按下或移动时距离MyLetterView顶的距离
                    float y = event.getY();
                    //根据距离(y)换算文字的下标值

                    int idx = (int) ((y*letters.length)/getHeight());
                    if(idx>=0 && idx<letters.length){
                        String letter = letters[idx];
                        listener.onTouchLetter(this,letter);
                    }


                }
            break;

            default:
                setBackgroundColor(Color.TRANSPARENT);

            break;
        }


        return true;
    }


    public interface OnTouchLetterListener{
        void onTouchLetter(MyLetterView view,String letter);
    }
}
