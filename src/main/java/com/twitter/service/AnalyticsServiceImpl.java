package com.twitter.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twitter.dao.PopularUsersDAO;
import com.twitter.dao.UserDao;
import com.twitter.dto.DTOUtility;
import com.twitter.dto.FriendsDTO;
import com.twitter.dto.HopsDTO;
import com.twitter.dto.PopularUsersDTO;
import com.twitter.dto.UserDTO;
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
		List<PopularUsersDAO> popularUserDao = userDao.getPopularUsers();
		List<PopularUsersDTO> popularUserDto = DTOUtility.convertPopularUsersDaoToDto(popularUserDao);
		return popularUserDto;
	}


	@Override
	public HopsDTO getHops(User currentUser,String id) throws UserNotFoundException, FollowException {
		User targetUser = userDao.getUserById(id);
		ServiceUtility serviceUtility = new ServiceUtility();
		int hops = serviceUtility.getShortestDistance(currentUser,targetUser,userDao);
		UserDTO currentUserDTO = DTOUtility.map(currentUser, UserDTO.class); 
		UserDTO targetUserDTO =  DTOUtility.map(targetUser, UserDTO.class);  
		HopsDTO hopsDto = new HopsDTO();
		hopsDto.setCurrentUser(currentUserDTO);
		hopsDto.setTargetUser(targetUserDTO);
		hopsDto.setNoOfHops(hops);
		return hopsDto;
	}
}
