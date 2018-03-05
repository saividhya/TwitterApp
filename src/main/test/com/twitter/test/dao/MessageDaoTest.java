package com.twitter.test.dao;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.twitter.ChallengeApplication;
import com.twitter.dao.MessageDao;
import com.twitter.entity.Message;
import com.twitter.entity.User;
import com.twitter.exception.UserNotFoundException;
import com.twitter.test.ExpectedResultBuilder;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ChallengeApplication.class)
@ComponentScan
public class MessageDaoTest {

	@Autowired
	MessageDao messageDao;
	
	
	//1. Test for a valid user with feeds
	@Test
	public void testMyFeedsWithValidUser(){
		User testUser = new User();
		testUser.setId(12);
		testUser.setHandle("phoenix");
		testUser.setName("Jean Grey");
		try {
			List<Message> actualResult = messageDao.getMyFeeds(testUser);
			List<Message> expectedResult = ExpectedResultBuilder.getMyFeeds();
			assertEquals(expectedResult.size(),actualResult.size());
			for(int i=0;i < actualResult.size();i++){
				assertEquals(expectedResult.get(i).getUser().getId(), actualResult.get(i).getUser().getId());
				assertEquals(expectedResult.get(i).getUser().getHandle(), actualResult.get(i).getUser().getHandle());
				assertEquals(expectedResult.get(i).getUser().getName(), actualResult.get(i).getUser().getName());
				assertEquals(expectedResult.get(i).getContent(), actualResult.get(i).getContent());
			}
		} catch (UserNotFoundException e) {
			System.out.println("test failed");
		}
		
	}
	
	//2. Test for invalid user
	@Test
	public void testMyFeedsWithInValidUser(){
		User testUser = new User();
		testUser.setId(54);
		testUser.setHandle("invalid");
		testUser.setName("Invalid user");
		try {
			List<Message> actualResult = messageDao.getMyFeeds(testUser);
			assertEquals(new ArrayList<Message>(),actualResult);
		} catch (UserNotFoundException e) {
			assertEquals("User not found",e.getMessage());
		}
	}
	
	//3. Test for users with no feed
	@Test
	public void testMyFeedsWithValidUserWithNoFeed(){
		User testUser = new User();
		testUser.setId(14);
		testUser.setHandle("deathstroke");
		testUser.setName("Slade Wilson");
		try {
			List<Message> actualResult = messageDao.getMyFeeds(testUser);
			assertEquals(new ArrayList<Message>(),actualResult);
		} catch (UserNotFoundException e) {
			assertEquals("User not found",e.getMessage());
		}
	}
	
	//4. Test for content with search query
	@Test
	public void testMyFeedsWithSearchQuery(){
		User testUser = new User();
		testUser.setId(13);
		testUser.setHandle("wolverine");
		testUser.setName("Logan");
		try {
			String search = "look";
			List<Message> actualResult = messageDao.getMyFeeds(testUser,search);
			List<Message> expectedResult = ExpectedResultBuilder.getMyFeedsWithSearch();
			assertEquals(expectedResult.size(),actualResult.size());
			for(int i=0;i < actualResult.size();i++){
				assertEquals(expectedResult.get(i).getUser().getId(), actualResult.get(i).getUser().getId());
				assertEquals(expectedResult.get(i).getUser().getHandle(), actualResult.get(i).getUser().getHandle());
				assertEquals(expectedResult.get(i).getUser().getName(), actualResult.get(i).getUser().getName());
				assertEquals(expectedResult.get(i).getContent(), actualResult.get(i).getContent());
			}
		} catch (UserNotFoundException e) {
			assertEquals("User not found",e.getMessage());
		}
	}
	
	//5. Test for content with search query no result
		@Test
		public void testMyFeedsWithSearchQueryNoResult(){
			User testUser = new User();
			testUser.setId(13);
			testUser.setHandle("wolverine");
			testUser.setName("Logan");
			try {
				String search = "jean";
				List<Message> actualResult = messageDao.getMyFeeds(testUser,search);
				assertEquals(0,actualResult.size());
				assertEquals(new ArrayList<Message>(),actualResult);
			} catch (UserNotFoundException e) {
				assertEquals("User not found",e.getMessage());
			}
		}
		
		//6. Test for content with empty search query
		@Test
		public void testMyFeedsWithEmptySearchQuery(){
			User testUser = new User();
			testUser.setId(12);
			testUser.setHandle("phoenix");
			testUser.setName("Jean Grey");
			try {
				String search = "";
				List<Message> actualResult = messageDao.getMyFeeds(testUser,search);
				List<Message> expectedResult = ExpectedResultBuilder.getMyFeeds();
				
				assertEquals(expectedResult.size(),actualResult.size());
				for(int i=0;i < actualResult.size();i++){
					assertEquals(expectedResult.get(i).getUser().getId(), actualResult.get(i).getUser().getId());
					assertEquals(expectedResult.get(i).getUser().getHandle(), actualResult.get(i).getUser().getHandle());
					assertEquals(expectedResult.get(i).getUser().getName(), actualResult.get(i).getUser().getName());
					assertEquals(expectedResult.get(i).getContent(), actualResult.get(i).getContent());
				}
			} catch (UserNotFoundException e) {
				assertEquals("User not found",e.getMessage());
			}
		}
		
		//7. Test for a valid user with following feeds
		@Test
		public void testFollowingFeedsWithValidUser(){
			User testUser = new User();
			testUser.setId(13);
			testUser.setHandle("wolverine");
			testUser.setName("Logan");
			try {
				List<Message> actualResult = messageDao.getFollowingFeeds(testUser);
				List<Message> expectedResult = ExpectedResultBuilder.getMyFeeds();
				assertEquals(expectedResult.size(),actualResult.size());
				for(int i=0;i < actualResult.size();i++){
					assertEquals(expectedResult.get(i).getUser().getId(), actualResult.get(i).getUser().getId());
					assertEquals(expectedResult.get(i).getUser().getHandle(), actualResult.get(i).getUser().getHandle());
					assertEquals(expectedResult.get(i).getUser().getName(), actualResult.get(i).getUser().getName());
					assertEquals(expectedResult.get(i).getContent(), actualResult.get(i).getContent());
				}
			} catch (UserNotFoundException e) {
				System.out.println("test failed");
			}
		}
		
		//8. Test for a valid user with following feeds
		@Test
		public void testFollowingFeedsWithForUserWithNoFollowing(){
			User testUser = new User();
			testUser.setId(14);
			testUser.setHandle("deathstroke");
			testUser.setName("Slade Wilson");
			try {
				List<Message> actualResult = messageDao.getFollowingFeeds(testUser);
				assertEquals(0,actualResult.size());
			} catch (UserNotFoundException e) {
				System.out.println("test failed");
			}
		}
		
		
		
}