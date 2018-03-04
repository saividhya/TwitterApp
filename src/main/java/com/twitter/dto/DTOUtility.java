package com.twitter.dto;

import java.util.List;

import com.twitter.entity.Message;
import com.twitter.entity.User;

public class DTOUtility {
	
	public static MessagesDTO convertMessageDaoToDto(List<Message> myFeeds,List<Message> followingFeeds){
		MessagesDTO messageDto = new MessagesDTO();
		messageDto.setMyFeeds(myFeeds);
		messageDto.setFollowingFeeds(followingFeeds);
		return messageDto;
	}
	
	public static FriendsDTO convertFriendsDaoToDto(List<User> followers,List<User> following){
		FriendsDTO friendsDto = new FriendsDTO();
		friendsDto.setFollowers(followers);;
		friendsDto.setFollowing(following);
		return friendsDto;
	}

}
