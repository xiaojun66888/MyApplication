package com.example.app4.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by Administrator on 2017/3/24.
 */

public class CircleView extends View {
    private int width;
    private int height;

    public CircleView(Context context) {
        super(context);
        init();
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        width = wm.getDefaultDisplay().getWidth();
        height = wm.getDefaultDisplay().getHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);//设置实心
        paint.setTypeface(Typeface.DEFAULT_BOLD);
        paint.setStrokeWidth(40);// 设置paint的外框宽度
        // 设置画笔的锯齿效果
        paint.setAntiAlias(true);
        //绘制
        canvas.drawCircle(width / 2, height / 2, 200, paint);
    }
}
