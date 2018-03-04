package com.twitter.dto;

import com.twitter.entity.User;

public class PopularUsersDTO {
	User user;
	User popularUser;
	int followersCount;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public User getPopularUser() {
		return popularUser;
	}
	public void setPopularUser(User popularUser) {
		this.popularUser = popularUser;
	}
	public int getFollowersCount() {
		return followersCount;
	}
	public void setFollowersCount(int followersCount) {
		this.followersCount = followersCount;
	}

}
