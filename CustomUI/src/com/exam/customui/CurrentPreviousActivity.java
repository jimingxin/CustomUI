package com.exam.customui;

import com.exam.customui.R;

import com.exam.adapter.VotePagerAdapter;

import android.app.Activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class CurrentPreviousActivity extends FragmentActivity {

	View headerView;
	TextView tvCurrent,tvPrevious;
	TextView tvTitle ;
	ImageView ivLeft;
	ViewPager viewPager;
	VotePagerAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_current_previous);
		
		initView();
		initViewPager();
		setListener();
	}
	
	private void initViewPager() {

		viewPager=(ViewPager) findViewById(R.id.vp_vote_viewpager);
		adapter=new VotePagerAdapter(getSupportFragmentManager());
		viewPager.setAdapter(adapter);
		viewPager.setCurrentItem(0);
	}
	
	private void initView() {

		headerView =findViewById(R.id.headerView);
		tvTitle= (TextView) headerView.findViewById(R.id.tv_headerview_title);
		tvTitle.setText("事件列表");
		
		ivLeft= (ImageView) headerView.findViewById(R.id.iv_headerview_left);
		ivLeft.setVisibility(View.VISIBLE);
		
		tvCurrent=(TextView) findViewById(R.id.current_vote_list);
		tvPrevious=(TextView) findViewById(R.id.previous_vote_list);
	}
	
	private void setListener() {
		tvCurrent.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				viewPager.setCurrentItem(0, false);
			}
		});
		tvPrevious.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				viewPager.setCurrentItem(1, false);

			}
		});
		ivLeft.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});

	}
	
}
