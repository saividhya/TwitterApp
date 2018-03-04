package com.twitter.dto;

import java.util.List;

import com.twitter.entity.Message;

public class MessagesDTO {
	List<Message> myFeeds;
	List<Message> followingFeeds;
	public List<Message> getMyFeeds() {
		return myFeeds;
	}
	public void setMyFeeds(List<Message> myFeeds) {
		this.myFeeds = myFeeds;
	}
	public List<Message> getFollowingFeeds() {
		return followingFeeds;
	}
	public void setFollowingFeeds(List<Message> followingFeeds) {
		this.followingFeeds = followingFeeds;
	}
	
	
}
