package com.example.test.myapplication.activity;

import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Vibrator;
import android.support.v7.app.AlertDialog;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.test.myapplication.R;
import com.example.test.myapplication.base.AppBaseActivity;
import com.example.test.myapplication.util.ShakeListener;

import org.xutils.view.annotation.ViewInject;

import cn.jpush.android.api.JPushInterface;

public class MainActivity extends AppBaseActivity {
    @ViewInject(R.id.shake_img)
    private ImageView ivShake;
    @ViewInject(R.id.shake_connect_status)
    private TextView tvConnect;
    /*振动器*/
    private Vibrator vibrator;
    /* 音频池*/
    private SoundPool soundPool;
    private ShakeListener mShakeListener;
    private int hitOkSfx;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {

        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        // 这里指定声音池的最大音频流数目为10，
        soundPool = new SoundPool(10, AudioManager.STREAM_SYSTEM, 5);
        // 载入音频流
        hitOkSfx = soundPool.load(this, R.raw.shake, 0);
        mShakeListener = new ShakeListener(this);

    }

    @Override
    protected void widgetListener() {
        mShakeListener.setOnShakeListener(new ShakeListener.OnShakeListener() {
            @Override
            public void onShake() {
                setShark();
            }
        });
    }


    private void setShark() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.shark_anim);
        ivShake.startAnimation(animation);
        mShakeListener.stop();
        // 第一个｛｝里面是节奏数组， 第二个参数是重复次数，-1为不重复，非-1从pattern的指定下标开始重复
        vibrator.vibrate(new long[]{500, 200, 500, 200}, -1);
        soundPool.play(hitOkSfx, 1, 1, 0, 0, 1);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                vibrator.cancel();
                mShakeListener.start();
                ivShake.clearAnimation();
                checkBluetooth();
            }
        }, 2000);
    }


    private void checkBluetooth() {
        //是否连上蓝牙设备
        BluetoothManager bluetoothManager = (BluetoothManager) mContext.getSystemService(Context.BLUETOOTH_SERVICE);
        if (null != bluetoothManager) {
            final BluetoothAdapter mBluetoothAdapter = bluetoothManager.getAdapter();
            if (null != mBluetoothAdapter) {
                if (!mBluetoothAdapter.isEnabled()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                    builder.setTitle("提示");
                    builder.setMessage("是否允许申请打开蓝牙？");
                    builder.setCancelable(false);
                    builder.setPositiveButton("允许", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            final ProgressDialog progressDialog = new ProgressDialog(mContext);
                            progressDialog.setMessage("正在打开蓝牙...");
                            progressDialog.show();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    progressDialog.dismiss();
                                    Intent intent = new Intent(mContext, Main2Activity.class);
                                    startActivity(intent);
                                }
                            }, 3000);
                            mBluetoothAdapter.enable();

                        }
                    });
                    builder.setNegativeButton("拒绝", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            return;
                        }
                    });
                    builder.create().show();
                }else{
                    Intent intent = new Intent(mContext,Main2Activity.class);
                    startActivity(intent);
                }

            }

        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        JPushInterface.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        JPushInterface.onPause(this);
    }
}
