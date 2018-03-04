package com.twitter.service;


import com.twitter.dto.MessagesDto;
import com.twitter.entity.User;
import com.twitter.exception.UserNotFoundException;

public interface MessageService {
	public MessagesDto getMessages(User user) throws UserNotFoundException;
	public MessagesDto getMessages(User user, String search) throws UserNotFoundException;
}
