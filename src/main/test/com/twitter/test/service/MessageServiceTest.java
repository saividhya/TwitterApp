package com.twitter.test.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.twitter.ChallengeApplication;
import com.twitter.dto.MessagesDTO;
import com.twitter.entity.Message;
import com.twitter.entity.User;
import com.twitter.exception.UserNotFoundException;
import com.twitter.service.MessageService;
import com.twitter.test.ExpectedResultBuilder;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ChallengeApplication.class)
@ComponentScan

public class MessageServiceTest {

	@Autowired
	MessageService messsageService;
	
	@Test
	public void testMyFeedsWithValidUser(){
		User testUser = new User();
		testUser.setId(12);
		testUser.setHandle("phoenix");
		testUser.setName("Jean Grey");
		try {
			MessagesDTO actualResult = messsageService.getMessages(testUser);
			MessagesDTO expectedResult = ExpectedResultBuilder.getMessages();
			
			assertEquals(expectedResult.getMyFeeds().size(),actualResult.getMyFeeds().size());
			for(int i=0;i < actualResult.getMyFeeds().size();i++){
				assertEquals(expectedResult.getMyFeeds().get(i).getUser().getId(), actualResult.getMyFeeds().get(i).getUser().getId());
				assertEquals(expectedResult.getMyFeeds().get(i).getUser().getHandle(), actualResult.getMyFeeds().get(i).getUser().getHandle());
				assertEquals(expectedResult.getMyFeeds().get(i).getUser().getName(), actualResult.getMyFeeds().get(i).getUser().getName());
				assertEquals(expectedResult.getMyFeeds().get(i).getContent(), actualResult.getMyFeeds().get(i).getContent());
			}
			
			assertEquals(expectedResult.getFollowingFeeds().size(),actualResult.getFollowingFeeds().size());
			for(int i=0;i < actualResult.getFollowingFeeds().size();i++){
				assertEquals(expectedResult.getFollowingFeeds().get(i).getUser().getId(), actualResult.getFollowingFeeds().get(i).getUser().getId());
				assertEquals(expectedResult.getFollowingFeeds().get(i).getUser().getHandle(), actualResult.getFollowingFeeds().get(i).getUser().getHandle());
				assertEquals(expectedResult.getFollowingFeeds().get(i).getUser().getName(), actualResult.getFollowingFeeds().get(i).getUser().getName());
				assertEquals(expectedResult.getFollowingFeeds().get(i).getContent(), actualResult.getFollowingFeeds().get(i).getContent());
			}
			
		} catch (UserNotFoundException e) {
			System.out.println("test failed");
		}
		
	}
	
	@Test
	public void testMyFeedsWithValidUserAndSearch(){
		User testUser = new User();
		testUser.setId(12);
		testUser.setHandle("phoenix");
		testUser.setName("Jean Grey");
		try {
			String search = "do";
			MessagesDTO actualResult = messsageService.getMessages(testUser,search);
			MessagesDTO expectedResult = ExpectedResultBuilder.getMessagesWithSearch();
			
			assertEquals(expectedResult.getMyFeeds().size(),actualResult.getMyFeeds().size());
			for(int i=0;i < actualResult.getMyFeeds().size();i++){
				assertEquals(expectedResult.getMyFeeds().get(i).getUser().getId(), actualResult.getMyFeeds().get(i).getUser().getId());
				assertEquals(expectedResult.getMyFeeds().get(i).getUser().getHandle(), actualResult.getMyFeeds().get(i).getUser().getHandle());
				assertEquals(expectedResult.getMyFeeds().get(i).getUser().getName(), actualResult.getMyFeeds().get(i).getUser().getName());
				assertEquals(expectedResult.getMyFeeds().get(i).getContent(), actualResult.getMyFeeds().get(i).getContent());
			}
			
			assertEquals(expectedResult.getFollowingFeeds().size(),actualResult.getFollowingFeeds().size());
			for(int i=0;i < actualResult.getFollowingFeeds().size();i++){
				assertEquals(expectedResult.getFollowingFeeds().get(i).getUser().getId(), actualResult.getFollowingFeeds().get(i).getUser().getId());
				assertEquals(expectedResult.getFollowingFeeds().get(i).getUser().getHandle(), actualResult.getFollowingFeeds().get(i).getUser().getHandle());
				assertEquals(expectedResult.getFollowingFeeds().get(i).getUser().getName(), actualResult.getFollowingFeeds().get(i).getUser().getName());
				assertEquals(expectedResult.getFollowingFeeds().get(i).getContent(), actualResult.getFollowingFeeds().get(i).getContent());
			}
			
		} catch (UserNotFoundException e) {
			System.out.println("test failed");
		}
		
	}

	@Test
	public void testMyFeedsWithValidUserAndNoSearch(){
		User testUser = new User();
		testUser.setId(12);
		testUser.setHandle("phoenix");
		testUser.setName("Jean Grey");
		try {
			String search = "";
			MessagesDTO actualResult = messsageService.getMessages(testUser,search);
			MessagesDTO expectedResult = ExpectedResultBuilder.getMessages();
			
			assertEquals(expectedResult.getMyFeeds().size(),actualResult.getMyFeeds().size());
			for(int i=0;i < actualResult.getMyFeeds().size();i++){
				assertEquals(expectedResult.getMyFeeds().get(i).getUser().getId(), actualResult.getMyFeeds().get(i).getUser().getId());
				assertEquals(expectedResult.getMyFeeds().get(i).getUser().getHandle(), actualResult.getMyFeeds().get(i).getUser().getHandle());
				assertEquals(expectedResult.getMyFeeds().get(i).getUser().getName(), actualResult.getMyFeeds().get(i).getUser().getName());
				assertEquals(expectedResult.getMyFeeds().get(i).getContent(), actualResult.getMyFeeds().get(i).getContent());
			}
			
			assertEquals(expectedResult.getFollowingFeeds().size(),actualResult.getFollowingFeeds().size());
			for(int i=0;i < actualResult.getFollowingFeeds().size();i++){
				assertEquals(expectedResult.getFollowingFeeds().get(i).getUser().getId(), actualResult.getFollowingFeeds().get(i).getUser().getId());
				assertEquals(expectedResult.getFollowingFeeds().get(i).getUser().getHandle(), actualResult.getFollowingFeeds().get(i).getUser().getHandle());
				assertEquals(expectedResult.getFollowingFeeds().get(i).getUser().getName(), actualResult.getFollowingFeeds().get(i).getUser().getName());
				assertEquals(expectedResult.getFollowingFeeds().get(i).getContent(), actualResult.getFollowingFeeds().get(i).getContent());
			}
			
		} catch (UserNotFoundException e) {
			System.out.println("test failed");
		}
		
	}

}
