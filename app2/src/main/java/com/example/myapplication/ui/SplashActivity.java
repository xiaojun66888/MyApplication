package com.example.myapplication.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.TextureView;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2017/3/15.
 */

public class SplashActivity extends AppCompatActivity {
     @ViewInject(value = R.id.text1)
     private TextView txt1;
/*    private Handler handler =  new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    Intent intent = new Intent(SplashActivity.this,MainActivity.class);
                    startActivity(intent);
                    break;
            }
            super.handleMessage(msg);
        }
    };*/
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        x.Ext.init(getApplication());
        x.view().inject(this);
/*        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
              Message msg = handler.obtainMessage();
                msg.what = 1;
                handler.sendMessage(msg);
            }
        },2000);*/

        initView();
    }

    private void initView() {
        AnimationSet  animationset = new AnimationSet(true);
        AlphaAnimation  alpha = new AlphaAnimation(0.3f,1.0f);
        animationset.addAnimation(alpha);
        animationset.setDuration(3000);
        txt1.startAnimation(animationset);
        animationset.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Intent intent = new Intent(SplashActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
                showToast("欢迎，主人");

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
    private  void  showToast(String msg){
        Toast toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER,0,0);
        LinearLayout layout = (LinearLayout) toast.getView();
        ImageView image = new ImageView(this);
        image.setImageResource(R.mipmap.icon_success);
        layout.addView(image, 0);
        toast.show();
    }

}
