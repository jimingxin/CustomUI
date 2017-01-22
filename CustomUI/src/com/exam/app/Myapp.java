package com.exam.app;

import com.android.volley.RequestQueue;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.android.volley.toolbox.ImageLoader.ImageCache;
import com.android.volley.toolbox.ImageLoader.ImageListener;

import com.exam.bean.LoginUser;
import com.exam.customui.R;

import android.app.Application;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.widget.ImageView;


public class Myapp extends Application {

	private static RequestQueue queue;
	public static ImageLoader imageLoader;
	public static LoginUser loginUser;
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		
		queue = Volley.newRequestQueue(this);
		loginUser=new LoginUser();
		imageLoader = new ImageLoader(queue, new ImageCache() {
			
			LruCache<String , Bitmap> cache = new LruCache<String, Bitmap>((int) (Runtime.getRuntime().maxMemory()/8)){
				protected int sizeOf(String key, Bitmap value) {
					return value.getHeight()*value.getRowBytes();
				};
			};
			
			@Override
			public void putBitmap(String arg0, Bitmap arg1) {
				cache.put(arg0, arg1);
				
			}
			
			@Override
			public Bitmap getBitmap(String arg0) {
				return cache.get(arg0);
			}
		});
	
	}

	public  static RequestQueue getQueue(){
		return queue;
	}
	
	public static void loadImage(String url,ImageView iv){
		ImageListener listener = ImageLoader.getImageListener(iv, R.drawable.bucket_no_picture, R.drawable.bucket_no_picture);
		imageLoader.get(url, listener );
	}

}
