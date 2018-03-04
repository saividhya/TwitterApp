package com.twitter.service;


import com.twitter.dto.MessagesDTO;
import com.twitter.entity.User;
import com.twitter.exception.UserNotFoundException;

public interface MessageService {
	public MessagesDTO getMessages(User user) throws UserNotFoundException;
	public MessagesDTO getMessages(User user, String search) throws UserNotFoundException;
}
