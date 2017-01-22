package com.exam.fragment;

import java.util.ArrayList;



import java.util.List;

import com.exam.adapter.PreviousVoteAdapter;
import com.exam.bean.PreviousVote;
import com.exam.listview.XListView;
import com.exam.listview.XListView.IXListViewListener;
import com.exam.app.Myapp;
import com.exam.listener.OnPreviousVoteFinishListener;
import com.exam.util.HttpUtil;
import com.exam.util.NetUtil;
import com.exam.customui.VoteExplainActivity;
import com.exam.customui.R;


import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class PreviousVoteFragment extends Fragment {

	int pageNumber=1;
	int pageSize=10;
	XListView listView;
	
	PreviousVoteAdapter mAdapter;
	List<PreviousVote.Data> dataList=new ArrayList<PreviousVote.Data>();
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		View view= inflater.inflate(R.layout.fragment_pervious_vote, container,
				false);
		initView(view);
		refresh();
		
		return view;
	}
	
	
	private void initView(View view) {
		// TODO Auto-generated method stub
		
		listView=(XListView) view.findViewById(R.id.previous_pullrefresh);

		listView.setPullRefreshEnable(true);
		listView.setPullLoadEnable(true);
		mAdapter =new PreviousVoteAdapter(getActivity(), dataList);
		listView.setAdapter(mAdapter);
		listView.setXListViewListener(new IXListViewListener() {

			@Override
			public void onRefresh() {
				mAdapter.notifyDataSetChanged();
				listView.stopRefresh();
				listView.setRefreshTime("�ո�");
			}

			@Override
			public void onLoadMore() {
				pageNumber++;
				refresh();
				listView.stopLoadMore();
			}
		});
		
		
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				PreviousVote.Data prevote=mAdapter.getItem(position-1);
				Intent intent=new Intent(getActivity(),VoteExplainActivity.class );
				intent.putExtra("from", "pre");
				intent.putExtra("Id",prevote.getId() );
				intent.putExtra("Subject", prevote.getSubject());
				intent.putExtra("Description", prevote.getDescription());
				intent.putExtra("MustSelect", prevote.getMustSelect());
				intent.putExtra("CreateDate", prevote.getCreateDate());
				intent.putExtra("Deadline", prevote.getDeadline());
				intent.putExtra("HasVoted", prevote.isHasVoted());
				intent.putExtra("CreateUserId", prevote.getCreateUserId());
				startActivity(intent);

			}

		});	
		
	}
	
	@Override
	public void onResume() {
		super.onResume();
		//refresh();
	}
	
	
	private void refresh(){
		if (!NetUtil.isNetworkAvailable(getActivity())) {
			Toast.makeText(getActivity(), "当前网络不可用", 1).show();
			return;
		}
		HttpUtil.getPreviousVoteList(Myapp.loginUser.getData().getId(), pageNumber, pageSize, new OnPreviousVoteFinishListener() {
			
			@Override
			public void onPreviousVoteFinished(PreviousVote previousVote) {
				dataList=previousVote.getData();
				
				if(dataList!=null&&dataList.size()>0){
					mAdapter.addAll(dataList, false);
					if(mAdapter.getCount()<(pageNumber*10)){
						listView.setPullLoadEnable(false);
					}
				}

			}
		});
	}

	
	
	
}
