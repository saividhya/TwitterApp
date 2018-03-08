package com.twitter.dto;

public class MessageDTO {
	int id;
	UserDTO user;
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
	public UserDTO getUser() {
		return user;
	}
	public void setUser(UserDTO user) {
		this.user = user;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}

