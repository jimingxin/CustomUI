package com.exam.adapter;

import java.util.List;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class MyBaseAdapter<T> extends BaseAdapter {

	List<T> ts;
	Context context;
	LayoutInflater inflater;
	
	public MyBaseAdapter(Context context,List<T> ts) {
		this.context=context;
		this.ts=ts;
		inflater=(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
	}
	
	@Override
	public int getCount() {
		return ts.size();
	}

	@Override
	public T getItem(int position) {
		return ts.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		return getItemView(position,convertView,parent);
	}

	public abstract View getItemView(int position, View convertView, ViewGroup parent) ;

	public void addAll(List<T> list,boolean isClearDataSource){
	
		if(isClearDataSource){
		
			ts.clear();
		}
	
		ts.addAll(list);
		notifyDataSetChanged();
	}
	
	public void remove(T t){
		
		ts.remove(t);
		notifyDataSetChanged();
	}

}
