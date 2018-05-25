package com.example.test.myapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.test.myapplication.R;
import com.example.test.myapplication.util.ImageLoaderHelper;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/9/22.
 */
public class Man2Adapter extends RecyclerView.Adapter<Man2Adapter.ViewHolder> implements View.OnClickListener {

    private ArrayList<String> urls;
    private Context mContext;
    private OnItemClickListener listener;
    private DisplayImageOptions options;

    public Man2Adapter(Context mContext, ArrayList<String> urls) {
        this.mContext = mContext;
        this.urls = urls;
        options = ImageLoaderHelper.getInstance().getDisplayImageOptions();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_main2_layout, parent, false);
        ViewHolder vh = new ViewHolder(view);
        view.setOnClickListener(this);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String url = urls.get(position);
        ImageLoader.getInstance().displayImage(url, holder.ivImage, options);
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return urls == null ? 0 : urls.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivImage;
        private LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            ivImage = (ImageView) itemView.findViewById(R.id.item_main2_img);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.ll_item_out);
        }
    }

    @Override
    public void onClick(View v) {
        if (listener != null) {
            listener.showPicture(v, (int) v.getTag());
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void showPicture(View view, int positon);
    }
}
