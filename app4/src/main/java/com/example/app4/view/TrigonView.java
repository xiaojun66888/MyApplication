package com.example.app4.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 *三角形
 */

public class TrigonView extends View {
    public TrigonView(Context context) {
        super(context);
    }

    public TrigonView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TrigonView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
        //实例化路径
        Path path = new Path();
        path.moveTo(80, 200);
        path.lineTo(120,250);
        path.lineTo(80,250);
        path.close(); // 使这些点构成封闭的多边形
        //绘制
        canvas.drawPath(path,paint);
    }
}
