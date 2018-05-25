package com.example.myapplication.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.myapplication.R;

/**
 * 自定义标题
 */

public class TitleView extends RelativeLayout {
    //控件初始化
    private ImageView ibBack;
    private ImageView ibRight;
    private ImageView ibLeft;
    private TextView txtLeft;
    private TextView txtCenter;
    private TextView txtRight;
    /**文本信息*/
    private String textInfoLeft;
    private String textInfoCenter;
    private String textInfoRight;
    /**是否有返回按钮、右侧按钮*/
    private boolean hasBackBtn;
    private boolean hasRightBtn;
    private boolean hasLeftBtn;

    public TitleView(Context context) {
        this(context, null);
    }

    public TitleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.title_view_layout, this);
        ibBack = (ImageView) findViewById(R.id.title_ib_back);
        ibRight = (ImageView) findViewById(R.id.title_ib_right);
        ibLeft = (ImageView) findViewById(R.id.title_ib_left);
        txtLeft = (TextView) findViewById(R.id.title_txt_left);
        txtCenter = (TextView) findViewById(R.id.title_txt_center);
        txtRight = (TextView) findViewById(R.id.title_txt_right);
        TypedArray typeArray = context.obtainStyledAttributes(attrs,R.styleable.TitleView);
        int n = typeArray.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = typeArray.getIndex(i);
             switch (attr){
                 case R.styleable.TitleView_textInfoLeft:
                     textInfoLeft =  typeArray.getString(attr);
                     break;
                 case R.styleable.TitleView_textInfoCenter:
                     textInfoCenter =typeArray.getString(attr);
                     break;
                 case R.styleable.TitleView_textInfoRight:
                     textInfoRight = typeArray.getString(attr);
                     break;
                 case R.styleable.TitleView_backBtnIcon:
                     ibBack.setImageResource(typeArray.getResourceId(attr,-1));
                     break;
                 case R.styleable.TitleView_rightBtnIcon:
                     ibRight.setImageResource(typeArray.getResourceId(attr,-1));
                     break;
                 case R.styleable.TitleView_leftBtnIcon:
                     ibLeft.setImageResource(typeArray.getResourceId(attr,-1));
                     break;
                 case R.styleable.TitleView_hasBackBtn:
                     hasBackBtn = typeArray.getBoolean(attr,false);
                     break;
                 case R.styleable.TitleView_hasRightBtn:
                     hasRightBtn = typeArray.getBoolean(attr,false);
                     break;
                 case R.styleable.TitleView_hasLeftBtn:
                      hasLeftBtn = typeArray.getBoolean(attr,false);
                     break;
             }
        }
        typeArray.recycle();
        if (!TextUtils.isEmpty(textInfoLeft)) {
            txtLeft.setText(textInfoLeft);
        }
        if(!TextUtils.isEmpty(textInfoCenter)){
            txtCenter.setText(textInfoCenter);
        }
        if(!TextUtils.isEmpty(textInfoRight)){
            txtRight.setText(textInfoRight);
        }
        if(!hasBackBtn){
            ibBack.setVisibility(View.GONE);
        }else{
            ibBack.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((Activity) getContext()).finish();
                    ((Activity) getContext()).overridePendingTransition(R.anim.slide_right_in, R.anim.slide_right_out);
                }
            });
        }
        if (!hasRightBtn) {
            ibRight.setVisibility(View.GONE);
        }
        if (!hasLeftBtn) {
            ibRight.setVisibility(View.GONE);
        }

    }

    /***
     * s设置
     * @param textInfoLeft
     */
    public void setTextInfoLeft(String textInfoLeft) {
        txtLeft.setText(textInfoLeft);
    }

    public void setTextInfoCenter(String textInfoCenter) {
        txtCenter.setText(textInfoCenter);
    }

    public void setTextInfoRight(String textInfoRight) {
        txtRight.setText(textInfoRight);
    }
    public void setleftBtnIcon(int resId) {
        ibLeft.setImageResource(resId);
    }

    public void setRightBtnIcon(int resId) {
        ibRight.setImageResource(resId);
    }
    public void setRightTextInfoListener(OnClickListener l){
        txtRight.setOnClickListener(l);
    }
    public void setRightBtnListener(OnClickListener l){
        ibRight.setOnClickListener(l);
    }
    public void setLefttBtnListener(OnClickListener l){
        ibLeft.setOnClickListener(l);
    }
}
