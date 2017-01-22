package com.exam.fragment;

import java.util.ArrayList;

import java.util.List;

import com.exam.adapter.CurrentVoteAdapter;
import com.exam.bean.CurrentVote;
import com.exam.listview.XListView;
import com.exam.listview.XListView.IXListViewListener;
import com.exam.customui.VoteExplainActivity;
import com.exam.listener.onCurrentVoteFinishListener;
import com.exam.customui.R;

import com.exam.util.HttpUtil;
import com.exam.util.NetUtil;
import com.exam.app.Myapp;

import android.support.v4.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class CurrentVoteFragment extends Fragment {

	XListView listview;
	int pageNumber=1;
	int pageSize=10;

	CurrentVoteAdapter mAdapter;
	
	List<CurrentVote.Data> dataList;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		View view=inflater.inflate(R.layout.fragment_current_vote, container,
				false);
		initView(view);
		refresh();
		return view;
	}
	
	private void initView(View view) {
		// TODO Auto-generated method stub	
		listview=(XListView) view.findViewById(R.id.current_pullrefresh);
		listview.setPullRefreshEnable(true);
		listview.setPullLoadEnable(true);
		
		dataList=new ArrayList<CurrentVote.Data>();
		mAdapter =new CurrentVoteAdapter(getActivity(), dataList);
		listview.setAdapter(mAdapter);
		
		listview.setXListViewListener(new IXListViewListener() {
			
			@Override
			public void onRefresh() {
				mAdapter.notifyDataSetChanged();
				listview.stopRefresh();
				listview.setRefreshTime("刚刚");	
			}
			
			@Override
			public void onLoadMore() {
				pageNumber++;
				refresh();
				listview.stopLoadMore();
				
			}
		});
		
		
		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				CurrentVote.Data currvote=mAdapter.getItem(position-1);
				Intent intent=new Intent(getActivity(),VoteExplainActivity.class );
				intent.putExtra("from", "current");
				intent.putExtra("Id",currvote.getId() );
				Log.d("TAG", currvote.getId()+"");
				intent.putExtra("Subject", currvote.getSubject());
				intent.putExtra("Description", currvote.getDescription());
				intent.putExtra("MustSelect", currvote.getMustSelect());
				intent.putExtra("CreateDate", currvote.getCreateDate());
				intent.putExtra("Deadline", currvote.getDeadline());
				intent.putExtra("HasVoted", currvote.isHasVoted());
				intent.putExtra("CreateUserId", currvote.getCreateUserId());
				startActivity(intent);
			}
		});
		
	}

	@Override
	public void onResume() {
		super.onResume();
		//refresh();
	}
	
	//获取数据
	private void refresh() {
		// TODO Auto-generated method stub

		if (!NetUtil.isNetworkAvailable(getActivity())) {
			Toast.makeText(getActivity(), "当前网络不可用", 1).show();
			return;
		}
		HttpUtil.getCurrentVoteList(Myapp.loginUser.getData().getId(), pageNumber, pageSize, new onCurrentVoteFinishListener() {

			@Override
			public void onCurrentVoteFinished(CurrentVote currentVote){
				dataList=currentVote.getData();
				
				if(dataList!=null&&dataList.size()>0){
					mAdapter.addAll(dataList, false);
					
					if(mAdapter.getCount()<(pageNumber*10)){
						listview.setPullLoadEnable(false);
					}
				}
			}
		});
	
	}
	
	
	
}
