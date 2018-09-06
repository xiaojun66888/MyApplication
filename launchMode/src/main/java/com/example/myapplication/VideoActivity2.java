package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class VideoActivity2 extends Activity {

    @InjectView(R.id.wv_device_history)
    WebView mWebView;
    //    private String mStrHtml = "<h1 style=\"margin: 5px auto 10px; padding: 0px; font-stretch: normal; font-size: 38px; line-height: 57px; font-family: 微软雅黑; clear: both; color: rgb(34, 34, 34); white-space: normal;\"><span style=\"font-size: 24px;\">苏州四县市三区创建首批省级食品安全城市</span></h1><p>2017-04-05 15:26:05</p><hr/><p style=\"margin-top: 25px; margin-bottom: 25px; padding: 0px; font-stretch: normal; font-size: 18px; line-height: 32.4px; font-family: 微软雅黑; color: rgb(34, 34, 34); white-space: normal;\">昨天上午，我市召开苏州市食安委全体（扩大）会议暨创建省级食品安全城市推进工作会议，总结上半年全市食品安全工作，研究部署下半年食品安全工作任务及省级食品安全城市创建工作。市委副书记、副市长周伟强出席会议。</p><p style=\"margin-top: 25px; margin-bottom: 25px; padding: 0px; font-stretch: normal; font-size: 18px; line-height: 32.4px; font-family: 微软雅黑; color: rgb(34, 34, 34); white-space: normal;\">　　市食安办通报了上半年我市食品安全工作情况。今年以来，我市深入贯彻习近平总书记提出的食品安全“四个最严”的要求，市食安办积极发挥牵头和综合协调作用，统一部署全市食品安全工作，各地各部门加强食品安全监管，深入开展食品安全“放心行动”专项整治，坚决严惩违法行为。今年的“放心行动”重点开展食用农产品、桶装饮用水、食用油、黄酒、学校食堂及学校周边餐饮、旅游景区餐饮、“两超一非”、网络订餐、农村食品市场等9项专项整治行动。　　上半年全市累计出动监管人员90370人次，检查各类食品生产经营单位95813家次，查处食品案件798起，罚没款831.8万元，销毁不合格食品180吨。加强行政执法与刑事司法衔接，强化刑事责任追究，全市移送公安案件41件，采取强制措施58人。针对新兴的网络平台订餐情况，市食药监部门积极主动介入，开展网络订餐状况调研，约谈饿了么、百度外卖、美团外卖、零号线等5家主要第三方平台，签订《互联网订餐食品安全承诺书》，要求其把好餐饮单位上线准入关，严格履行食品安全主体责任。</p><p style=\"margin-top: 25px; margin-bottom: 25px; padding: 0px; font-stretch: normal; font-size: 18px; line-height: 32.4px; font-family: 微软雅黑; color: rgb(34, 34, 34); white-space: normal;\">　　周伟强指出，各地政府和食品安全监管部门要准确把握食品安全工作的新要求，用新的发展理念指导食品安全工作，以社会共治的思路推进食品安全工作，继续推动落实“四有两责”，着力推进长效机制建设，严厉打击食品安全违法行为，强化监管和检测能力建设，进一步完善督查考评机制，切实保障人民群众“舌尖上的安全”。</p><p style=\"margin-top: 25px; margin-bottom: 25px; padding: 0px; font-stretch: normal; font-size: 18px; line-height: 32.4px; font-family: 微软雅黑; color: rgb(34, 34, 34); white-space: normal;\"><img src=\"http://mdm.ahd6g.cn/uploads/image/20170405/1491377317568195.jpg\" title=\"1491377317568195.jpg\" alt=\"timg (5).jpg\" width=\"100%\"/></p><p style=\"margin-top: 25px; margin-bottom: 25px; padding: 0px; font-stretch: normal; font-size: 18px; line-height: 32.4px; font-family: 微软雅黑; color: rgb(34, 34, 34); white-space: normal;\">　　昆山、张家港、常熟、太仓四市及吴江区、相城区、苏州高新区三区正在参与江苏省首批省级食品安全城市创建，动员部署、组织创建阶段现已基本完成，市食安委将加强跟踪督导，组织相关人员按照创建标准开展督查，对发现的问题及时督促整改，迎接下一阶段的考核评价。周伟强要求，各地要用好“食品安全城市创建”这一有力抓手，充分发挥政府主导作用，调动各部门力量和资源，激发人民群众和社会各界参与创建的积极性、主动性，形成食品安全社会共治局面。</p><p style=\"margin-top: 25px; margin-bottom: 25px; padding: 0px; font-stretch: normal; font-size: 18px; line-height: 32.4px; font-family: 微软雅黑; color: rgb(34, 34, 34); white-space: normal;\">　　副市长盛蕾出席会议。</p><p style=\"text-align: center;\"><video class=\"edui-upload-video vjs-default-skin video-js\" controls=\"\" preload=\"none\" poster=\"http://mdm.ahd6g.cn/uploads/images/20170407/14915313834964.jpg\" width=\"100%\" src=\"http://mdm.ahd6g.cn/uploads/videos/20170405/1491381800143098.mp4\" data-setup=\"{}\"><source src=\"http://mdm.ahd6g.cn/uploads/videos/20170405/1491381800143098.mp4\" type=\"video/mp4\"/></video></p>";
    /** 视频全屏参数 */
    protected static final FrameLayout.LayoutParams COVER_SCREEN_PARAMS = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    private View customView;
    private FrameLayout fullscreenContainer;
    private WebChromeClient.CustomViewCallback customViewCallback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video2);
        ButterKnife.inject(this);
        loadWebvieUrl();
    }

    private void loadWebvieUrl() {
        String head = "<head><style>video{width: 100%;  height: 480;}object{width: 100%;  height: 480;}</style></head>";
        String news_content = "<video class=\"edui-upload-video  vjs-default-skin video-js\" controls=\"\" autobuffer=\"\" preload=\"none\" poster=\"\" width=\"420\" height=\"280\" src=\"http://data.vod.itc.cn/?new=/92/78/KK9114TOJF7TTnDfy47k0A.mp4&vid=103494822&plat=17&mkey=y22k7FCa3rA0U_UcQPDy_nwgBLcldWk2&ch=null&user=api&uid=1608272337357415&SOHUSVP=Gh8YyutaCGBl_f4sdHzCtXTO_Tk9uhIOOsbcfn3xWzg&pt=1&prod=56&pg=1&eye=0&cv=1.0.0&qd=68000&src=11050001&ca=4&cateCode=133&_c=1&appid=tv\" data-setup=\"{}\"></video>";
        String html = "<html>" + head + "<body>" + news_content + "</body></html>";
        WebChromeClient wvcc = new WebChromeClient();
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true); // 关键点
        webSettings.setAllowFileAccess(true); // 允许访问文件
        webSettings.setSupportZoom(true); // 支持缩放
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE); // 不加载缓存内容

        mWebView.setWebChromeClient(wvcc);
        WebViewClient wvc = new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                mWebView.loadUrl(url);
                return true;
            }
        };

        mWebView.setWebViewClient(wvc);
        mWebView.setWebChromeClient(new WebChromeClient() {

            /*** 视频播放相关的方法 **/

            @Override
            public View getVideoLoadingProgressView() {
                FrameLayout frameLayout = new FrameLayout(VideoActivity2.this);
                frameLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                return frameLayout;
            }

            @Override
            public void onShowCustomView(View view, CustomViewCallback callback) {
                showCustomView(view, callback);
            }

            @Override
            public void onHideCustomView() {
                hideCustomView();
            }
        });
        mWebView.loadDataWithBaseURL(null, html, "text/html", "UTF-8", null);
