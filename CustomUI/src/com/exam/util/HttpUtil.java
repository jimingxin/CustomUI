package com.exam.util;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.android.volley.Request.Method;
import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.exam.customui.MainActivity;

import com.exam.app.Myapp;

import com.exam.bean.CandidateDetail;
import com.exam.bean.CandidateList;
import com.exam.bean.CurrentVote;
import com.exam.bean.LoginUser;
import com.exam.bean.PreviousVote;
import com.exam.listener.OnCandidateDetailFinishListener;
import com.exam.listener.OnCandidateListFinishListener;
import com.exam.listener.onCurrentVoteFinishListener;
import com.exam.listener.OnLoginFinishListener;
import com.exam.listener.OnPreviousVoteFinishListener;
import com.exam.listener.OnSubmitVoteFinishListener;
import com.google.gson.Gson;

public class HttpUtil {
	
	static Context context;
	
	public static void login(final String userName,final String password,final OnLoginFinishListener listener) {
		// TODO Auto-generated method stub
		
		new Thread(){
			
			public void run(){
				
				String url="http://118.26.135.179:1234/api/Account/Login";
				
				StringRequest req1 = new StringRequest(Method.POST, url, new Listener<String>() {
					
					@Override
					public void onResponse(String arg0){
						Log.d("TAG1", arg0);
						Gson gson=new Gson();
						 Myapp.loginUser=gson.fromJson(arg0, LoginUser.class);
						listener.onLoginFinish(Myapp.loginUser.getStatus());
					}
					
				}, null){
					
					@Override
					protected Map<String, String> getParams() throws AuthFailureError {
					
						Map<String ,String > map=new HashMap<String, String>();
						map.put("UserName", userName);
						map.put("Password", password);
						return map;
					}
				};
			
				Myapp.getQueue().add(req1);
			}
		}.start();

	}
	
	public  static void getCurrentVoteList(final int Id,final int pageNumber,final int pageSize,final onCurrentVoteFinishListener listener){
		new Thread(){
			public void run() {
				String url="http://118.26.135.179:1234/api/Vote/CurrentVotes?userId="+Id+"&pageNumber="+pageNumber+"&pageSize="+pageSize;
				StringRequest req2=new StringRequest(Method.GET,url, new Listener<String>() {

					@Override
					public void onResponse(String arg0) {
						Log.d("TAG2", arg0);
						Gson gson=new Gson();
						CurrentVote currentVote=gson.fromJson(arg0, CurrentVote.class);
						listener.onCurrentVoteFinished(currentVote);
						
					}
				}, null);
				Myapp.getQueue().add(req2);
				
			};
		}.start();
	}
	
	public static void getPreviousVoteList(final int Id,final int pageNumber,final int pageSize,final OnPreviousVoteFinishListener listener){
		new Thread(){
			public void run() {
				String url="http://118.26.135.179:1234/api/Vote/ForwardVotes?userId="+Id+"&pageNumber="+pageNumber+"&pageSize="+pageSize;
				StringRequest req3=new StringRequest(Method.GET,url,new Listener<String>() {

					@Override
					public void onResponse(String arg0) {
						Log.d("TAG3", arg0);
						Gson gson=new Gson();
						PreviousVote previousVote=gson.fromJson(arg0, PreviousVote.class);
						listener.onPreviousVoteFinished(previousVote);
						
					}
					
				} , null);
				Myapp.getQueue().add(req3);
			};
		}.start();
	}
	
	public static  void getCandidateList(final int userId,final int pageNumber,final int pageSize,final OnCandidateListFinishListener listener){
		new Thread(){
			public void run() {
				String url="http://118.26.135.179:1234/api/Vote/Candidates?voteId="+userId+"&pageNumber="+pageNumber+"&pageSize="+pageSize;
				Log.d("TAG", "URL: "+url);
				StringRequest req4=new StringRequest(Method.GET, url, new Listener<String>() {
					public void onResponse(String arg0) {
						Log.d("TAG4", arg0);
						Gson gson=new Gson();
						CandidateList candidateList=gson.fromJson(arg0, CandidateList.class);
						listener.onCandidateListFinished(candidateList.getData());
					};
				}, null);
				Myapp.getQueue().add(req4);
			};
		}.start();
	}
	
	public static void getCandidateDetail(final int userId,final OnCandidateDetailFinishListener listener){
		new Thread(){
			public void run() {
				String url="http://118.26.135.179:1234/api/Vote/CandidateDetail?userId="+userId;
				StringRequest req5=new StringRequest(Method.GET, url, new Listener<String>() {

					@Override
					public void onResponse(String arg0) {
						Log.d("TAG5", arg0);
						Gson  gson=new Gson();
						CandidateDetail  candidateDetail=gson.fromJson(arg0, CandidateDetail.class);
						listener.onCandidateDetailFinished(candidateDetail.getData());
						
					}
				}, null);
				Myapp.getQueue().add(req5);
			};
		}.start();
	}
	
	public static void submitVote(final int voteId,final String sb,final int userId,final OnSubmitVoteFinishListener listener){
		new Thread(){
			public void run() {
				Log.d("TAG", "-----11----");
				String url="http://118.26.135.179:1234/api/Vote/VoteSubmit";
				
				StringRequest req6=new StringRequest(Method.POST, url, new Listener<String>() {

					@Override
					public void onResponse(String arg0) {
					try {
						Log.d("TAG", "-----5----");
						JSONObject obj=new JSONObject(arg0);
						Log.d("TAG", "-----6----");
						int status=obj.getInt("status");
						Log.d("TAG", "-----7----");
						listener.onSubmitVoteFinished(status);
					} catch (JSONException e) {
						e.printStackTrace();
					}
					}
				}, null)
				{
					@Override
					protected Map<String, String> getParams() throws AuthFailureError {
						Map<String ,String > map=new HashMap<String, String>();
						map.put("VoteId", String.valueOf(voteId));
						map.put("SelectedUserIds", sb);
						map.put("CreateUserId", String.valueOf(userId));
						return map;
					}
				};
				Myapp.getQueue().add(req6);
			};
		}.start();
	}
	

}
