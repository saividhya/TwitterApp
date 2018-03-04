package com.twitter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twitter.dao.UserDao;
import com.twitter.entity.User;
import com.twitter.exception.UserNotFoundException;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	UserDao userDao;
	
	@Override
	public User getUserByHandle(String handle) throws UserNotFoundException {
		return userDao.getUserByHandle(handle);
	}

}
