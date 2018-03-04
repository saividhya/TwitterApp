package com.twitter.dto;

import java.util.List;

import com.twitter.entity.Message;

public class DTOUtility {
	
	public static MessagesDto convertMessageDaoToDto(List<Message> myFeeds,List<Message> followingFeeds){
		MessagesDto messageDto = new MessagesDto();
		messageDto.setMyFeeds(myFeeds);
		messageDto.setFollowingFeeds(followingFeeds);
		return messageDto;
	}

}
