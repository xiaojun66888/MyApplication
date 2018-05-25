package com.example.test.myapplication.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.test.myapplication.R;
import com.example.test.myapplication.adapter.Man2Adapter;
import com.example.test.myapplication.base.AppBaseActivity;
import com.example.test.myapplication.bean.MessageEvent;

import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

public class Main2Activity extends AppBaseActivity {

    @ViewInject(value = R.id.rv_main_recycler)
    private RecyclerView recyclerView;
    private ArrayList<String> urls = new ArrayList<>();

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main2;
    }

    @Override
    protected void init() {
        urls.add("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=184848620,87883237&fm=27&gp=0.jpg");
        urls.add("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=2942798347,501972415&fm=27&gp=0.jpg");
        urls.add("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2002425038,3577470782&fm=27&gp=0.jpg");
        urls.add("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1142703689,2647413975&fm=27&gp=0.jpg");
        urls.add("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=980978685,1276613761&fm=111&gp=0.jpg");
        urls.add("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2896788661,710749588&fm=27&gp=0.jpg");
        urls.add("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=4159732642,626724082&fm=111&gp=0.jpg");
        urls.add("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=184848620,87883237&fm=27&gp=0.jpg");
        urls.add("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=2942798347,501972415&fm=27&gp=0.jpg");
        urls.add("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2002425038,3577470782&fm=27&gp=0.jpg");
        urls.add("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1142703689,2647413975&fm=27&gp=0.jpg");
        urls.add("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=980978685,1276613761&fm=111&gp=0.jpg");
        urls.add("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2896788661,710749588&fm=27&gp=0.jpg");
        urls.add("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=4159732642,626724082&fm=111&gp=0.jpg");
        //线性布局
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        //瀑布流
        StaggeredGridLayoutManager manager1 = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        //网格布局
        GridLayoutManager manager2 =  new  GridLayoutManager(this,3,LinearLayoutManager.HORIZONTAL,false);

        recyclerView.setLayoutManager(manager2);
        Man2Adapter man2Adapter = new Man2Adapter(this, urls);
        recyclerView.setAdapter(man2Adapter);
        man2Adapter.setOnItemClickListener(new Man2Adapter.OnItemClickListener() {
            @Override
            public void showPicture(View view, int positon) {
                String url = urls.get(positon);
                openFile(url);
            }
        });
    }

    @Override
    protected void widgetListener() {

    }

    /**
     * @declaration: 打开文件
     * @author xiaojun
     * @date 创建时间：2017年1月9日 下午2:59:19
     */
    protected void openFile(String url) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.parse(url), "image/*");
        startActivity(intent);
    }

}
