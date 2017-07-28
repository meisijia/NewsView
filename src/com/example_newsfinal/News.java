package com.example_newsfinal;
/*"title": "快播案王欣辩护人：技术无罪",
"description": "称王欣虽曾在央视认过错，但他认为自己无罪。",
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
