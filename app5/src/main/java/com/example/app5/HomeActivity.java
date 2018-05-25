package com.example.app5;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    @ViewInject(value = R.id.umeng_menu)
    private TextView tvUmengMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        x.view().inject(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.umeng_blue));
        }
        widgetListen();
    }


    private void widgetListen() {
        tvUmengMenu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.umeng_menu:
                Intent menuintent = new Intent(HomeActivity.this,ShareMenuActivity.class);
                startActivity(menuintent);
                break;
        }
    }
}
