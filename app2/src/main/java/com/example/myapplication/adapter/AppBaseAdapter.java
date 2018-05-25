package com.example.myapplication.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * 所有的Adapter都继承此AppBaseAdapter
 * @param <T>
 */
public abstract class AppBaseAdapter<T> extends BaseAdapter {
	public Context context;
	public List<T> list;
	public LayoutInflater inflater;
	public AppBaseAdapter(Context context, List<T> list) {
		 this.context = context ;
		 this.list = list;
		 inflater = LayoutInflater.from(context);
	}
	@Override
	public int getCount() {
		return list == null?0:list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		return getItemView(position, convertView, parent);
	}


	public abstract View getItemView(int position, View convertView, ViewGroup parent);

}
