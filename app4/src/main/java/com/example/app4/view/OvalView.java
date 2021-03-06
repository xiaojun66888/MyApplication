package com.example.app4.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 *椭圆
 */

public class OvalView extends View {
    public OvalView(Context context) {
        super(context);
    }

    public OvalView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public OvalView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
        RectF rectF = new RectF(60, 100, 200, 240);
        rectF.set(210,100,250,130);
        //绘制
        canvas.drawOval(rectF,paint);
    }
}
