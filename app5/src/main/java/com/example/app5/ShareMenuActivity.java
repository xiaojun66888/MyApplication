package com.example.app5;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app5.model.Defaultcontent;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.shareboard.ShareBoardConfig;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.socialize.utils.Log;
import com.umeng.socialize.utils.ShareBoardlistener;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.lang.ref.WeakReference;

public class ShareMenuActivity extends Activity implements View.OnClickListener {

    @ViewInject(value = R.id.umeng_menu_bottom)
    private TextView tvMenuBottom ;
    @ViewInject(value = R.id.umeng_menu_center)
    private TextView tvMenuCenter ;
    private UMShareListener mShareListener;
    private ShareAction mShareAction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_menu);
        x.view().inject(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.umeng_blue));
        }
        initView();
        widgetListen();
    }

    private void initView() {
        mShareListener = new CustomShareListener(this);
        mShareAction = new ShareAction(this);
        mShareAction.setDisplayList(SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.WEIXIN_FAVORITE,SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE);
//        mShareAction .addButton("umeng_sharebutton_copy", "umeng_sharebutton_copy", "umeng_socialize_copy", "umeng_socialize_copy");
//        mShareAction .addButton("umeng_sharebutton_copyurl", "umeng_sharebutton_copyurl", "umeng_socialize_copyurl", "umeng_socialize_copyurl");
        mShareAction.setShareboardclickCallback(new ShareBoardlistener() {
            @Override
            public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {
                if (snsPlatform.mShowWord.equals("umeng_sharebutton_copy")) {
                    Toast.makeText(ShareMenuActivity.this, "复制文本按钮", Toast.LENGTH_LONG).show();
                } else if (snsPlatform.mShowWord.equals("umeng_sharebutton_copyurl")) {
                    Toast.makeText(ShareMenuActivity.this, "复制链接按钮", Toast.LENGTH_LONG).show();
                }else if(share_media == SHARE_MEDIA.SMS) {
                    new ShareAction(ShareMenuActivity.this).withText("来自分享面板标题").setPlatform(share_media).setCallback(mShareListener).share();
                }else{
                    UMWeb web = new UMWeb(Defaultcontent.url);
                    web.setTitle("来自分享面板标题");
                    web.setDescription("来自分享面板内容");
                    web.setThumb(new UMImage(ShareMenuActivity.this, R.mipmap.logo));
                    new ShareAction(ShareMenuActivity.this).withMedia(web).setPlatform(share_media).setCallback(mShareListener) .share();
                }
            }
        });
    }

    private void widgetListen() {
        tvMenuBottom.setOnClickListener(this);
        tvMenuCenter.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.umeng_menu_bottom:
                mShareAction.open();
                break;
            case  R.id.umeng_menu_center:
                ShareBoardConfig config = new ShareBoardConfig();
                config.setShareboardPostion(ShareBoardConfig.SHAREBOARD_POSITION_CENTER);
                config.setMenuItemBackgroundShape(ShareBoardConfig.BG_SHAPE_CIRCULAR); // 圆角背景
                mShareAction.open(config);
                break;
        }
    }

    private static class  CustomShareListener implements  UMShareListener{
        private WeakReference<ShareMenuActivity> mActivity;

        private CustomShareListener(ShareMenuActivity activity) {
            mActivity = new WeakReference(activity);
        }
        @Override
        public void onStart(SHARE_MEDIA share_media) {

        }

        @Override
        public void onResult(SHARE_MEDIA platform) {
               if(platform.name().equals("WEIXIN_FAVORITE")){
                   Toast.makeText(mActivity.get(), platform + " 收藏成功啦", Toast.LENGTH_SHORT).show();
               }else{
                   if (platform != SHARE_MEDIA.MORE && platform != SHARE_MEDIA.SMS
                           && platform != SHARE_MEDIA.EMAIL
                           && platform != SHARE_MEDIA.FLICKR
                           && platform != SHARE_MEDIA.FOURSQUARE
                           && platform != SHARE_MEDIA.TUMBLR
                           && platform != SHARE_MEDIA.POCKET
                           && platform != SHARE_MEDIA.PINTEREST
                           && platform != SHARE_MEDIA.INSTAGRAM
                           && platform != SHARE_MEDIA.GOOGLEPLUS
                           && platform != SHARE_MEDIA.YNOTE
                           && platform != SHARE_MEDIA.EVERNOTE) {
                       Toast.makeText(mActivity.get(), platform + " 分享成功啦", Toast.LENGTH_SHORT).show();
                   }
               }
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable throwable) {
            if (platform != SHARE_MEDIA.MORE && platform != SHARE_MEDIA.SMS
                    && platform != SHARE_MEDIA.EMAIL
                    && platform != SHARE_MEDIA.FLICKR
                    && platform != SHARE_MEDIA.FOURSQUARE
                    && platform != SHARE_MEDIA.TUMBLR
                    && platform != SHARE_MEDIA.POCKET
                    && platform != SHARE_MEDIA.PINTEREST
                    && platform != SHARE_MEDIA.INSTAGRAM
                    && platform != SHARE_MEDIA.GOOGLEPLUS
                    && platform != SHARE_MEDIA.YNOTE
                    && platform != SHARE_MEDIA.EVERNOTE) {
                Toast.makeText(mActivity.get(), platform + " 分享失败啦", Toast.LENGTH_SHORT).show();
                if (throwable != null) {
                    Log.d("throw", "throw:" + throwable.getMessage());
                }
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(mActivity.get(), platform + " 分享取消了", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /** attention to this below ,must add this**/
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
    /**
     * 屏幕横竖屏切换时避免出现window leak的问题
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mShareAction.close();
    }
}