//        mWebView.loadUrl("http://vodcdn.video.taobao.com/oss/taobao-ugc/d5d0602f0a3e448aa4d41b7fd4d91216/1492340273/video.mp4");// 加载url
    }

    /** 视频播放全屏 **/
    private void showCustomView(View view, WebChromeClient.CustomViewCallback callback) {
        // if a view already exists then immediately terminate the new one
        if (customView != null) {
            callback.onCustomViewHidden();
            return;
        }

       this.getWindow().getDecorView();

        FrameLayout decor = (FrameLayout) getWindow().getDecorView();
        fullscreenContainer = new FullscreenHolder(this);
        fullscreenContainer.addView(view, COVER_SCREEN_PARAMS);
        decor.addView(fullscreenContainer, COVER_SCREEN_PARAMS);
        customView = view;
        setStatusBarVisibility(false);
        customViewCallback = callback;
        //设置横屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        //设置全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
    /** 隐藏视频全屏 */
    private void hideCustomView() {
        if (customView == null) {
            return;
        }

        setStatusBarVisibility(true);
        FrameLayout decor = (FrameLayout) getWindow().getDecorView();
        decor.removeView(fullscreenContainer);
        fullscreenContainer = null;
        customView = null;
        customViewCallback.onCustomViewHidden();
        mWebView.setVisibility(View.VISIBLE);
        // 设置竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // 取消全屏
        final WindowManager.LayoutParams attrs = getWindow().getAttributes();
        attrs.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setAttributes(attrs);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }

    /** 全屏容器界面 */
    static class FullscreenHolder extends FrameLayout {

        public FullscreenHolder(Context ctx) {
            super(ctx);
        }

        @Override
        public boolean onTouchEvent(MotionEvent evt) {
            return true;
        }
    }
    private void setStatusBarVisibility(boolean visible) {
        int flag = visible ? 0 : WindowManager.LayoutParams.FLAG_FULLSCREEN;
        getWindow().setFlags(flag, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

}
