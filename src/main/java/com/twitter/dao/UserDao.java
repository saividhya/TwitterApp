package com.twitter.dao;

import java.util.List;

import com.twitter.entity.User;
import com.twitter.exception.UserNotFoundException;

public interface UserDao {

	List<User> getUsers();
	User getUserById(String id);
	User getUserByName(String name);
	User getUserByHandle(String handle) throws UserNotFoundException;
	List<User> getFollowers(User u);
	List<User> getFollowing(User u);
	//List<PopularUser> getPopularUsers();
	void addFollowing(User currentUser, User targetUser);
	void deleteFollowing(User currentUser, User targetUser);
	
}
