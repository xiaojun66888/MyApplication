package com.example.test.myapplication.util;

import android.view.Display;

import com.example.test.myapplication.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

/**
 * Created by Administrator on 2017/9/22.
 */
public class ImageLoaderHelper  {
    private static ImageLoaderHelper helper;
    private ImageLoaderHelper(){

    }
    public static ImageLoaderHelper getInstance() {
        if (helper == null) {
            helper = new ImageLoaderHelper();
        }
        return helper;
    }

    public DisplayImageOptions getDisplayImageOptions(){
        DisplayImageOptions.Builder builder= new DisplayImageOptions.Builder();
        // 启用内存缓存
        builder.cacheInMemory(true);
        builder.cacheOnDisk(true);
        builder.showImageOnLoading(R.mipmap.progress_loading2);
        builder.displayer(new FadeInBitmapDisplayer(300));
        builder.showImageOnFail(R.mipmap.icon_no_msg);
        return builder.build();
    }
}
