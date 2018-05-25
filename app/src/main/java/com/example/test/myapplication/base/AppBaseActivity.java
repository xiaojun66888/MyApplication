package com.example.test.myapplication.base;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.widget.Toast;

import org.xutils.x;

/**
 * Created by Administrator on 2017/9/19.
 */
public abstract class AppBaseActivity extends Activity implements Handler.Callback {

    /* 公共变量*/
    protected Context mContext;
    /* handler处理*/
    protected Handler handler;
    /* 申请权限后的返回码*/
    private static final int REQUEST_CODE_CONTACT = 101;
    /* 布局管理器 */
    protected LayoutInflater mInflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        x.view().inject(this);
        mContext = this;
        handler = new Handler(this);
        mInflater = LayoutInflater.from(this);
        init();
        widgetListener();
        checkPermissions();
    }

    /**
     * 获取显示view的xml文件ID
     */
    protected abstract int getContentViewId();

    /**
     * 初始化应用程序
     */
    protected abstract void init();

    /**
     * 组件监听模块
     */
    protected abstract void widgetListener();

    @Override
    public boolean handleMessage(Message msg) {
        return false;
    }

    /**
     * 动态获取权限，Android 6.0 新特性，一些保护权限，除了要在AndroidManifest中声明权限，还要使用如下代码动态获取
     */
    private void checkPermissions() {
        if (Build.VERSION.SDK_INT >= 23) {
            String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_CONTACTS,Manifest.permission.READ_PHONE_STATE,Manifest.permission.CAMERA,
                    Manifest.permission.VIBRATE, Manifest.permission.BLUETOOTH, Manifest.permission.BLUETOOTH_ADMIN,Manifest.permission.RECORD_AUDIO};
            for (String str : permissions) {
                if (ContextCompat.checkSelfPermission(mContext, str) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, permissions, REQUEST_CODE_CONTACT);
                }
            }

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_CODE_CONTACT:
                for (int i = 0; i < permissions.length; i++) {
                    if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(this, permissions[i] + "权限被拒绝", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
    }
}
