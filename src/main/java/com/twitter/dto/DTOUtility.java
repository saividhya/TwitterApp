package com.twitter.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.modelmapper.ModelMapper;

import com.twitter.dao.PopularUsersDAO;
import com.twitter.entity.Message;
import com.twitter.entity.User;

public class DTOUtility {
	
	public static <D, T> List<D> mapAll(final Collection<T> entityList, Class<D> outCLass) {
		ModelMapper modelMapper = new ModelMapper();
		List<D> entityDTO = new ArrayList<D>();
		for(T entity:entityList){
			entityDTO.add(modelMapper.map(entity, outCLass));
		}
		return entityDTO;
    }
	
	
	public static <D, T> D map(T entity, Class<D> outCLass) {
		ModelMapper modelMapper = new ModelMapper();
		D entityDTO = modelMapper.map(entity, outCLass);
		return entityDTO;
    }
	
	public static MessagesDTO convertMessageDaoToDto(List<Message> myFeeds,List<Message> followingFeeds){
		MessagesDTO messageDto = new MessagesDTO();
		List<MessageDTO> myFeedsDTO = DTOUtility.mapAll(myFeeds, MessageDTO.class);
		List<MessageDTO> followingFeedsDTO = DTOUtility.mapAll(followingFeeds, MessageDTO.class);
		
		messageDto.setMyFeeds(myFeedsDTO);
		messageDto.setFollowingFeeds(followingFeedsDTO);
		return messageDto;
	}
	
	public static FriendsDTO convertFriendsDaoToDto(List<User> followers,List<User> following){
		FriendsDTO friendsDto = new FriendsDTO();
		List<UserDTO> followersDTO = DTOUtility.mapAll(followers, UserDTO.class);
		List<UserDTO> followingDTO = DTOUtility.mapAll(following, UserDTO.class);
		friendsDto.setFollowers(followersDTO);;
		friendsDto.setFollowing(followingDTO);
		return friendsDto;
	}
	
	public static final List<PopularUsersDTO> convertPopularUsersDaoToDto(List<PopularUsersDAO> popularUserDao){
		List<PopularUsersDTO> popularUsersDTO = DTOUtility.mapAll(popularUserDao, PopularUsersDTO.class); 
		return popularUsersDTO;
	}

}
