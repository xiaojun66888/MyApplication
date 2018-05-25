package com.example.test.myapplication.config;

import android.app.Application;

import com.example.test.myapplication.R;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import org.xutils.x;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by Administrator on 2017/9/19.
 */
public class MyApplication extends Application {

    private static MyApplication app = null;

    public static synchronized MyApplication getIntance() {
        if (null == app) {
            app = new MyApplication();
        }
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        initJpush();
        initImageLoader(); //初始化图片
    }

    /**
     * 图片配置缓存
     **/
    private void initImageLoader() {
        DisplayImageOptions.Builder options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.progress_loading2)// 加载中显示的图片
                .considerExifParams(true)// 是否考虑EXIF信息，比如拍照方向
                .displayer(new FadeInBitmapDisplayer(300));// 淡入动画
        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(getApplicationContext());
        // 取消缓存多张尺寸不同的同一张图片
        config.denyCacheImageMultipleSizesInMemory();
        config.threadPoolSize(3);//线程池内加载的数量
        // 设置显示选项
        config.defaultDisplayImageOptions(options.build());
        // 生成缓存文件的生成器，保证唯一的文件名，可以不设置，默认使用hash算法，也是可以保证不重名的
        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
        // 磁盘缓存大小
        config.diskCacheSize(100 * 1024 * 1024); // 100 MB
        // 内存缓存大小
        config.memoryCacheSize((int) (Runtime.getRuntime().freeMemory() / 4));
        config.discCacheFileCount(100);
        // 任务处理顺序，默认是FIFO 先进先出， LIFO 后进先出
        config.tasksProcessingOrder(QueueProcessingType.LIFO);
        // 打印调试日志
        config.writeDebugLogs(); // Remove for release app
        ImageLoader.getInstance().init(config.build());
    }

    /**
     * 初始化极光推送
     */
    private void initJpush() {
        JPushInterface.setDebugMode(true);    // 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);            // 初始化 JPush
    }

}
