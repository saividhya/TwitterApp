package com.twitter.entity;

public class Message {
	int id;
	User user;
	String content;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
