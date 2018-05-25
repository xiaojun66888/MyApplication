package com.example.test.myapplication.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.test.myapplication.R;
import com.example.test.myapplication.bean.PersonChat;

import java.util.List;

public class ChatAdapter extends BaseAdapter {
    private Context context;
    private List<PersonChat> lists;

    public ChatAdapter(Context context, List<PersonChat> lists) {
        super();
        this.context = context;
        this.lists = lists;
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Object getItem(int arg0) {
        return lists.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

    @Override
    public View getView(int positon, View convertView, ViewGroup parent) {
        HolderView holderView;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.chat_dialog_item, null);
            holderView = new HolderView(convertView);
            convertView.setTag(holderView);
        } else {
            holderView = (HolderView) convertView.getTag();
        }
        PersonChat entity = lists.get(positon);
        boolean isMeSend = entity.isMeSend();
        if (isMeSend) {
            holderView.llLeft.setVisibility(View.GONE);
            holderView.llRight.setVisibility(View.VISIBLE);
            holderView.tvChatFormMe.setText(entity.getChatMessage());
        } else {
            holderView.llLeft.setVisibility(View.VISIBLE);
            holderView.llRight.setVisibility(View.GONE);
            holderView.tvChatToMe.setText(entity.getChatMessage());
        }

        return convertView;
    }

    private class HolderView {
        private TextView tvChatFormMe;
        private TextView tvChatToMe;
        private LinearLayout llLeft;
        private LinearLayout llRight;

        public HolderView(View convertView) {
            tvChatFormMe = (TextView) convertView.findViewById(R.id.tv_chat_from_me);
            tvChatToMe = (TextView) convertView.findViewById(R.id.tv_chat_to_me);
            llLeft = (LinearLayout) convertView.findViewById(R.id.ll_item_left);
            llRight = (LinearLayout) convertView.findViewById(R.id.ll_item_right);
        }
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }
}
