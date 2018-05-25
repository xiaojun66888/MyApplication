package com.example.test.myapplication.activity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.text.TextUtils;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.test.myapplication.R;
import com.example.test.myapplication.base.AppBaseActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class Main4Activity extends AppBaseActivity {


    ListView lvLxr;
    private ContentResolver cr;
    private List<Map<String, Object>> datalistView;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main4;
    }

    @Override
    protected void init() {
        lvLxr = (ListView) findViewById(R.id.lv_lxr);
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progressDialog.dismiss();
                initCursor();
            }
        },3000);
    }

    private void initCursor() {
        //得到访问者
        cr = getContentResolver();
        //定义一个接收联系人姓名和电话号码的集
        datalistView = new ArrayList<>();
//        SystemClock.sleep(3000);
        Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");
        Cursor cursor = cr.query(uri, null, null, null, null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("_id"));
            Uri uriData = Uri.parse("content://com.android.contacts/raw_contacts/" + id + "/data");
            Cursor contactData = cr.query(uriData, null, null, null, null);
            //用来装姓名
            String aa = "";
            //用来装号码
            String bb = "";
            if (contactData != null) {
                while (contactData.moveToNext()) {
                    String type = contactData.getString(contactData.getColumnIndex("mimetype"));
                    //如果获取的是vnd.android.cursor.item/phone_v2则是号码
                    if (type.equals("vnd.android.cursor.item/phone_v2")) {
                        bb = contactData.getString(contactData.getColumnIndex("data1"));
                        //如果获取的是vnd.android.cursor.item/name则是姓名
                    } else if (type.equals("vnd.android.cursor.item/name")) {
                        aa = contactData.getString(contactData.getColumnIndex("data1"));
                    }
                }
                if (!TextUtils.isEmpty(bb)) {
                    //将用户名和号码放入Map集合中
                    Map<String, Object> map = new HashMap<>();
                    map.put("images", aa);
                    map.put("titles", bb);
                    datalistView.add(map);
                }

            }


        }
        SimpleAdapter adapter = new SimpleAdapter(Main4Activity.this, datalistView, R.layout.activity_xs, new String[]{"images", "titles"}, new int[]{R.id.tv_name, R.id.tv_telephone});
        lvLxr.setAdapter(adapter);
    }

    @Override
    protected void widgetListener() {

    }

}
