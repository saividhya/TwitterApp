package com.twitter.dto;

public class PopularUsersDTO {
	UserDTO user;
	UserDTO popularUser;
	int followersCount;
	public UserDTO getUser() {
		return user;
	}
	public void setUser(UserDTO user) {
		this.user = user;
	}
	public UserDTO getPopularUser() {
		return popularUser;
	}
	public void setPopularUser(UserDTO popularUser) {
		this.popularUser = popularUser;
	}
	public int getFollowersCount() {
		return followersCount;
	}
	public void setFollowersCount(int followersCount) {
		this.followersCount = followersCount;
	}

}
