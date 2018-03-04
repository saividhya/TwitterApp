package com.twitter.dto;

import java.util.List;

import com.twitter.entity.Message;
import com.twitter.entity.User;

public class DTOUtility {
	
	public static MessagesDto convertMessageDaoToDto(List<Message> myFeeds,List<Message> followingFeeds){
		MessagesDto messageDto = new MessagesDto();
		messageDto.setMyFeeds(myFeeds);
		messageDto.setFollowingFeeds(followingFeeds);
		return messageDto;
	}
	
	public static FriendsDto convertFriendsDaoToDto(List<User> followers,List<User> following){
		FriendsDto friendsDto = new FriendsDto();
		friendsDto.setFollowers(followers);;
		friendsDto.setFollowing(following);
		return friendsDto;
	}

}
