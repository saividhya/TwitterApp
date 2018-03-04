package com.twitter.dao;

import java.util.List;

import com.twitter.dto.PopularUsersDto;
import com.twitter.entity.User;
import com.twitter.exception.FollowException;
import com.twitter.exception.UnFollowException;
import com.twitter.exception.UserNotFoundException;

public interface UserDao {

	List<User> getUsers() throws UserNotFoundException;
	User getUserById(String id) throws UserNotFoundException;
	User getUserByName(String name) throws UserNotFoundException;
	User getUserByHandle(String handle) throws UserNotFoundException;
	List<User> getFollowers(User u) throws FollowException;
	List<User> getFollowing(User u) throws FollowException;
	List<PopularUsersDto> getPopularUsers()  throws UserNotFoundException ;
	void addFollowing(User currentUser, User targetUser) throws FollowException;
	void deleteFollowing(User currentUser, User targetUser) throws UnFollowException;
	
}
