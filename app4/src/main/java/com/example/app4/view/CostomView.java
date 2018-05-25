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

public class CostomView extends View {
    public CostomView(Context context) {
        super(context);
    }

    public CostomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CostomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();// 定义画笔
        paint.setStyle(Paint.Style.FILL);//设置实心
        paint.setAntiAlias(true);// 消除锯齿
        paint.setColor(Color.RED);//设置画笔颜色
        paint.setStrokeWidth(40);// 设置paint的外框宽度
        canvas.drawRect(200, 200, 800, 220, paint);//绘制矩形
        /**
         * 画最外层的大圆环
         */
        Paint paint1 = new Paint();
        paint1.setColor(Color.RED); //画笔颜色
        paint1.setStyle(Paint.Style.STROKE); //设置画笔实心
        paint1.setAntiAlias(true);// 消除锯齿
        paint1.setStrokeWidth(20);//设置外框宽度
        canvas.drawCircle(350, 450, 100, paint1);// 用画笔在画布上添加一个圆
        /**
         * 画进度百分比
         */
/*        paint1.setStrokeWidth(0);
        paint1.setColor(Color.GREEN);
        paint1.setTextSize(15);
        paint1.setTypeface(Typeface.DEFAULT_BOLD);*/


    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }
}
