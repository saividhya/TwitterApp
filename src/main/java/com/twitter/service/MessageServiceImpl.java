package com.twitter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twitter.dao.MessageDao;
import com.twitter.dto.DTOUtility;
import com.twitter.dto.MessagesDTO;
import com.twitter.entity.Message;
import com.twitter.entity.User;
import com.twitter.exception.UserNotFoundException;

@Service
public class MessageServiceImpl implements MessageService{
	@Autowired
	MessageDao messageDao;
	
	@Override
	public MessagesDTO getMessages(User user) throws UserNotFoundException {
		List<Message> myFeeds = messageDao.getMyFeeds(user);
		List<Message> followingFeeds =messageDao.getFollowingFeeds(user);
		MessagesDTO messagesDto = DTOUtility.convertMessageDaoToDto(myFeeds, followingFeeds);
		return messagesDto;
	}

	@Override
	public MessagesDTO getMessages(User user, String search) throws UserNotFoundException {
		List<Message> myFeeds = messageDao.getMyFeeds(user,search);
		List<Message> followingFeeds =messageDao.getFollowingFeeds(user,search);
		MessagesDTO messagesDto = DTOUtility.convertMessageDaoToDto(myFeeds, followingFeeds);
		return messagesDto;
	}

}
