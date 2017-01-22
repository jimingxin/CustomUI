package com.exam.customui;

import java.util.ArrayList;
import java.util.List;



import com.exam.adapter.CandidateListAdapter;
import com.exam.adapter.CandidateListAdapter.onItemCheckedListener;
import com.exam.bean.CandidateList;
import com.exam.listview.XListView;
import com.exam.customui.CurrentPreviousActivity;
import com.exam.customui.VoteSelectActivity;
import com.exam.app.Myapp;
import com.exam.listener.OnSubmitVoteFinishListener;
import com.exam.util.HttpUtil;
import com.exam.customui.CandidateActivity;
import com.exam.bean.CandidateList.Data;
import com.exam.listener.OnCandidateListFinishListener;
import com.exam.util.NetUtil;
import com.exam.customui.VoteSelectActivity;
import com.exam.listview.XListView.IXListViewListener;
import com.exam.customui.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class VoteSelectActivity extends BaseActivity implements onItemCheckedListener{

	View headerView;//����
	TextView tvAlready,tvRestVote,tvJugeUser;//��ͶδͶƱ��
	int alreadyNumber,restNumber;
	int mustSeleted;
	int createUserId;
	
	Button submitButton;//�ύͶƱ��ť
	
	int voteId;
	boolean hasVoted;
	
	XListView listView;
	CandidateListAdapter adapter;
	List<CandidateList.Data> dataList;
	
	int pageNumber=1;//��ҳ���ص�ҳ��
	int pageSize=10;//��ҳ���ص���Ŀ��
	String sybmit="";
	StringBuilder sb=new StringBuilder();//�ύͶƱ��id�ַ���
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vote_select);
		
		initHeaderView();
		initView();
		initPullToRefresh();
		setListener();
		refresh();
	}
	
	private void initPullToRefresh() {
		listView=(XListView) findViewById(R.id.candidate_pullrefresh);
		listView.setPullRefreshEnable(true);
		listView.setPullLoadEnable(true);

		dataList=new ArrayList<CandidateList.Data>();
		adapter =new CandidateListAdapter(VoteSelectActivity.this,hasVoted,mustSeleted,dataList,this,alreadyNumber);
		listView.setAdapter(adapter);

	}

	private void initView() {
		
		voteId=getIntent().getIntExtra("voteId",0);
		restNumber=alreadyNumber=mustSeleted=getIntent().getIntExtra("MustSeleted", 0);
		createUserId=getIntent().getIntExtra("CreateUserId", 0);
		hasVoted=getIntent().getBooleanExtra("HasVoted", true);
		
		tvJugeUser=(TextView) findViewById(R.id.judge_vote);
		submitButton=(Button) findViewById(R.id.submit_vote);
		tvAlready=(TextView) findViewById(R.id.already_vote_number);
		tvRestVote=(TextView) findViewById(R.id.rest_vote_number);
		
		if(hasVoted){
			tvAlready.setText("已投票数: "+alreadyNumber);
			tvRestVote.setText("剩余票数: "+0);
		}else{
			tvAlready.setText("已投票数: "+0);
			tvRestVote.setText("剩余票数: "+restNumber);
		}
		
		
		submitButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				if (hasVoted) {
					AlertDialog.Builder builder=new AlertDialog.Builder(VoteSelectActivity.this);
					builder.setIcon(android.R.drawable.ic_menu_info_details)
					.setTitle("提示")
					.setMessage("当前投票已完成,无法提交")
					.setNegativeButton("确定", null).create().show();
					return;
				
				}else {
					if(restNumber!=0){
						AlertDialog.Builder builder=new AlertDialog.Builder(VoteSelectActivity.this);
						builder.setIcon(android.R.drawable.ic_menu_info_details)
						.setTitle("提示")
						.setMessage("您尚有剩余票数,无法提交")
						.setNegativeButton("确定", null).create().show();
						return;	
					}else {
						if(sybmit.equals("success")){
							Toast.makeText(VoteSelectActivity.this, "提交成功", 1).show();
							return;
						}else{
							AlertDialog.Builder builder=new AlertDialog.Builder(VoteSelectActivity.this);
							builder.setIcon(android.R.drawable.ic_menu_info_details)
							.setTitle("提示")
							.setMessage("请确认当前投票是否提交,提交后无法更改")
							.setNegativeButton("取消", null)
							.setPositiveButton("确定", new DialogInterface.OnClickListener() {
								
								@Override
								public void onClick(DialogInterface dialog, int which) {
									voteSubmit();
								}
							}
							).create().show();
							
						}
						
					}
				
				}
		
				
			}
		});	
	}
	
	
	
	private void setListener() {
		
		listView.setXListViewListener(new IXListViewListener() {

			@Override
			public void onRefresh() {
				adapter.notifyDataSetChanged();
				listView.stopRefresh();
				listView.setRefreshTime("刚刚");
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
				CandidateList.Data candidateList=adapter.getItem(position-1);
				int candidateId=candidateList.getId();
				Intent intent=new Intent(getApplicationContext(),CandidateActivity.class);
				intent.putExtra("UserId", candidateId);
				startActivity(intent);
			}
		});
		
		
	}
	
	private void initHeaderView() {
		headerView=findViewById(R.id.headerView);
		setHeaderViewTitle(headerView, "候选列表");
		setHeaderViewImageLeft(headerView, true);
	}
	
	
	@Override
	protected void onResume() {
		super.onResume();
		//refresh();
	}
	
	
	
	
	protected void voteSubmit() {
		String ids=adapter.getCheckedID();
		Log.d("TAG","ids"+ids);
		HttpUtil.submitVote(voteId, ids, Myapp.loginUser.getData().getId(), new OnSubmitVoteFinishListener() {
			@Override
			public void onSubmitVoteFinished(int status) {
				if(status==1){
				
					AlertDialog.Builder builder=new AlertDialog.Builder(VoteSelectActivity.this);
					builder.setIcon(android.R.drawable.ic_menu_info_details)
					.setTitle("提示")
					.setMessage("提交成功")
					.setNegativeButton("确定", null).create().show();
					sybmit="success";
					Intent intent=new Intent(VoteSelectActivity.this,CurrentPreviousActivity.class);
					startActivity(intent);
					finish();
				}else{
					Toast.makeText(VoteSelectActivity.this, "提交失败", 1).show();
				}

			}
		});
	}
	
	
	private void refresh() {
		if (!NetUtil.isNetworkAvailable(this)) {
			Toast.makeText(this, "当前网络不可用", 1).show();
			return;
		}
			HttpUtil.getCandidateList(voteId, pageNumber, pageSize, new OnCandidateListFinishListener() {

				@Override
				public void onCandidateListFinished(List<Data> data) {
					dataList.addAll(data);
					adapter.notifyDataSetChanged();
					if(adapter.getCount()<(pageNumber*10)){
						listView.setPullLoadEnable(false);
					}
				}
			});
		}

	@Override
	public void onItemChecked(int count) {
		// TODO Auto-generated method stub
		Log.d("TAG", "count: "+count);
		if(count>alreadyNumber){
			return;
		}
		restNumber=alreadyNumber-count;
		tvAlready.setText("已投票数: "+count);
		tvRestVote.setText("剩余票数: "+restNumber);
	}
}
