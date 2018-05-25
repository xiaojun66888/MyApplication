package com.example.app4.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.app4.R;

public class MainActivity extends AppCompatActivity {
    private ListView listview;
    //item上的数据源
    private String[] name = {"矩形", "圆形", "三角形", "扇形", "椭圆", "曲线","文字和图片"};
    private ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listview = (ListView) findViewById(R.id.listview);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, name);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (id == 0) {
                    //矩形
                    Intent intent0 = new Intent(MainActivity.this, RectActivity.class);
                    intent0.putExtra("type",0);
                    startActivity(intent0);
                } else if (id == 1) {
                    //圆形
                    Intent intent0 = new Intent(MainActivity.this, RectActivity.class);
                    intent0.putExtra("type",1);
                    startActivity(intent0);
                } else if (id == 2) {
                    //三角形
                    Intent intent0 = new Intent(MainActivity.this, RectActivity.class);
                    intent0.putExtra("type",2);
                    startActivity(intent0);
//                    startActivity(new Intent(MainActivity.this, TrigonActivity.class));
                } else if (id == 3) {
                    //扇形
                    Intent intent0 = new Intent(MainActivity.this, RectActivity.class);
                    intent0.putExtra("type",3);
                    startActivity(intent0);
//                    startActivity(new Intent(MainActivity.this, SectorActivity.class));
                } else if (id == 4) {
                    //椭圆
                    Intent intent0 = new Intent(MainActivity.this, RectActivity.class);
                    intent0.putExtra("type",4);
                    startActivity(intent0);
//                    startActivity(new Intent(MainActivity.this, OvalActivity.class));
                } else if (id == 5) {
                    //曲线
                    Intent intent0 = new Intent(MainActivity.this, RectActivity.class);
                    intent0.putExtra("type",5);
                    startActivity(intent0);
//                    startActivity(new Intent(MainActivity.this, PathActivity.class));
                }else if (id == 6) {
                    //带文字图片
                    Intent intent0 = new Intent(MainActivity.this, RectActivity.class);
                    intent0.putExtra("type",6);
                    startActivity(intent0);
//                    startActivity(new Intent(MainActivity.this, TvIvActivity.class));
                }
            }
        });
    }
}
