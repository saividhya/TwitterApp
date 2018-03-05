package com.twitter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twitter.dao.UserDao;
import com.twitter.dto.DTOUtility;
import com.twitter.dto.FriendsDTO;
import com.twitter.dto.HopsDTO;
import com.twitter.dto.PopularUsersDTO;
import com.twitter.entity.User;
import com.twitter.exception.FollowException;
import com.twitter.exception.UserNotFoundException;

@Service
public class AnalyticsServiceImpl implements AnalyticsService{
	@Autowired
	UserDao userDao;
	

	@Override
	public FriendsDTO getFriends(User user) throws FollowException {
		List<User> followers = userDao.getFollowers(user);
		List<User> following = userDao.getFollowing(user);
		FriendsDTO friendsDto = DTOUtility.convertFriendsDaoToDto(followers, following);
		return friendsDto;
	}


	@Override
	public List<PopularUsersDTO> getPopularUsers() throws FollowException, UserNotFoundException {
		return userDao.getPopularUsers();
	}


	@Override
	public HopsDTO getHops(User currentUser,String id) throws UserNotFoundException, FollowException {
		User targetUser = userDao.getUserById(id);
		ServiceUtility su = new ServiceUtility();
		int hops = su.getShortestDistance(currentUser,targetUser,userDao);
		HopsDTO hopsDto = new HopsDTO();
		hopsDto.setCurrentUser(currentUser);
		hopsDto.setTargetUser(targetUser);
		hopsDto.setNoOfHops(hops);
		return hopsDto;
	}
}
