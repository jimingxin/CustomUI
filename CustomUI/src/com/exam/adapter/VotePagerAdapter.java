package com.exam.adapter;

import java.util.ArrayList;



import java.util.List;

import com.exam.fragment.CurrentVoteFragment;
import com.exam.fragment.PreviousVoteFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class VotePagerAdapter extends FragmentPagerAdapter {

	List<Fragment> fragments;
	
	public VotePagerAdapter(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
		
		fragments=new ArrayList<Fragment>();
		fragments.add(new CurrentVoteFragment());
		fragments.add(new PreviousVoteFragment());
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
