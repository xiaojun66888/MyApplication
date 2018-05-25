package com.example.test.myapplication.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.test.myapplication.R;
import com.example.test.myapplication.bean.MessageEvent;

import butterknife.ButterKnife;
import butterknife.InjectView;
import de.greenrobot.event.EventBus;

public class ShowPictureActivity extends AppCompatActivity {

    @InjectView(R.id.iv_show_img)
    ImageView ivShowImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_picture);
        ButterKnife.inject(this);
        EventBus.getDefault().post(new MessageEvent("您好吗？"));
    }

}
