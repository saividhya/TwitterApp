package com.twitter.dto;

import java.util.List;

import com.twitter.entity.User;


public class FriendsDTO {
	List<User> followers;
	List<User> following;
	public List<User> getFollowers() {
		return followers;
	}
	public void setFollowers(List<User> followers) {
		this.followers = followers;
	}
	public List<User> getFollowing() {
		return following;
	}
	public void setFollowing(List<User> following) {
		this.following = following;
	}
	
	
}
