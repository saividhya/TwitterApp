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

import com.twitter.entity.User;
import com.twitter.exception.FollowException;
import com.twitter.exception.UnFollowException;
import com.twitter.exception.UserNotFoundException;
import com.twitter.service.UserService;

@RestController
@RequestMapping("twitter")
public class UserController {
	
	@Autowired
	 UserService userService;
	 
	@RequestMapping(method=RequestMethod.GET, value = "/users")
	public ResponseEntity<?> getUsers() throws UserNotFoundException{	
			List<User> users = userService.getUsers();
			return new ResponseEntity<List<User>>(users,HttpStatus.OK); 
   } 
	
	@RequestMapping(method=RequestMethod.GET, value="/users/{id}")
    public ResponseEntity<?> getUserById(@PathVariable String id) throws UserNotFoundException {
        User user = userService.getUserById(id);
        return new ResponseEntity<User>(user,HttpStatus.OK); 
    }
	
	@RequestMapping(method=RequestMethod.POST, value="/users/{id}/follow")
    public ResponseEntity<?> addFollowing(@PathVariable String id) throws UserNotFoundException, FollowException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String handle = authentication.getName();
		User user = userService.getUserByHandle(handle);
		userService.addFollowing(user,id);
        return new ResponseEntity<String>("Success",HttpStatus.OK); 
    }
	
	@RequestMapping(method=RequestMethod.DELETE, value="/users/{id}/follow")
    public ResponseEntity<?> deleteFollowing(@PathVariable String id) throws UserNotFoundException, UnFollowException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String handle = authentication.getName();
		User user = userService.getUserByHandle(handle);
		userService.deleteFollowing(user,id);
        return new ResponseEntity<String>("Success",HttpStatus.OK); 
    }
	
	
	
}
