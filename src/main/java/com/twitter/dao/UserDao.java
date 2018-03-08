package com.twitter.dao;

import java.util.List;
import com.twitter.entity.User;
import com.twitter.exception.FollowException;
import com.twitter.exception.UnFollowException;
import com.twitter.exception.UserNotFoundException;

public interface UserDao {

	List<User> getUsers() throws UserNotFoundException;
	User getUserById(String id) throws UserNotFoundException;
	User getUserByUsername(String name) throws UserNotFoundException;
	User getUserByName(String name) throws UserNotFoundException;
	User getUserByHandle(String handle) throws UserNotFoundException;
	List<User> getFollowers(User user) throws UserNotFoundException;
	List<User> getFollowing(User user) throws UserNotFoundException;
	List<PopularUsersDAO> getPopularUsers()  throws UserNotFoundException;
	boolean checkFollowing(User currentUser, User targetUser);
	void addFollowing(User currentUser, User targetUser) throws FollowException;
	void deleteFollowing(User currentUser, User targetUser) throws UnFollowException;
	
}
