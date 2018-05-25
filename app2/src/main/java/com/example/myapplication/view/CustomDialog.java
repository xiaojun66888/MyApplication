package com.example.myapplication.view;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.R;

/**
 * 自定义窗口
 */

public class CustomDialog  extends AlertDialog implements View.OnClickListener {
    private String message;
    private String strOk;
    private String strCancel;
    private OnDialogClick onDialogClick;
    public CustomDialog(@NonNull Context context, String message, String strCancel, String strOk) {
        super(context,R.style.DialogTheme);
        this.message = message;
        this.strOk = strOk;
        this.strCancel = strCancel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_dialog_layout);
        TextView txtMsg = (TextView) findViewById(R.id.dialog_txt_message);
        Button btnCancel = (Button) findViewById(R.id.dialog_btn_cancel);
        Button btnOK     = (Button) findViewById(R.id.dialog_btn_ok);
        txtMsg.setText(message);
        btnCancel.setText(strCancel);
        btnOK.setText(strOk);
        btnCancel.setOnClickListener(this);
        btnOK.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
       switch (v.getId()){
           case R.id.dialog_btn_cancel:
               if(onDialogClick!=null){
                   onDialogClick.onCancel();
               }
               break;
           case R.id.dialog_btn_ok:
               if(onDialogClick!=null){
                   onDialogClick.onConfrim();
               }
               break;

       }
    }
    public void setOnDialogClick(OnDialogClick onDialogClick) {
        this.onDialogClick = onDialogClick;
    }


    public interface OnDialogClick{
        void onConfrim();
        void onCancel();
    }
}
