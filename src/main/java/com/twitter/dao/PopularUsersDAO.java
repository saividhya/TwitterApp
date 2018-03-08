package com.twitter.dao;
import com.twitter.entity.User;

public class PopularUsersDAO {
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
