package com.exam.adapter;

import java.util.List;

import com.exam.bean.PreviousVote.Data;
import com.exam.customui.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class PreviousVoteAdapter extends MyBaseAdapter<Data> {

	public PreviousVoteAdapter(Context context, List<Data> ts) {
		super(context, ts);
	}
	
	@Override
	public View getItemView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		ViewHolder vh;
		
		if(convertView==null){
			convertView=inflater.inflate(R.layout.item_vote_list, parent,false);
			vh=new ViewHolder();
			vh.tvTitle=(TextView) convertView.findViewById(R.id.item_vote_name);
			vh.tvCreateTime=(TextView) convertView.findViewById(R.id.item_vote_starttime); 
			vh.tvEndTime=(TextView) convertView.findViewById(R.id.item_vote_endtime);
			vh.ivTag=(ImageView) convertView.findViewById(R.id.item_vote_imageview);
			convertView.setTag(vh);
		}else{
			vh=(ViewHolder) convertView.getTag();
		}
		
		Data data=getItem(position);
		vh.tvTitle.setText(data.getSubject());
		vh.tvCreateTime.setText("开始时间:"+data.getCreateDate());
		vh.tvEndTime.setText("结束时间:"+data.getDeadline());
		if(data.isHasVoted()){
			vh.ivTag.setVisibility(View.VISIBLE);
		}else{
			vh.ivTag.setVisibility(View.INVISIBLE);
		}
		return convertView;
		
	}

	public class ViewHolder{

		TextView tvTitle,tvCreateTime,tvEndTime;
		ImageView ivTag;
	}
	
	
	
}
