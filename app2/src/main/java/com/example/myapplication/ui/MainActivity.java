package com.example.myapplication.ui;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.ab.view.pullview.AbPullToRefreshView;
import com.ab.view.slidingmenu.SlidingMenu;
import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.myapplication.R;
import com.example.myapplication.adapter.SwipAdapter;
import com.example.myapplication.bean.Info;
import com.example.myapplication.bean.NewsInfoEnty;
import com.example.myapplication.floatingAction.FloatingActionButton;
import com.example.myapplication.floatingAction.FloatingActionMenu;
import com.example.myapplication.floatingAction.SubActionButton;
import com.example.myapplication.utils.SkinSettingManager;
import com.example.myapplication.view.CustomDialog;
import com.example.myapplication.view.CycleViewPager;
import com.example.myapplication.view.TitleView;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SwipeMenuListView.OnMenuItemClickListener, AbPullToRefreshView.OnHeaderRefreshListener, AbPullToRefreshView.OnFooterLoadListener {
    @ViewInject(value = R.id.title)
    private TitleView mTitle;
    //带左右划出SwipeListView
    @ViewInject(value = R.id.mPullRefreshView)
    private AbPullToRefreshView mAbPullToRefreshView;
    @ViewInject(value = R.id.mSwipeMenuListView)
    private SwipeMenuListView mSwipeMenuListView;
    private String[] items = {"打开", "删除"};
    private ArrayList<NewsInfoEnty> list = new ArrayList<NewsInfoEnty>();
    private SwipAdapter swipAdapter;
    //轮播
    private CycleViewPager mCycleViewPager;
    private List<Info> mList = new ArrayList<Info>();
    private View headBannerView;
    //c侧滑
    private SlidingMenu menu;
    //浮动按钮
    private FloatingActionButton floatingActionButton;
    private FloatingActionMenu floatingActionMenu;

    private int[] themes = { R.style.AppTheme, R.style.NightAppTheme };
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
/*            //取消设置透明状态栏,使 ContentView 内容不再覆盖状态栏
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            //设置状态栏颜色
            getWindow().setStatusBarColor(getResources().getColor(R.color.black));*/
        setContentView(R.layout.activity_main);
        x.view().inject(this);
        addData();
        initView();
        initFlashView();  //轮播图
        initSlidingMenu();
        initFloatAction();

    }

    /**
     * 初始化VIew
     */
    private void initView() {
        swipAdapter = new SwipAdapter(this, list);
        mSwipeMenuListView.setAdapter(swipAdapter);
        SwipeMenuCreator swipeMenuCreator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu swipeMenu) {
                for (int i = 0; i < items.length; i++) {
                    //创建一个"打开"功能菜单
                    SwipeMenuItem swipeMenuItem = new SwipeMenuItem(MainActivity.this);
                    // 宽度：菜单的宽度是一定要有的，否则不会显示
                    swipeMenuItem.setWidth(dp2px(80));
                    //设置标题
                    swipeMenuItem.setTitle(items[i]);
                    // 标题文字大小
                    swipeMenuItem.setTitleSize(16);
                    // 标题的颜色
                    swipeMenuItem.setTitleColor(Color.WHITE);
                    if (i == 0) {
                        // 设置菜单的背景
                        swipeMenuItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9, 0xCE)));
                    } else if (i == 1) {
                        swipeMenuItem.setBackground(new ColorDrawable(Color.rgb(0xF9, 0x3F, 0x25)));
                    }
                    swipeMenu.addMenuItem(swipeMenuItem);
                }
            }
        };
        mSwipeMenuListView.setMenuCreator(swipeMenuCreator);
        mSwipeMenuListView.setOnMenuItemClickListener(this);
        mAbPullToRefreshView.setOnHeaderRefreshListener(this);
        mAbPullToRefreshView.setOnFooterLoadListener(this);
    }
    /**
     * banner
     */
    private void initFlashView() {
        headBannerView = LayoutInflater.from(MainActivity.this).inflate(R.layout.head_banner_view, null);
        mSwipeMenuListView.addHeaderView(headBannerView);
        mCycleViewPager = (CycleViewPager) headBannerView.findViewById(R.id.cycle_view);
        assert mCycleViewPager != null;
        mCycleViewPager.setIndicators(R.mipmap.icon_cricle_check, R.mipmap.icon_cricle_uncheck);
        //设置轮播间隔时间，默认为4000
        mCycleViewPager.setDelay(5000);
        initBannerData();
        mCycleViewPager.setData(mList, new CycleViewPager.ImageCycleViewListener() {
            @Override
            public void onImageClick(Info info, int position, View imageView) {
                if (mCycleViewPager.isCycle()) {
                    position = position - 1;
                }
                Toast.makeText(MainActivity.this, info.getTitle() + "选择了" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 侧滑菜单
     */

    private void initSlidingMenu() {
        menu = new SlidingMenu(MainActivity.this);
        menu.setLeft(SlidingMenu.LEFT);
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
        menu.setShadowWidthRes(R.dimen.shadow_width); // 1）
        menu.setShadowDrawable(R.drawable.shadow); // 2）
        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset); // 3）
        menu.setBehindWidth(800);
        menu.setFadeEnabled(true);//是否有渐变
        menu.setFadeDegree(0.35f);// SlidingMenu滑动时的渐变程度
        menu.attachToActivity(MainActivity.this, SlidingMenu.SLIDING_CONTENT);
        menu.setMenu(R.layout.slidingmenu_layout);
        mTitle.setLefttBtnListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu.showMenu();
            }
        });

    }





    /**
     * 浮动按钮
     */
    private void initFloatAction() {
        final ImageView fabIcon = new ImageView(this);
        fabIcon.setImageResource(R.mipmap.ic_action_new_light);
        floatingActionButton = new FloatingActionButton.Builder(this).setContentView(fabIcon).build();
        ImageView rlIcon1 = new ImageView(this);
        ImageView rlIcon2 = new ImageView(this);
        ImageView rlIcon3 = new ImageView(this);
        ImageView rlIcon4 = new ImageView(this);
        rlIcon1.setImageDrawable(getResources().getDrawable(R.mipmap.ic_action_chat_light));
        rlIcon2.setImageDrawable(getResources().getDrawable(R.mipmap.ic_action_camera_light));
        rlIcon3.setImageDrawable(getResources().getDrawable(R.mipmap.ic_action_video_light));
        rlIcon4.setImageDrawable(getResources().getDrawable(R.mipmap.ic_action_place_light));
        // 小部分按钮
        SubActionButton.Builder rLSubBuilder = new SubActionButton.Builder(this);
        floatingActionMenu = new FloatingActionMenu.Builder(this)
                .addSubActionView(rLSubBuilder.setContentView(rlIcon1).build())
                .addSubActionView(rLSubBuilder.setContentView(rlIcon2).build())
                .addSubActionView(rLSubBuilder.setContentView(rlIcon3).build())
                .addSubActionView(rLSubBuilder.setContentView(rlIcon4).build())
                .attachTo(floatingActionButton).build();

        floatingActionMenu.setStateChangeListener(new FloatingActionMenu.MenuStateChangeListener() {
            @Override
            public void onMenuOpened(FloatingActionMenu menu) {
                fabIcon.setRotation(0);
                PropertyValuesHolder pvhR = PropertyValuesHolder.ofFloat(View.ROTATION, 45);
                ObjectAnimator animation = ObjectAnimator.ofPropertyValuesHolder(fabIcon, pvhR);
                animation.start();
            }

            @Override
            public void onMenuClosed(FloatingActionMenu menu) {
                fabIcon.setRotation(45);
                PropertyValuesHolder pvhR = PropertyValuesHolder.ofFloat(View.ROTATION, 0);
                ObjectAnimator animation = ObjectAnimator.ofPropertyValuesHolder(fabIcon, pvhR);
                animation.start();
            }
        });
        if (menu.isShown()) {
            floatingActionMenu.close(true);
            floatingActionButton.detach();
        }
        rlIcon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void onMenuItemClick(final int position, SwipeMenu swipeMenu, int index) {
        switch (index) {
            case 0:
                Toast.makeText(MainActivity.this, "打开第" + position + "个条目", Toast.LENGTH_SHORT).show();
                break;

            case 1:
                final CustomDialog customDialog = new CustomDialog(MainActivity.this, "你确定要删除吗？", "取消", "确定");

                customDialog.setCanceledOnTouchOutside(true);
                customDialog.setOnDialogClick(new CustomDialog.OnDialogClick() {
                    @Override
                    public void onConfrim() {
                        list.remove(position);
                        swipAdapter.notifyDataSetChanged();
                        customDialog.dismiss();
                    }

                    @Override
                    public void onCancel() {
                        customDialog.dismiss();
                    }
                });
                customDialog.show();

                break;

        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (mSwipeMenuListView.getItemsCanFocus()) {
            mSwipeMenuListView.setItemsCanFocus(true);
            menu.setSlidingEnabled(false);
        }
        return super.dispatchTouchEvent(ev);
    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }

    private long exitTime = 0;

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
            if (System.currentTimeMillis() - exitTime > 2000) {
                Toast.makeText(this, "再按一次就退出哦", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
                return true;
            } else {
                finish();
                System.exit(0);
            }
        }
        return super.onKeyUp(keyCode, event);
    }


    /********************************************** 测试数据*************************************/
    private void addData() {
        for (int i = 0; i < 10; i++) {
            NewsInfoEnty newsInfoEnty = new NewsInfoEnty();
            newsInfoEnty.setTitle("王宝强" + i);
            list.add(newsInfoEnty);
        }
    }

    private void initBannerData() {
        mList.add(new Info("赵丽颖", "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3763978580,3996266286&fm=111&gp=0.jpg"));
        mList.add(new Info("唐嫣", "https://ss0.baidu.com/73t1bjeh1BF3odCf/it/u=3534981231,3321509791&fm=73"));
        mList.add(new Info("罗晋", "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=4170384155,776291189&fm=23&gp=0.jpg"));
        mList.add(new Info("胡歌", "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1513607383,2631136788&fm=23&gp=0.jpg"));
    }

    @Override
    public void onHeaderRefresh(AbPullToRefreshView abPullToRefreshView) {
        abPullToRefreshView.onHeaderRefreshFinish();
    }

    @Override
    public void onFooterLoad(AbPullToRefreshView abPullToRefreshView) {
        abPullToRefreshView.onFooterLoadFinish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (menu.isShown()) {
            floatingActionMenu.close(true);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (menu.isShown()) {
            floatingActionMenu.close(true);
        }
    }
}
