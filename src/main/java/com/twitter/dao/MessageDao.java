package com.twitter.dao;

import com.twitter.entity.User;
import com.twitter.exception.UserNotFoundException;

import java.util.List;

import com.twitter.entity.Message;


public interface MessageDao {
	
	List<Message> getMyFeeds (User u) throws UserNotFoundException;
	List<Message> getFollowingFeeds(User u) throws UserNotFoundException;
	List<Message> getMyFeeds(User u,String search) throws UserNotFoundException;
	List<Message> getFollowingFeeds(User u,String search) throws UserNotFoundException;

}
