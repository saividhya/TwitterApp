package com.twitter.test;

import java.util.ArrayList;
import java.util.List;

import com.twitter.dto.FriendsDTO;
import com.twitter.dto.HopsDTO;
import com.twitter.dto.MessageDTO;
import com.twitter.dto.MessagesDTO;
import com.twitter.dto.PopularUsersDTO;
import com.twitter.dto.UserDTO;
import com.twitter.entity.Message;
import com.twitter.entity.User;

public class ExpectedResultBuilder {
	
	public static List<Message> getMyFeeds(){
		User expectedUser = new User();
		expectedUser.setId(12);
		expectedUser.setHandle("phoenix");
		expectedUser.setName("Jean Grey");
		Message m1 = new Message();
		m1.setUser(expectedUser);
		m1.setContent("I am fire and life incarnate! Now and forever — I am Phoenix!");
		Message m2 = new Message();
		m2.setUser(expectedUser);
		m2.setContent("Finish me with your claws. I beg you. I do not want to HURT YOU!!");
		List<Message> result = new ArrayList<Message>();
		result.add(0,m1);
		result.add(1,m2);
		return result;
	}
	
	public static List<Message> getMyFeedsWithSearch(){
		User expectedUser = new User();
		expectedUser.setId(13);
		expectedUser.setHandle("wolverine");
		expectedUser.setName("Logan");
		Message m1 = new Message();
		m1.setUser(expectedUser);
		m1.setContent("Do I look like a man who exaggerates?");
		Message m2 = new Message();
		m2.setUser(expectedUser);
		m2.setContent("That looks like the creature that ate Fred Dukes!");
		List<Message> result = new ArrayList<Message>();
		result.add(0,m1);
		result.add(1,m2);
		return result;
	}
	
	public static List<Message> getFollowingFeedsWithSearch(){
		User expectedUser = new User();
		expectedUser.setId(12);
		expectedUser.setHandle("phoenix");
		expectedUser.setName("Jean Grey");
		Message m1 = new Message();
		m1.setUser(expectedUser);
		m1.setContent("Finish me with your claws. I beg you. I do not want to HURT YOU!!");
		List<Message> result = new ArrayList<Message>();
		result.add(0,m1);
		return result;
	}

	public static List<Message> getFollowingFeeds(){
		User expectedUser = new User();
		expectedUser.setId(12);
		expectedUser.setHandle("phoenix");
		expectedUser.setName("Jean Grey");
		Message m1 = new Message();
		m1.setUser(expectedUser);
		m1.setContent("I am fire and life incarnate! Now and forever — I am Phoenix!");
		Message m2 = new Message();
		m2.setUser(expectedUser);
		m2.setContent("Finish me with your claws. I beg you. I do not want to HURT YOU!!");
		List<Message> result = new ArrayList<Message>();
		result.add(0,m1);
		result.add(1,m2);
		return result;
	}
	public static User getValidUser(){
		User expectedUser = new User();
		expectedUser.setId(12);
		expectedUser.setHandle("phoenix");
		expectedUser.setName("Jean Grey");
		return expectedUser;
	}
	
	public static List<User> getFollowersValid(){
		User expectedUser = new User();
		expectedUser.setId(13);
		expectedUser.setHandle("wolverine");
		expectedUser.setName("Logan");
		List<User> result = new ArrayList<User>();
		result.add(expectedUser);
		return result;		
	}

	public static List<User> getFollowingValid(){
		User expectedUser = new User();
		expectedUser.setId(12);
		expectedUser.setHandle("phoenix");
		expectedUser.setName("Jean Grey");
		List<User> result = new ArrayList<User>();
		result.add(expectedUser);
		return result;
		
	}
	
