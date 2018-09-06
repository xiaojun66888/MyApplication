package com.example.myapplication;

import android.app.Application;


/**
 * Created by Administrator on 2017/9/19.
 */
public class MyApplication extends Application {

    private static MyApplication app = null;

    @Override
    public void onCreate() {
        super.onCreate();
    }
    public static synchronized MyApplication getIntance() {
        if (null == app) {
            app = new MyApplication();
        }
        return app;
    }

}
