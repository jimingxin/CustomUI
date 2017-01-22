package com.exam.customui;

import com.exam.customui.R;
import com.exam.customui.VoteSelectActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class VoteExplainActivity extends BaseActivity {

	View headerView;
	TextView tvTitle,tvDescription,tvCreateTime,tvDeadTime,tvMustSeleted;
	Button btnGreen,btnBlue,btnGray;
	int id;
	String subject;
	String description;
	String createTime;
	String deadLine;
	int mustSeleted;
	int createUserId;
	boolean hasVoted;
	String from;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vote_explain);
		
		initHeadrView();
		initView();
		setListener();
	}
	
	private void initHeadrView() {
		headerView=findViewById(R.id.headerView);
		setHeaderViewTitle(headerView, "详情界面");
		setHeaderViewImageLeft(headerView, true);
	}

	private void initView() {
			
		tvTitle=(TextView) findViewById(R.id.vote_explain_title);
		tvDescription=(TextView) findViewById(R.id.vote_explain_describe);
		tvCreateTime=(TextView) findViewById(R.id.vote_explain_createtime);
		tvDeadTime=(TextView) findViewById(R.id.vote_explain_deadtime);
		tvMustSeleted=(TextView) findViewById(R.id.vote_explain_number);
		btnGreen=(Button) findViewById(R.id.vote_explain_button_green);
		btnBlue=(Button) findViewById(R.id.vote_explain_button_blue);
		btnGray=(Button) findViewById(R.id.vote_explain_button_gray);
		
		id=getIntent().getIntExtra("Id", 0);
		subject=getIntent().getStringExtra("Subject");
		description=getIntent().getStringExtra("Description");
		mustSeleted=getIntent().getIntExtra("MustSelect", 0);
		createTime=getIntent().getStringExtra("CreateDate");
		deadLine=getIntent().getStringExtra("Deadline");
		from=getIntent().getStringExtra("from");
		createUserId=getIntent().getIntExtra("CreateUserId", 0);
		hasVoted=getIntent().getBooleanExtra("HasVoted",true);
		
		tvTitle.setText(subject+"\n相关说明");
		tvDescription.setText(description);
		tvCreateTime.setText("开始时间:"+createTime);
		tvDeadTime.setText("结束时间:"+deadLine);
		tvMustSeleted.setText("每人"+mustSeleted+"票");

		if(from.equals("current")){
			Log.d("TAG", from);
			if (hasVoted) {
				btnBlue.setVisibility(View.VISIBLE);
			}else{
				btnGreen.setVisibility(View.VISIBLE);
			}
		}else if(from.equals("pre")){
			btnGray.setVisibility(View.VISIBLE);
		}
		
	}
	
	
	private void setListener() {

		btnGreen.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent=new Intent(getApplicationContext(),VoteSelectActivity.class);
				intent.putExtra("voteId", id);
				intent.putExtra("MustSeleted", mustSeleted);
				intent.putExtra("createUserId", createUserId);
				intent.putExtra("HasVoted", hasVoted);
				startActivity(intent);
			}
		});
		
		btnBlue.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent=new Intent(getApplicationContext(),VoteSelectActivity.class);
				intent.putExtra("voteId", id);
				intent.putExtra("MustSeleted", mustSeleted);
				intent.putExtra("CreateUserId", createUserId);
				intent.putExtra("HasVoted", hasVoted);
				startActivity(intent);

			}
		});
		
	}
	
	
}
