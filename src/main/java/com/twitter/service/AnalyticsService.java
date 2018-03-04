package com.twitter.service;


import java.util.List;

import com.twitter.dto.FriendsDto;
import com.twitter.dto.PopularUsersDto;
import com.twitter.entity.User;
import com.twitter.exception.FollowException;
import com.twitter.exception.UserNotFoundException;

public interface AnalyticsService {
	public FriendsDto getFriends(User user) throws FollowException;
	public List<PopularUsersDto> getPopularUsers() throws FollowException, UserNotFoundException;
}
