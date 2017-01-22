package com.exam.adapter;

import java.util.List;

import com.exam.app.Myapp;
import com.exam.bean.CandidateList;
import com.exam.bean.CandidateList.Data;
import com.exam.customui.R;
import com.exam.myview.CircleImageView;
import com.exam.app.Myapp;
import android.view.View.OnClickListener;
import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class CandidateListAdapter extends MyBaseAdapter<CandidateList.Data> {

	onItemCheckedListener listener;
	boolean isHasVoted;
	int mustSeleted;
	int restNumber;
	int alreadyNumber;

	public CandidateListAdapter(Context context, boolean isHasVoted,int mustSeleted,List<Data> ts,onItemCheckedListener listener,int alreadyNumber) {
		super(context, ts);
		
		this.isHasVoted=isHasVoted;
		this.listener=listener;
		this.mustSeleted=mustSeleted;
		this.alreadyNumber=alreadyNumber;
	}
	
	@Override
	public View getItemView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		final ViewHolder vh;
		final Data data=getItem(position);
		
		if(convertView==null){
			convertView=inflater.inflate(R.layout.item_candidate_list, parent,false);
			vh=new ViewHolder();
			vh.avatar=(CircleImageView) convertView.findViewById(R.id.candidate_avatar);
			vh.tvName=(TextView) convertView.findViewById(R.id.vote_username);
			vh.tvAge=(TextView) convertView.findViewById(R.id.vote_age);
			vh.tvPisition=(TextView) convertView.findViewById(R.id.vote_position);
			vh.tvDepartment=(TextView) convertView.findViewById(R.id.vote_department);
			vh.button=(Button) convertView.findViewById(R.id.candidate_button);
			convertView.setTag(vh);
		}else{
			vh=(ViewHolder) convertView.getTag();
		}
		
		Myapp.loadImage("http://118.26.135.179:1234"+data.getAvatar(), vh.avatar);
		vh.tvName.setText(data.getTrueName());
		vh.tvAge.setText("年龄: "+data.getAge());
		vh.tvPisition.setText("ְ职位: "+data.getPosition());
		vh.tvDepartment.setText("部门: "+data.getDepartment());
		vh.button.setSelected(data.isHasSeleted());
		vh.button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(isHasVoted){
					AlertDialog.Builder builder=new AlertDialog.Builder(context);
					builder.setIcon(android.R.drawable.ic_menu_info_details)
					.setTitle("提示")
					.setMessage("当前投票已完成")
					.setNegativeButton("确定", null).create().show();
					
					return;
				}
				
				if(getCheckedCount()>=alreadyNumber){
					if(data.isHasSeleted()){
						data.setHasSeleted(false);
						vh.button.setSelected(false);
						//ִ�лص�����
						listener.onItemChecked(getCheckedCount());
						return;
					}else{
						AlertDialog.Builder builder=new AlertDialog.Builder(context);
						builder.setIcon(android.R.drawable.ic_menu_info_details)
						.setTitle("提示")
						.setMessage("当前剩余票数已用完")
						.setNegativeButton("确定", null).create().show();
						return;
					}
					
				}
				
				
				if(data.isHasSeleted()){
					data.setHasSeleted(false);
					vh.button.setSelected(false);
				}else if(!data.isHasSeleted()){
					data.setHasSeleted(true);
					vh.button.setSelected(true);
				}
				listener.onItemChecked(getCheckedCount());
				
			}
		});

		return convertView;
	}
	

	public class ViewHolder{
		CircleImageView avatar;
		TextView tvName,tvPisition,tvAge,tvDepartment;
		Button button;
	}
	
	public interface onItemCheckedListener{
		void onItemChecked(int count);
	}


	public int getCheckedCount(){
		int checkedCount=0;
		for(Data item:ts){
			if(item.isHasSeleted()){
				checkedCount++;
			}
		}
		return checkedCount;
	}
	
	public String getCheckedID(){
		StringBuilder sb=new StringBuilder();
		for(Data item:ts){
			if(item.isHasSeleted()){
				sb.append(item.getId()).append(",");
			}
		}
		return sb.toString().substring(0, sb.length()-1);
	}
	


}
