package com.example_newsfinal;

import java.util.List;

import com.bumptech.glide.Glide;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class NewsAdapter extends BaseAdapter {
	private Context context;
	private List<News> list;
	
	public NewsAdapter(Context context, List<News> list) {
		super();
		this.context = context;
		this.list = list;
	}

	@Override
	public int getCount() {

		return list.size()<0?0:list.size();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		News news = list.get(position);
		//布局填充器   viewholder优化
		//TODO 布局文件重新写一遍!!item
		View view;
		ViewHolder viewholder = null;
		if (convertView == null) {
			//布局填充器
			view = LayoutInflater.from(context).inflate(R.layout.list_news, parent, false);
			
			viewholder = new ViewHolder();
			viewholder.tv_title = (TextView) view.findViewById(R.id.tv_title);
			viewholder.tv_type = (TextView) view.findViewById(R.id.tv_type);
			viewholder.tv_desc = (TextView) view.findViewById(R.id.tv_description);
			viewholder.iv = (ImageView) view.findViewById(R.id.iv);
			
			view.setTag(viewholder);
			
		}else {
			view = convertView;
			viewholder= (ViewHolder) view.getTag();
		}
		viewholder.tv_title.setText(news.title);
		viewholder.tv_desc.setText(news.description);
		
		switch (news.type) {
		case 1:
			viewholder.tv_type.setText(news.type +"");
			break;
		case 2:
			viewholder.tv_type.setText("新闻");
			break;
		case 3:
			viewholder.tv_type.setText("直播");
			break;
		default:
			break;
		}
		
		//加载图片,用Glide
		Glide.with(context).load(news.image).into(viewholder.iv);
		
		return view;
	}
	
	class ViewHolder{
		TextView tv_title;
		TextView tv_type;
		TextView tv_desc;
		ImageView iv;
	}
	
	
	
	@Override
	public Object getItem(int position) {

		return null;
	}

	@Override
	public long getItemId(int position) {

		return 0;
	}


}
