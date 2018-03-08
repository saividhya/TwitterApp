package com.twitter.service;


import java.util.List;

import com.twitter.dto.FriendsDTO;
import com.twitter.dto.HopsDTO;
import com.twitter.dto.PopularUsersDTO;
import com.twitter.entity.User;
import com.twitter.exception.FollowException;
import com.twitter.exception.UserNotFoundException;

public interface AnalyticsService {
	public FriendsDTO getFriends(User user) throws UserNotFoundException;
	public List<PopularUsersDTO> getPopularUsers() throws FollowException, UserNotFoundException;
	public HopsDTO getHops(User user,String id)  throws UserNotFoundException, FollowException ;
}
