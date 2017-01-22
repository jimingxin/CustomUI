package com.exam.customui;

import com.exam.util.NetUtil;
import com.exam.util.HttpUtil;

import com.exam.customui.LoginActivity;
import com.exam.customui.MainActivity;
import com.exam.listener.OnLoginFinishListener;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

public class LoginActivity extends Activity {

	FrameLayout  loginBack,loginUser,loginPassward;
	EditText etUserName;
	EditText etPassword;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		initView();
	}

	
	private void initView() {
//		loginBack=(FrameLayout) findViewById(R.id.loginbackground1);
		loginUser=(FrameLayout) findViewById(R.id.loginuserlayout);
		loginPassward=(FrameLayout) findViewById(R.id.loginpasswardlayout);
		
		etUserName=(EditText) findViewById(R.id.loginuserEdit);
		etPassword=(EditText) findViewById(R.id.loginpasswardEdit);
	}
	
	public void loginIn(View view) {
		// TODO Auto-generated method stub

		if(TextUtils.isEmpty(etUserName.getText())||TextUtils.isEmpty(etPassword.getText())){
			Toast.makeText(this, "请输入用户名或密码", 1).show();
			return;
		}
		
		if(!NetUtil.isNetworkAvailable(this)){
			Toast.makeText(this, "当期网络不可用", 1).show();
			
			return;
		}
		
		HttpUtil.login(String.valueOf(etUserName.getText()), String.valueOf(etPassword.getText()),new OnLoginFinishListener() {
			
			@Override
			public void onLoginFinish(int status) {
				// TODO Auto-generated method stub
				if(status==1){
					
					Intent intent=new Intent(getApplicationContext(),MainActivity.class);
					startActivity(intent);
					finish();
				
				}else if(status==0){
					
					AlertDialog.Builder builder=new AlertDialog.Builder(LoginActivity.this);
					builder.setIcon(android.R.drawable.ic_menu_info_details)
					.setTitle("提醒")
					.setMessage("登录失败")
					.setNegativeButton("确定", null).create().show();
				}
				
				
			}
		});
	}
}
