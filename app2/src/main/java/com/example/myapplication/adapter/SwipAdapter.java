package com.example.myapplication.adapter;

import java.util.List;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import android.content.Context;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.bean.NewsInfoEnty;

import static android.R.id.list;

/**
 * @author xiaojun
 * 新闻适配器
 */
public class SwipAdapter extends AppBaseAdapter<NewsInfoEnty>{
	public SwipAdapter(Context context, List<NewsInfoEnty> list) {
		super(context, list);
	}
	@Override
	public View getItemView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if(convertView==null){
			convertView= inflater.inflate(R.layout.item_news_layout, parent,false);
			viewHolder = new ViewHolder(convertView);
			convertView.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder) convertView.getTag();
		}
		NewsInfoEnty newsInfoEnty = list.get(position);
		viewHolder.newsTitle.setText(newsInfoEnty.getTitle());
		viewHolder.newsDate.setText("时间：122555555");
		viewHolder.newsClick.setText("已阅读：700");
		viewHolder.newsContent.setText("王宝强，1984年5月29日出生于河北省邢台市，中国内地男演员、导演。王宝强6岁开始练习武术，8岁在嵩山少林寺做俗家弟子。2003年，凭借剧情片《盲井》获得第40届台湾电影金马奖最佳新演员奖。");
		x.image().bind(viewHolder.newsImg, "http://pic.baike.soso.com/p/20090711/20090711101754-314944703.jpg");
		return convertView;
	}
	private class ViewHolder {
		@ViewInject(value = R.id.news_title)
		private TextView newsTitle;
		@ViewInject(value =R.id.news_date)
		private TextView newsDate;
		@ViewInject(value =R.id.news_click)
		private TextView newsClick;
		@ViewInject(value =R.id.news_content)
		private TextView newsContent;
		@ViewInject(value =R.id.news_img)
		private ImageView newsImg;
		public ViewHolder(View convertView) {
			x.view().inject(this, convertView);
		}
	}

}
