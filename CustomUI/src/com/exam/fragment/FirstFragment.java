package com.exam.fragment;

import com.exam.customui.CurrentPreviousActivity;


import com.exam.customui.MeetActivity;
import com.exam.customui.R;

import android.support.v4.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.View.OnClickListener;

public class FirstFragment extends Fragment {

	View headerView;
	LinearLayout voteLayout,meetLayout;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		View view=inflater.inflate(R.layout.fragment_first, container, false);
		initHeaderView(view);
		initVoteMeetView(view);
		
		return view;
	}
	
	
	private void initHeaderView(View view) {
		
		headerView = view.findViewById(R.id.headerView);
		TextView tvTitle = (TextView)headerView.findViewById(R.id.tv_headerview_title);
		tvTitle.setText("首页");
		ImageView ivLeft = (ImageView)headerView.findViewById(R.id.iv_headerview_left);
		ivLeft.setVisibility(view.INVISIBLE);
	}

	private void initVoteMeetView(View view) {
		// TODO Auto-generated method stub
		voteLayout = (LinearLayout)view.findViewById(R.id.firstpage_vote);
		voteLayout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent =new Intent(getActivity(), CurrentPreviousActivity.class);
				startActivity(intent);
			}
		});
	
		meetLayout = (LinearLayout)view.findViewById(R.id.firstpage_meet);
		meetLayout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent =new Intent(getActivity(), MeetActivity.class);
				startActivity(intent);
			}
		});
		
	}

}