	public static List<PopularUsersDTO> getPopularUsers(){
		List<PopularUsersDTO> popualarUsers = new ArrayList<PopularUsersDTO>();
		
		UserDTO user1 = new UserDTO();
		user1.setId(1);
		user1.setName("Bruce Wayne");
		user1.setHandle("batman");
		
		UserDTO user2 = new UserDTO();
		user2.setId(2);
		user2.setName("Clark Kent");
		user2.setHandle("superman");
		
		UserDTO user3 = new UserDTO();
		user3.setId(3);
		user3.setName("Selina Kyle");
		user3.setHandle("catwoman");
		
		UserDTO user4 = new UserDTO();
		user4.setId(4);
		user4.setName("Matt Murdock");
		user4.setHandle("daredevil");
		
		UserDTO user5 = new UserDTO();
		user5.setId(5);
		user5.setName("Alfred Pennyworth");
		user5.setHandle("alfred");
		
		UserDTO user6 = new UserDTO();
		user6.setId(6);
		user6.setName("Otto Octavius");
		user6.setHandle("dococ");
		
		UserDTO user7 = new UserDTO();
		user7.setId(7);
		user7.setName("Dru-Zod");
		user7.setHandle("zod");
		
		UserDTO user8 = new UserDTO();
		user8.setId(8);
		user8.setName("Peter Parker");
		user8.setHandle("spiderman");
		
		UserDTO user9 = new UserDTO();
		user9.setId(9);
		user9.setName("Tony Stark");
		user9.setHandle("ironman");
		
		UserDTO user10 = new UserDTO();
		user10.setId(10);
		user10.setName("Charles Xavier");
		user10.setHandle("profx");
		
		UserDTO user11 = new UserDTO();
		user11.setId(11);
		user11.setName("Barry Allen");
		user11.setHandle("flash");
		
		UserDTO user12 = new UserDTO();
		user12.setId(12);
		user12.setName("Jean Grey");
		user12.setHandle("phoenix");
		
		
		PopularUsersDTO popularUser1 = new PopularUsersDTO();
		popularUser1.setUser(user1);
		popularUser1.setPopularUser(user5);
		popularUser1.setFollowersCount(6);
		
		PopularUsersDTO popularUser2 = new PopularUsersDTO();
		popularUser2.setUser(user1);
		popularUser2.setPopularUser(user8);
		popularUser2.setFollowersCount(6);
		
		PopularUsersDTO popularUser3 = new PopularUsersDTO();
		popularUser3.setUser(user2);
		popularUser3.setPopularUser(user1);
		popularUser3.setFollowersCount(7);
		
		PopularUsersDTO popularUser4 = new PopularUsersDTO();
		popularUser4.setUser(user2);
		popularUser4.setPopularUser(user7);
		popularUser4.setFollowersCount(7);
		
		PopularUsersDTO popularUser5 = new PopularUsersDTO();
		popularUser5.setUser(user3);
		popularUser5.setPopularUser(user7);
		popularUser5.setFollowersCount(7);
		
		PopularUsersDTO popularUser6 = new PopularUsersDTO();
		popularUser6.setUser(user4);
		popularUser6.setPopularUser(user1);
		popularUser6.setFollowersCount(7);
		
		PopularUsersDTO popularUser7 = new PopularUsersDTO();
		popularUser7.setUser(user4);
		popularUser7.setPopularUser(user7);
		popularUser7.setFollowersCount(7);
		
		PopularUsersDTO popularUser8 = new PopularUsersDTO();
		popularUser8.setUser(user5);
		popularUser8.setPopularUser(user1);
		popularUser8.setFollowersCount(7);
		
		
		PopularUsersDTO popularUser9 = new PopularUsersDTO();
		popularUser9.setUser(user5);
		popularUser9.setPopularUser(user7);
		popularUser9.setFollowersCount(7);
		
		PopularUsersDTO popularUser10 = new PopularUsersDTO();
		popularUser10.setUser(user6);
		popularUser10.setPopularUser(user7);
		popularUser10.setFollowersCount(7);
		
		PopularUsersDTO popularUser11 = new PopularUsersDTO();
		popularUser11.setUser(user7);
		popularUser11.setPopularUser(user2);
		popularUser11.setFollowersCount(6);
		
		PopularUsersDTO popularUser12 = new PopularUsersDTO();
		popularUser12.setUser(user7);
		popularUser12.setPopularUser(user4);
		popularUser12.setFollowersCount(6);
		
		PopularUsersDTO popularUser13 = new PopularUsersDTO();
		popularUser13.setUser(user7);
		popularUser13.setPopularUser(user5);
		popularUser13.setFollowersCount(6);
		
		PopularUsersDTO popularUser14 = new PopularUsersDTO();
		popularUser14.setUser(user7);
		popularUser14.setPopularUser(user8);
		popularUser14.setFollowersCount(6);
		
		PopularUsersDTO popularUser15 = new PopularUsersDTO();
		popularUser15.setUser(user8);
		popularUser15.setPopularUser(user1);
		popularUser15.setFollowersCount(7);
		
		PopularUsersDTO popularUser16 = new PopularUsersDTO();
		popularUser16.setUser(user9);
		popularUser16.setPopularUser(user7);
		popularUser16.setFollowersCount(7);
		
		PopularUsersDTO popularUser17 = new PopularUsersDTO();
		popularUser17.setUser(user10);
		popularUser17.setPopularUser(user1);
		popularUser17.setFollowersCount(7);
		
		PopularUsersDTO popularUser18 = new PopularUsersDTO();
		popularUser18.setUser(user11);
		popularUser18.setPopularUser(user12);
		popularUser18.setFollowersCount(1);
		
		
		popualarUsers.add(0,popularUser1);
		popualarUsers.add(1,popularUser2);
		popualarUsers.add(2,popularUser3);
		popualarUsers.add(3,popularUser4);
		popualarUsers.add(4,popularUser5);
		popualarUsers.add(5,popularUser6);
		popualarUsers.add(6,popularUser7);
		popualarUsers.add(7,popularUser8);
		popualarUsers.add(8,popularUser9);
		popualarUsers.add(9,popularUser10);
		popualarUsers.add(10,popularUser11);
		popualarUsers.add(11,popularUser12);
		popualarUsers.add(12,popularUser13);
		popualarUsers.add(13,popularUser14);
		popualarUsers.add(14,popularUser15);
		popualarUsers.add(15,popularUser16);
		popualarUsers.add(16,popularUser17);
		popualarUsers.add(17,popularUser18);
		return popualarUsers;
	}
	
