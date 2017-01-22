package com.exam.customui;

import com.exam.customui.R;
import com.exam.app.Myapp;
import com.exam.bean.CandidateDetail.Data;
import com.exam.listener.OnCandidateDetailFinishListener;
import com.exam.util.HttpUtil;

import android.app.Activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class CandidateActivity extends BaseActivity {

	View headerView;
	ImageView ivAvatar;
	TextView tvName,tvAge,tvPosition,tvDepartment,tvBasicMessage,tvVoteMessage;
	int userId;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_candidate);
		
		initHeadrView();
		initView();
	}

	private void initHeadrView() {
		headerView=findViewById(R.id.headerView);
		setHeaderViewTitle(headerView, "候选人详情");
		setHeaderViewImageLeft(headerView, true);	
	}
	
	private void initView() {
		
		ivAvatar=(ImageView) findViewById(R.id.candidate_detail_avatar);
		tvName=(TextView) findViewById(R.id.candidate_detail_username);
		tvAge=(TextView) findViewById(R.id.candidate_detail_age);
		tvPosition=(TextView) findViewById(R.id.candidate_detail_position);
		tvDepartment=(TextView) findViewById(R.id.candidate_detail_department);
		tvBasicMessage=(TextView) findViewById(R.id.candidate_detail_message);
		tvVoteMessage=(TextView) findViewById(R.id.candidate_detail_votemessage);
		userId=getIntent().getIntExtra("UserId", 0);	
	}
	
	private void refresh() {
		
		HttpUtil.getCandidateDetail(userId, new OnCandidateDetailFinishListener() {
			
			@Override
			public void onCandidateDetailFinished(Data data) {
				
				Myapp.loadImage("http://118.26.135.179:1234"+data.getAvatar(), ivAvatar);
				tvName.setText("姓名: "+data.getTrueName());
				tvAge.setText("年龄: "+data.getAge());
				tvPosition.setText("ְ职位: "+data.getPosition());
				tvDepartment.setText("部门: "+data.getDepartment());
				tvBasicMessage.setText(data.getDescription());
				tvVoteMessage.setText(data.getAchievement());
				
			}
		});
		
	}

	@Override
	protected void onResume() {
		super.onResume();
		refresh();
	}
}
