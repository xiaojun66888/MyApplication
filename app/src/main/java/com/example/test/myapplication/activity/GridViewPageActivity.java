package com.example.test.myapplication.activity;

import com.example.test.myapplication.R;
import com.example.test.myapplication.adapter.MyAdapter;
import com.example.test.myapplication.base.AppBaseActivity;
import com.example.test.myapplication.view.MyPageIndicator;
import com.example.test.myapplication.view.PageGridView;

import java.util.ArrayList;
import java.util.List;

public class GridViewPageActivity extends AppBaseActivity {

    int width;
    int screenWidth;
    List<String> data = new ArrayList<>();
    PageGridView pageGridView, pageGridView2, pageGridView3;
    MyPageIndicator pageIndicator;
    @Override
    protected int getContentViewId() {
        return R.layout.activity_grid_view_page;
    }

    @Override
    protected void init() {
        for (int i = 1; i <= 17; i++) {
            data.add(i + "");
        }
        pageIndicator= (MyPageIndicator) findViewById(R.id.pageindicator);
        pageGridView2 = (PageGridView) findViewById(R.id.pagingGridView2);
        width = getResources().getDisplayMetrics().widthPixels / 4;
        screenWidth = getResources().getDisplayMetrics().widthPixels;
        MyAdapter adapter2 = new MyAdapter(GridViewPageActivity.this,data,width);
        pageGridView2.setOnItemClickListener(adapter2);
        pageGridView2.setAdapter(adapter2);
        pageGridView2.setPageIndicator(pageIndicator);
    }

    @Override
    protected void widgetListener() {

    }
}
