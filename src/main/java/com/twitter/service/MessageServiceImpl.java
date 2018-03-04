package com.twitter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twitter.dao.MessageDao;
import com.twitter.dto.DTOUtility;
import com.twitter.dto.MessagesDto;
import com.twitter.entity.Message;
import com.twitter.entity.User;
import com.twitter.exception.UserNotFoundException;

@Service
public class MessageServiceImpl implements MessageService{
	@Autowired
	MessageDao messageDao;
	
	@Override
	public MessagesDto getMessages(User user) throws UserNotFoundException {
		List<Message> myFeeds = messageDao.getMyFeeds(user);
		List<Message> followingFeeds =messageDao.getFollowingFeeds(user);
		MessagesDto messagesDto = DTOUtility.convertMessageDaoToDto(myFeeds, followingFeeds);
		return messagesDto;
	}

	@Override
	public MessagesDto getMessages(User user, String search) throws UserNotFoundException {
		List<Message> myFeeds = messageDao.getMyFeeds(user,search);
		List<Message> followingFeeds =messageDao.getFollowingFeeds(user,search);
		MessagesDto messagesDto = DTOUtility.convertMessageDaoToDto(myFeeds, followingFeeds);
		return messagesDto;
	}

}
