package com.twitter.service;

import java.util.List;

import com.twitter.dto.UserDTO;
import com.twitter.entity.User;
import com.twitter.exception.FollowException;
import com.twitter.exception.UnFollowException;
import com.twitter.exception.UserNotFoundException;

public interface UserService {
	public List<UserDTO> getUsers() throws UserNotFoundException;
	public UserDTO getUser(String id) throws UserNotFoundException;
	public User getUserById(String id) throws UserNotFoundException;
	public User getUserByUsername(String name) throws UserNotFoundException;
	public User getUserByName(String name) throws UserNotFoundException;
	public User getUserByHandle(String handle) throws UserNotFoundException;
	void addFollowing(User user,String following_id) throws UserNotFoundException, FollowException;
	void deleteFollowing(User user,String following_id) throws UserNotFoundException, UnFollowException;
}
