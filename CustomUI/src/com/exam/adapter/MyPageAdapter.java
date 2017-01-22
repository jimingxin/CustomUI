package com.exam.adapter;

import java.util.ArrayList;


import java.util.List;

import com.exam.fragment.FirstFragment;
import com.exam.fragment.MySetFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MyPageAdapter extends FragmentPagerAdapter {

	List<Fragment> fragments;
	
	public MyPageAdapter(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
		
		fragments = new ArrayList<Fragment>();
		fragments.add(new FirstFragment());
		fragments.add(new MySetFragment());
	}
	
	public MyPageAdapter(FragmentManager fm,List<Fragment> fragments) {
		// TODO Auto-generated constructor stub
		super(fm);
		this.fragments = fragments;
	}

	@Override
	public Fragment getItem(int arg0) {
		// TODO Auto-generated method stub
		return fragments.get(arg0);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return fragments.size();
	}

}
