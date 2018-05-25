package com.example.myapplication.bean;

import java.io.Serializable;
 /**
  * 新闻实体类
  * @author Administrator
  */
public class NewsInfoEnty implements Serializable{
	private static final long serialVersionUID = -7186136320484865225L;
	private String id;
    private String title;
    private String author;
    private String date_add;
    private String content;
    private String clicks;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getDate_add() {
		return date_add;
	}
	public void setDate_add(String date_add) {
		this.date_add = date_add;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getClicks() {
		return clicks;
	}
	public void setClicks(String clicks) {
		this.clicks = clicks;
	}
    
}
