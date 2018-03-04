package com.twitter.service;

import com.twitter.entity.User;
import com.twitter.exception.UserNotFoundException;

public interface UserService {
	public User getUserByHandle(String handle) throws UserNotFoundException;
}
