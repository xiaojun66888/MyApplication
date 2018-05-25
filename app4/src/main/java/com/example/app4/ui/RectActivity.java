package com.example.app4.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.app4.R;
import com.example.app4.view.CircleView;
import com.example.app4.view.RectView;

/**
 * 矩形
 */
public class RectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rect);
        RectView rectView = (RectView) findViewById(R.id.rectView);
        CircleView circleView = (CircleView) findViewById(R.id.circleView);
        int type = getIntent().getIntExtra("type",0);

    }

}