	public static MessagesDTO getMessages(){
		 
		UserDTO expectedUser = new UserDTO();
		expectedUser.setId(12);
		expectedUser.setHandle("phoenix");
		expectedUser.setName("Jean Grey");
		MessageDTO m1 = new MessageDTO();
		m1.setUser(expectedUser);
		m1.setContent("I am fire and life incarnate! Now and forever — I am Phoenix!");
		MessageDTO m2 = new MessageDTO();
		m2.setUser(expectedUser);
		m2.setContent("Finish me with your claws. I beg you. I do not want to HURT YOU!!");
		List<MessageDTO> myFeeds = new ArrayList<MessageDTO>();
		myFeeds.add(0,m1);
		myFeeds.add(1,m2);
		
		UserDTO expectedUser1 = new UserDTO();
		expectedUser1.setId(11);
		expectedUser1.setHandle("flash");
		expectedUser1.setName("Barry Allen");
		MessageDTO m3 = new MessageDTO();
		m3.setUser(expectedUser1);
		m3.setContent("Sometimes great possibilities are right in front of us but we do not see them because we choose not to. I think that we need to be open to exploring something new");
		MessageDTO m4 = new MessageDTO();
		m4.setUser(expectedUser1);
		m4.setContent("Today I do not want to think, I just want to run.");
		List<MessageDTO> followingFeeds = new ArrayList<MessageDTO>();
		followingFeeds.add(0,m3);
		followingFeeds.add(1,m4);
		
		MessagesDTO result = new MessagesDTO();
		result.setMyFeeds(myFeeds);
		result.setFollowingFeeds(followingFeeds);
		return result;
		
	}
	
