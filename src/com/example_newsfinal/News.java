package com.example_newsfinal;
/*"title": "�첥�������绤�ˣ���������",
"description": "�����������������Ϲ���������Ϊ�Լ����",
"image": "http://192.168.72.42:8080/news/img/a.jpg",
"type": 1,
"comment": 163*/
public class News {
	public String title;
	public String description;
	public String image;
	public int type;
	public int comment;
	@Override
	public String toString() {
		return "News [title=" + title + ", description=" + description
				+ ", image=" + image + ", type=" + type + ", comment="
				+ comment + "]";
	}
	
}
