package com.example.suntest;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.content.Context;

public class UpdateInfoService {
	private Context context;

	public UpdateInfoService(Context context) {
		this.context = context;
	}
	
	public UpdateInfo getUpdateInfo(int urlId)throws Exception{
		String path = context.getResources().getString(urlId);//拿到config.xml里存放的地址
		URL url = new URL(path);
		HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();//开启一个叫http链接
		httpURLConnection.setConnectTimeout(5000);//设置链接的超时时间，现在为五秒
		httpURLConnection.setRequestMethod("GET");
		InputStream is = httpURLConnection.getInputStream();
		return UpdateInfoParser.getUpdateInfo(is);//解析XML
	}
}