	public static MessagesDTO getMessagesWithSearch(){
		 
		UserDTO expectedUser = new UserDTO();
		expectedUser.setId(12);
		expectedUser.setHandle("phoenix");
		expectedUser.setName("Jean Grey");
		MessageDTO m1 = new MessageDTO();
		m1.setUser(expectedUser);
		m1.setContent("Finish me with your claws. I beg you. I do not want to HURT YOU!!");
		List<MessageDTO> myFeeds = new ArrayList<MessageDTO>();
		myFeeds.add(0,m1);
		
		UserDTO expectedUser1 = new UserDTO();
		expectedUser1.setId(11);
		expectedUser1.setHandle("flash");
		expectedUser1.setName("Barry Allen");
		MessageDTO m3 = new MessageDTO();
		m3.setUser(expectedUser1);
		m3.setContent("Sometimes great possibilities are right in front of us but we do not see them because we choose not to. I think that we need to be open to exploring something new");
		MessageDTO m4 = new MessageDTO();
		m4.setUser(expectedUser1);
		m4.setContent("Today I do not want to think, I just want to run.");
		List<MessageDTO> followingFeeds = new ArrayList<MessageDTO>();
		followingFeeds.add(0,m3);
		followingFeeds.add(1,m4);
		
		MessagesDTO result = new MessagesDTO();
		result.setMyFeeds(myFeeds);
		result.setFollowingFeeds(followingFeeds);
		return result;
		
	}
	
	public static FriendsDTO getFriends(){
		FriendsDTO result = new FriendsDTO();
		
		List<UserDTO> followers = new ArrayList<UserDTO>();
		List<UserDTO> following = new ArrayList<UserDTO>();
		
		UserDTO user13 = new UserDTO();
		user13.setId(13);
		user13.setHandle("wolverine");
		user13.setName("Logan");
		
		UserDTO user11 = new UserDTO();
		user11.setId(11);
		user11.setName("Barry Allen");
		user11.setHandle("flash");
		
		followers.add(user13);
		following.add(user11);
		
		result.setFollowers(followers);
		result.setFollowing(following);
		
		return result;
	}
	
	public static HopsDTO getHops(){
		
		UserDTO user1 = new UserDTO();
		user1.setId(1);
		user1.setName("Bruce Wayne");
		user1.setHandle("batman");
		
		UserDTO user5 = new UserDTO();
		user5.setId(5);
		user5.setName("Alfred Pennyworth");
		user5.setHandle("alfred");
		
		HopsDTO result = new HopsDTO();
		result.setCurrentUser(user1);
		result.setTargetUser(user5);
		result.setNoOfHops(1);
		
		return result;
	}

	public static HopsDTO getHopsNoHops(){
		
		UserDTO user1 = new UserDTO();
		user1.setId(1);
		user1.setName("Bruce Wayne");
		user1.setHandle("batman");
		
		UserDTO user2 = new UserDTO();
		user2.setId(14);
		user2.setHandle("deathstroke");
		user2.setName("Slade Wilson");
		
		HopsDTO result = new HopsDTO();
		result.setCurrentUser(user1);
		result.setTargetUser(user2);
		result.setNoOfHops(0);
		
		return result;
	}
	
	public static HopsDTO getHopsSelf(){
		
		UserDTO user1 = new UserDTO();
		user1.setId(1);
		user1.setName("Bruce Wayne");
		user1.setHandle("batman");
		
		UserDTO user2 = new UserDTO();
		user2.setId(1);
		user2.setName("Bruce Wayne");
		user2.setHandle("batman");
		
		HopsDTO result = new HopsDTO();
		result.setCurrentUser(user1);
		result.setTargetUser(user2);
		result.setNoOfHops(0);
		
		return result;
	}
	
	
}
