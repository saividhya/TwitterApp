package com.twitter.dto;

import java.util.List;

public class MessagesDTO {
	List<MessageDTO> myFeeds;
	List<MessageDTO> followingFeeds;
	public List<MessageDTO> getMyFeeds() {
		return myFeeds;
	}
	public void setMyFeeds(List<MessageDTO> myFeeds) {
		this.myFeeds = myFeeds;
	}
	public List<MessageDTO> getFollowingFeeds() {
		return followingFeeds;
	}
	public void setFollowingFeeds(List<MessageDTO> followingFeeds) {
		this.followingFeeds = followingFeeds;
	}
	
	
}
