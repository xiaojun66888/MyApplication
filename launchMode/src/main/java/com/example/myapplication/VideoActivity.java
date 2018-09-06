package com.example.myapplication;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class VideoActivity extends Activity {

    @InjectView(R.id.wv_device_history)
    WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        ButterKnife.inject(this);
        initWebView();
    }

    private void initWebView() {
        WebSettings settings = mWebView.getSettings();
        settings.setDefaultTextEncodingName("UTF-8");
        // 设置支持JS
        settings.setJavaScriptEnabled(true);
        // 设置缓存
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        settings.setAppCacheEnabled(true);
        mWebView.requestFocus();
        // 设置适应屏幕
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        } else {
            settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        }

        String head ="<head><style>video{width: 100%;  height: 480;}object{width: 100%;  height: 480;}</style></head>";
       String news_content ="<video class=\"edui-upload-video  vjs-default-skin video-js\" controls=\"\" autobuffer=\"\" preload=\"none\" poster=\"\" width=\"420\" height=\"280\" src=\"http://data.vod.itc.cn/?new=/92/78/KK9114TOJF7TTnDfy47k0A.mp4&vid=103494822&plat=17&mkey=y22k7FCa3rA0U_UcQPDy_nwgBLcldWk2&ch=null&user=api&uid=1608272337357415&SOHUSVP=Gh8YyutaCGBl_f4sdHzCtXTO_Tk9uhIOOsbcfn3xWzg&pt=1&prod=56&pg=1&eye=0&cv=1.0.0&qd=68000&src=11050001&ca=4&cateCode=133&_c=1&appid=tv\" data-setup=\"{}\"></video>";

//       String news_content = "<object data=\"http://vodcdn.video.taobao.com/oss/taobao-ugc/d5d0602f0a3e448aa4d41b7fd4d91216/1492340273/video.mp4\">";
       String html = "<html>"+head+"<body>" + news_content + "</body></html>";
        mWebView.loadDataWithBaseURL(null, html, "text/html", "UTF-8", null);
    }
}
