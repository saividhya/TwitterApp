package com.twitter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.twitter.dto.MessagesDto;
import com.twitter.entity.User;
import com.twitter.exception.UserNotFoundException;
import com.twitter.service.MessageService;
import com.twitter.service.UserService;


@RestController
@RequestMapping("twitter")
public class MessageController {
	@Autowired
	 MessageService messageService;
	
	@Autowired
	 UserService userService;
	 
	@RequestMapping(method=RequestMethod.GET, value="/messages")
	public ResponseEntity<?> getMessages(@RequestParam(value = "search", required = false) String searchQuery) throws UserNotFoundException{	
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String handle = authentication.getName();
			User user = userService.getUserByHandle(handle);
			MessagesDto messagesDto = messageService.getMessages(user,searchQuery);
	        return new ResponseEntity<MessagesDto>(messagesDto,HttpStatus.OK); 
    } 
}
