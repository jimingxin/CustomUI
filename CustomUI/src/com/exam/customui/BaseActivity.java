package com.exam.customui;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class BaseActivity extends Activity {

	public static final int LEFT = 0;
	//设置标题
	public void setHeaderViewTitle(View headerView,String title){
		
		TextView tvTitle = (TextView) headerView.findViewById(R.id.tv_headerview_title);
		if(TextUtils.isEmpty(title)){
			tvTitle.setText("");
		}else{
			tvTitle.setText(title);
		}
	}
	
	//设置左边button图片
	public void setHeaderViewImageLeft(View headerView,boolean tag){
		ImageView ivLeft = (ImageView) headerView.findViewById(R.id.iv_headerview_left);
		if(tag){
			ivLeft.setVisibility(View.VISIBLE);
			ivLeft.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					finish();
				}
			});
		
		}else{
				ivLeft.setVisibility(View.INVISIBLE);
			}
		}
}
