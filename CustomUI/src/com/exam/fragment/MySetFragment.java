package com.exam.fragment;


import com.exam.customui.LoginActivity;

import com.exam.customui.R;
import com.exam.customui.UserMessageActivity;

import android.app.AlertDialog;
import android.support.v4.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class MySetFragment extends Fragment {
	View headerView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		View view=inflater.inflate(R.layout.fragment_myset, container, false);
		initHeaderView(view);
		initContentView(view);
		return view;
	}
	
	
	private void initHeaderView(View view) {
		// TODO Auto-generated method stub
		headerView=view.findViewById(R.id.headerView);
		TextView tvTitle=(TextView) headerView.findViewById(R.id.tv_headerview_title);
		tvTitle.setText("我的");
		ImageView ivLeft=(ImageView) headerView.findViewById(R.id.iv_headerview_left);
		ivLeft.setVisibility(view.INVISIBLE);
	}
	
	private void initContentView(View view) {
		// TODO Auto-generated method stub
		
		TextView tvUserMessage=(TextView) view.findViewById(R.id.myset_usermessage);
		tvUserMessage.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(getActivity(), UserMessageActivity.class);
				startActivity(intent);
				
			}
		});
		
		TextView tvUserQuit=(TextView) view.findViewById(R.id.myset_userquit);
		tvUserQuit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//提示框
				AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
							builder.setIcon(android.R.drawable.ic_menu_info_details)
							.setTitle("提示")
							.setMessage("是否退出系统")
							.setNegativeButton("取消", null)
							.setPositiveButton("确定", new DialogInterface.OnClickListener() {
								
								@Override
								public void onClick(DialogInterface dialog, int which) {
									// TODO Auto-generated method stub
									Intent intent=new Intent(getActivity(), LoginActivity.class);
									startActivity(intent);
									getActivity().finish();
								}
							}).create().show();
							
							
			}
		});
		
	}
	
	
	
}
