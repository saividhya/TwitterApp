package com.twitter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twitter.dao.UserDao;
import com.twitter.dto.DTOUtility;
import com.twitter.dto.FriendsDto;
import com.twitter.dto.PopularUsersDto;
import com.twitter.entity.User;
import com.twitter.exception.FollowException;
import com.twitter.exception.UserNotFoundException;

@Service
public class AnalyticsServiceImpl implements AnalyticsService{
	@Autowired
	UserDao userDao;
	

	@Override
	public FriendsDto getFriends(User user) throws FollowException {
		List<User> followers = userDao.getFollowers(user);
		List<User> following = userDao.getFollowing(user);
		FriendsDto friendsDto = DTOUtility.convertFriendsDaoToDto(followers, following);
		return friendsDto;
	}


	@Override
	public List<PopularUsersDto> getPopularUsers() throws FollowException, UserNotFoundException {
		return userDao.getPopularUsers();
	}

}
