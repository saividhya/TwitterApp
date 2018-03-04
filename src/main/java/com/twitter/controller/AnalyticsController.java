package com.twitter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.twitter.dto.FriendsDTO;
import com.twitter.dto.HopsDTO;
import com.twitter.dto.PopularUsersDTO;
import com.twitter.entity.User;
import com.twitter.exception.FollowException;
import com.twitter.exception.UserNotFoundException;
import com.twitter.service.AnalyticsService;
import com.twitter.service.UserService;

@RestController
@RequestMapping("twitter")
public class AnalyticsController {

	@Autowired
	AnalyticsService analyticsService;
	
	@Autowired
	UserService userService;
	
	@RequestMapping(method=RequestMethod.GET, value = "/friends")
	public ResponseEntity<?> getFriends() throws FollowException, UserNotFoundException{	
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String handle = authentication.getName();
		User user = userService.getUserByHandle(handle);
		FriendsDTO friendsDto = analyticsService.getFriends(user);
        return new ResponseEntity<FriendsDTO>(friendsDto,HttpStatus.OK); 
	}
	
	@RequestMapping(method=RequestMethod.GET, value = "/popularUsers")
	public ResponseEntity<?> getPopularUsers() throws FollowException, UserNotFoundException{	
		List<PopularUsersDTO> popularUsersDto = analyticsService.getPopularUsers();
        return new ResponseEntity<List<PopularUsersDTO>>(popularUsersDto,HttpStatus.OK); 
	}
	
	@RequestMapping(method=RequestMethod.GET, value = "/hops/{id}")
	public ResponseEntity<?> getMinHops(@PathVariable String id) throws FollowException, UserNotFoundException{	
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String handle = authentication.getName();
		User user = userService.getUserByHandle(handle);
		HopsDTO hopsDto = analyticsService.getHops(user,id);
        return new ResponseEntity<HopsDTO>(hopsDto,HttpStatus.OK); 
	}
	
}
