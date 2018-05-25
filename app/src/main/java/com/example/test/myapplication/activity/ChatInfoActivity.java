package com.example.test.myapplication.activity;

import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.ab.view.pullview.AbPullToRefreshView;
import com.example.test.myapplication.R;
import com.example.test.myapplication.adapter.ChatAdapter;
import com.example.test.myapplication.base.AppBaseActivity;
import com.example.test.myapplication.bean.PersonChat;

import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

import static com.example.test.myapplication.R.id.et_chat_message;

public class ChatInfoActivity extends AppBaseActivity implements View.OnClickListener, View.OnTouchListener, AbPullToRefreshView.OnFooterLoadListener, AbPullToRefreshView.OnHeaderRefreshListener {

    @ViewInject(value = R.id.ll_chat_main)
    private AbPullToRefreshView mAbPullToRefreshView;
    @ViewInject(value = R.id.lv_chat_dialog)
    private ListView lvChatDialog;
    @ViewInject(value = et_chat_message)
    private EditText etChatMsg;
    @ViewInject(value = R.id.btn_chat_message_send)
    private Button btnChatMsg;


    private List<PersonChat> personChats = new ArrayList<>();
    private ChatAdapter chatAdapter;

    @Override
    protected int getContentViewId() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        return R.layout.activity_chat_info;
    }

    @Override
    protected void init() {

        /** 虚拟1条发送方的消息*/
        PersonChat personChat = new PersonChat();
        personChat.setMeSend(false);
        personChat.setChatMessage("欢迎您来陪小爱聊天");
        personChats.add(personChat);

        chatAdapter = new ChatAdapter(this, personChats);
        lvChatDialog.setAdapter(chatAdapter);
    }

    @Override
    protected void widgetListener() {
        btnChatMsg.setOnClickListener(this);
        lvChatDialog.setOnTouchListener(this);
        mAbPullToRefreshView.setOnFooterLoadListener(this);
        mAbPullToRefreshView.setOnHeaderRefreshListener(this);
    }

    @Override
    public void onClick(View v) {
        String chatMessage = etChatMsg.getText().toString();
        if (TextUtils.isEmpty(chatMessage)) {
            Toast.makeText(mContext, "发送内容不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        PersonChat personChat = new PersonChat();
        //代表自己发送
        personChat.setMeSend(true);
        //得到发送内容
        personChat.setChatMessage(chatMessage);
        //加入集合
        personChats.add(personChat);
        //清空输入框
        etChatMsg.setText("");
        //刷新ListView
        autoAnswer(chatMessage);
        chatAdapter.notifyDataSetChanged();
        lvChatDialog.setSelection(personChats.size());
    }

    private void autoAnswer(String message) {
       if(message.contains("你是谁")){
           setPersonChat("我是小爱啊");
       }else if(message.contains("喜欢")){
           setPersonChat("这个要问你自己啊");
       }else if(message.contains("吃饭")){
           setPersonChat("我还没吃饭呢");
       }
    }

    private void setPersonChat(String message){
        PersonChat personChat = new PersonChat();
        personChat.setMeSend(false);
        //得到发送内容
        personChat.setChatMessage(message);
        personChats.add(personChat);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        return false;
    }

    @Override
    public void onFooterLoad(AbPullToRefreshView abPullToRefreshView) {
        mAbPullToRefreshView.onFooterLoadFinish();
    }

    @Override
    public void onHeaderRefresh(AbPullToRefreshView abPullToRefreshView) {
        mAbPullToRefreshView.onHeaderRefreshFinish();
    }
}
