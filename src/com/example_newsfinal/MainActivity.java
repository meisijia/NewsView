package com.example_newsfinal;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
import android.app.Activity;

public class MainActivity extends Activity {

	protected static final int MSG_RES_ERROR = 1;
	protected static final int MSG_NET_ERROR = 2;
	protected static final int MSG_OK = 3;

	private List<News> list = new ArrayList<News>();
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case MSG_OK:
				//��lv����ʾ����
				lv.setAdapter(new NewsAdapter(MainActivity.this,list));
				
				
				break;
			case MSG_RES_ERROR:
			case MSG_NET_ERROR:
				Toast.makeText(MainActivity.this, msg.toString(), Toast.LENGTH_SHORT).show();
				break;

			default:
				break;
			}
		};
	};
	private ListView lv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		lv = (ListView) findViewById(R.id.lv);
		// һ�����Ϳ�ʼ��ʾ
		new Thread() {
			@Override
			public void run() {
				try {
					//TODO ��okhttp ���Կ�����
					URL url = new URL(
							"http://192.168.72.47:8080/news/news.json");

					HttpURLConnection connection = (HttpURLConnection) url
							.openConnection();

					connection.setRequestMethod("GET");
					connection.setConnectTimeout(5000);
					connection.connect();

					int responseCode = connection.getResponseCode();
//����okhttp�Ż�
                    Log.d("tag",  "okhttp");

					if (responseCode == 200) {
						// ������ ��
						InputStream is = connection.getInputStream();
						// ��isת��Ϊstring
						String Jsonarray = StringUtils.Is2String(is);

						// Log.d("tag",Jsonarray);
						//TODO ��Gson���Կ�����
						/*Gson gson = new Gson();
						
						Type type = new TypeToken<List<User>>(){}.getType();
						
						List<User> userList = gson.fromJson(jsonArray, type);*/
						Gson gson = new Gson();
						
						Type type = new TypeToken<List<News>>(){}.getType();
						list = gson.fromJson(Jsonarray, type);
						
						
					/*	JSONArray array = new JSONArray(Jsonarray);

						// ����
						for (int i = 0; i < array.length(); i++) {
							News news = new News();
							JSONObject object = array.getJSONObject(i);
							news.title = object.getString("title");
							news.description = object.getString("description");
							news.image = object.getString("image");
							news.type = object.getInt("type");

							if (object.has("comment")) {
								news.comment = object.getInt("comment");
							}

							list.add(news);
						
				}*/

						Log.d("tag", list + "");
						handler.sendEmptyMessage(MSG_OK);
					} else {
						Message msg = new Message();
						msg.obj = "��Ӧ�벻��ȷ" + responseCode;
						msg.what = MSG_RES_ERROR;
						handler.sendMessage(msg);
					}

				} catch (Exception e) {
					handler.obtainMessage(MSG_NET_ERROR, "�쳣:" + e.getMessage())
							.sendToTarget();
					e.printStackTrace();
				}

			};
		}.start();

	}

}
