package com.twitter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twitter.dao.UserDao;
import com.twitter.entity.User;
import com.twitter.exception.FollowException;
import com.twitter.exception.UnFollowException;
import com.twitter.exception.UserNotFoundException;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	UserDao userDao;
	
	@Override
	public User getUserByHandle(String handle) throws UserNotFoundException {
		return userDao.getUserByHandle(handle);
	}

	@Override
	public List<User> getUsers() throws UserNotFoundException {
		return userDao.getUsers();
	}

	@Override
	public User getUserById(String id) throws UserNotFoundException {
		return userDao.getUserById(id);
	}

	@Override
	public User getUserByName(String name) throws UserNotFoundException {
		return userDao.getUserByName(name);
	}
	
	@Override
	public User getUserByUsername(String name) throws UserNotFoundException {
		return userDao.getUserByUsername(name);
	}
	
	@Override
	public User getUser(String id) throws UserNotFoundException {
		 User user =null;
		 try{
			 int userId = Integer.parseInt(id);
			 user = userDao.getUserById(id);
		 }catch(Exception e){
			 
		 }
		 if(user==null){
			try{
				 user = userDao.getUserByName(id);
			}catch(Exception e){
				 
			 }
		 }
		 if(user==null){
			try{
				 user = userDao.getUserByHandle(id);
			}catch(Exception e){
				 
			 }
		 }
		 if(user==null)
			throw new UserNotFoundException("User not found");
		return user;
	}
	
	@Override
	public void addFollowing(User user,String following_id) throws UserNotFoundException, FollowException {
		User followingUser = getUserById(following_id);
		userDao.addFollowing(user, followingUser);
	}

	@Override
	public void deleteFollowing(User user,String following_id) throws UserNotFoundException, UnFollowException {
		User followingUser = getUserById(following_id);
		userDao.deleteFollowing(user, followingUser);
	}

}
