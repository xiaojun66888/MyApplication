package com.example.myapplication;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MainActivity extends Activity {


    @InjectView(R.id.btn_skip1)
    Button btnSkip1;
    @InjectView(R.id.btn_skip2)
    Button btnSkip2;
    @InjectView(R.id.btn_skip3)
    Button btnSkip3;

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int state = SpUtils.getSPValue(this,"s",1);
        if(state==1){
            setTheme(R.style.Default_TextSize_Small);
        }else if(state==2){
            setTheme(R.style.Default_TextSize_Middle);
        }else{
            setTheme(R.style.Default_TextSize_Big);
        }

        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
    }


    @OnClick({R.id.btn_skip1, R.id.btn_skip2, R.id.btn_skip3})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_skip1:
                SpUtils.putSPValue(this,"s",1);
                break;
            case R.id.btn_skip2:
                SpUtils.putSPValue(this,"s",2);
                break;
            case R.id.btn_skip3:
                SpUtils.putSPValue(this,"s",3);
                break;
        }
    }
}
