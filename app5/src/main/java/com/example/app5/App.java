package com.example.app5;

import android.app.Application;

import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.common.QueuedWork;

import org.xutils.x;

/**
 * Created by Administrator on 2017/11/13.
 */

public class App extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        Config.DEBUG = true;
        QueuedWork.isUseThreadPool = false;
        UMShareAPI.get(this);

    }
    //各个平台的配置，建议放在全局Application或者程序入口
    {
        PlatformConfig.setWeixin("wxdc1e388c3822c80b", "3baf1193c85774b3fd9d18447d76cab0");
        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad","http://sns.whalecloud.com");
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
    }
}
