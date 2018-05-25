package com.example.test.myapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test.myapplication.R;
import com.example.test.myapplication.activity.MainActivity;
import com.example.test.myapplication.view.PageGridView;

import java.util.List;

/**
 * @version 1.0
 * @desc: <描述>
 * @projectName: MyApplication
 * @author: xiaoj
 * @date: 2018/4/28 16:49
 */
public class MyAdapter extends PageGridView.PagingAdapter<MyAdapter.ViewHolder> implements PageGridView.OnItemClickListener {
    private List<String> data;
    private Context context;
    private  int width;
    public MyAdapter(Context context, List<String> data, int width) {
        this.data = data;
        this.context = context;
        this.width = width;
    }

    @Override
    public List getData() {
        return data;
    }

    @Override
    public Object getEmpty() {
        return "";
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item, parent, false);
        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.height = width;
        params.width = width;
        view.setLayoutParams(params);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (TextUtils.isEmpty(data.get(position))) {
            holder.icon.setVisibility(View.GONE);
        } else {
            holder.icon.setVisibility(View.VISIBLE);
        }
        holder.tv_title.setText(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public void onItemClick(PageGridView pageGridView, int position) {
        Toast.makeText(context, " 第" + (position + 1) + "个item 被点击" + " 值：" + data.get(position), Toast.LENGTH_SHORT).show();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_title;
        public ImageView icon;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            icon = (ImageView) itemView.findViewById(R.id.icon);
        }
    }
}
