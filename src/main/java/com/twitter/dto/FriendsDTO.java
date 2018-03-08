package com.twitter.dto;

import java.util.List;


public class FriendsDTO {
	List<UserDTO> followers;
	List<UserDTO> following;
	public List<UserDTO> getFollowers() {
		return followers;
	}
	public void setFollowers(List<UserDTO> followers) {
		this.followers = followers;
	}
	public List<UserDTO> getFollowing() {
		return following;
	}
	public void setFollowing(List<UserDTO> following) {
		this.following = following;
	}
	
	
}
