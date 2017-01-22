package com.exam.customui;

import com.exam.adapter.MyPageAdapter;

import com.exam.customui.R;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;


public class MainActivity extends FragmentActivity {

	ViewPager viewPager;
	RadioGroup radioGroup;
	MyPageAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		 initView();
	     setListeners(); 
	}
	
	
	private void setListeners() {
		//ViewPager���һ������������
		viewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {

				switch (arg0) {
				case 0:
					radioGroup.check(R.id.rb_main_firstPage);
					break;
				case 1:
					radioGroup.check(R.id.rb_main_MySet);
					break;
				}

			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			
			}
		
		});
		
		
		radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				
				switch (checkedId) {
				case R.id.rb_main_firstPage:
					viewPager.setCurrentItem(0,false);
					break;
				case R.id.rb_main_MySet:
					viewPager.setCurrentItem(1,false);
					break;
				}
			}
		});
			
	}
	
	
	private void initView() {
		// TODO Auto-generated method stub
		
		viewPager = (ViewPager)findViewById(R.id.vp_main_viewpager);
		adapter = new MyPageAdapter(getSupportFragmentManager());
		viewPager.setAdapter(adapter);
		radioGroup = (RadioGroup) findViewById(R.id.rg_main_radiogroup);
	}
}
