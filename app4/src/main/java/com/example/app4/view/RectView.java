package com.example.app4.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2017/3/24.
 */

public class RectView extends View {
    public RectView(Context context) {
        super(context);
    }

    public RectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);//设置实心
        paint.setTypeface(Typeface.DEFAULT_BOLD);
        paint.setStrokeWidth(40);// 设置paint的外框宽度
        // 设置画笔的锯齿效果
        paint.setAntiAlias(true);
        //绘制
        canvas.drawRect(50, 100, 300, 300, paint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width;
        int height;
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        if(widthMode==MeasureSpec.EXACTLY){
            width = widthMode;
        }else{
            width =200;
            if(widthMode==MeasureSpec.AT_MOST){
                width = Math.min(width,widthSize);
            }
        }
        if(heightMode==MeasureSpec.EXACTLY){
            height = heightMode;
        }else{
            height =200;
            if(heightMode==MeasureSpec.AT_MOST){
                height = Math.min(height,heightSize);
            }
        }
    }
}
