package com.twitter.test.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.twitter.ChallengeApplication;
import com.twitter.dto.FriendsDTO;
import com.twitter.dto.HopsDTO;
import com.twitter.entity.User;
import com.twitter.exception.FollowException;
import com.twitter.exception.UserNotFoundException;
import com.twitter.service.AnalyticsService;
import com.twitter.test.ExpectedResultBuilder;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ChallengeApplication.class)
@ComponentScan
@WebAppConfiguration
public class AnalyticsServiceTest {
	@Autowired
	AnalyticsService analyticsService;
	
	@Test
	public void testGetFriends(){
		User testUser = new User();
		testUser.setId(12);
		testUser.setHandle("phoenix");
		testUser.setName("Jean Grey");
		try {
			FriendsDTO actualResult = analyticsService.getFriends(testUser);
			FriendsDTO expectedResult = ExpectedResultBuilder.getFriends();
			assertEquals(expectedResult.getFollowers().size(),actualResult.getFollowers().size());
			assertEquals(expectedResult.getFollowing().size(),actualResult.getFollowing().size());
			for(int i=0;i < actualResult.getFollowers().size();i++){
				assertEquals(expectedResult.getFollowers().get(i).getId(), actualResult.getFollowers().get(i).getId());
				assertEquals(expectedResult.getFollowers().get(i).getHandle(), actualResult.getFollowers().get(i).getHandle());
				assertEquals(expectedResult.getFollowers().get(i).getName(), actualResult.getFollowers().get(i).getName());
			}
			
		} catch (FollowException e) { 
			assertEquals(null,e.getMessage());
		}
	}

	@Test
	public void testGetFriendsInvalid(){
		User testUser = new User();
		testUser.setId(14);
		testUser.setHandle("deathstroke");
		testUser.setName("Slade Wilson");
		try {
			FriendsDTO actualResult = analyticsService.getFriends(testUser);
			assertEquals(0,actualResult.getFollowers().size());
			assertEquals(0,actualResult.getFollowing().size());
		} catch (FollowException e) { 
			assertEquals(null,e.getMessage());
		}
	}

	@Test
	public void testGetHops(){
		User testUser = new User();
		testUser.setId(1);
		testUser.setHandle("batman");
		testUser.setName("Bruce Wayne");
		
		HopsDTO actualResult;
		try {
			actualResult = analyticsService.getHops(testUser, "5");
			HopsDTO expectedResult = ExpectedResultBuilder.getHops();
			
			assertEquals(expectedResult.getCurrentUser().getId(), actualResult.getCurrentUser().getId());
			assertEquals(expectedResult.getCurrentUser().getHandle(), actualResult.getCurrentUser().getHandle());
			assertEquals(expectedResult.getCurrentUser().getName(), actualResult.getCurrentUser().getName());
			
			assertEquals(expectedResult.getTargetUser().getId(), actualResult.getTargetUser().getId());
			assertEquals(expectedResult.getTargetUser().getHandle(), actualResult.getTargetUser().getHandle());
			assertEquals(expectedResult.getTargetUser().getName(), actualResult.getTargetUser().getName());
			
			assertEquals(expectedResult.getNoOfHops(), actualResult.getNoOfHops());
			
		} catch (UserNotFoundException e) {
			assertEquals(null,e.getMessage());
		} catch (FollowException e) {
			assertEquals(null,e.getMessage());
		}
		
	}

	@Test
	public void testGetHopsSelf(){
		User testUser = new User();
		testUser.setId(1);
		testUser.setHandle("batman");
		testUser.setName("Bruce Wayne");
		
		HopsDTO actualResult;
		try {
			actualResult = analyticsService.getHops(testUser, "1");
			HopsDTO expectedResult = ExpectedResultBuilder.getHopsSelf();
			
			assertEquals(expectedResult.getCurrentUser().getId(), actualResult.getCurrentUser().getId());
			assertEquals(expectedResult.getCurrentUser().getHandle(), actualResult.getCurrentUser().getHandle());
			assertEquals(expectedResult.getCurrentUser().getName(), actualResult.getCurrentUser().getName());
			
			assertEquals(expectedResult.getTargetUser().getId(), actualResult.getTargetUser().getId());
			assertEquals(expectedResult.getTargetUser().getHandle(), actualResult.getTargetUser().getHandle());
			assertEquals(expectedResult.getTargetUser().getName(), actualResult.getTargetUser().getName());
			
			assertEquals(expectedResult.getNoOfHops(), actualResult.getNoOfHops());
			
		} catch (UserNotFoundException e) {
			assertEquals(null,e.getMessage());
		} catch (FollowException e) {
			assertEquals(null,e.getMessage());
		}
		
	}

	@Test
	public void testGetHopsNoHops(){
		User testUser = new User();
		testUser.setId(1);
		testUser.setHandle("batman");
		testUser.setName("Bruce Wayne");
		
		HopsDTO actualResult;
		try {
			actualResult = analyticsService.getHops(testUser, "14");
			HopsDTO expectedResult = ExpectedResultBuilder.getHopsNoHops();
			
			assertEquals(expectedResult.getCurrentUser().getId(), actualResult.getCurrentUser().getId());
			assertEquals(expectedResult.getCurrentUser().getHandle(), actualResult.getCurrentUser().getHandle());
			assertEquals(expectedResult.getCurrentUser().getName(), actualResult.getCurrentUser().getName());
			
			assertEquals(expectedResult.getTargetUser().getId(), actualResult.getTargetUser().getId());
			assertEquals(expectedResult.getTargetUser().getHandle(), actualResult.getTargetUser().getHandle());
			assertEquals(expectedResult.getTargetUser().getName(), actualResult.getTargetUser().getName());
			
			assertEquals(expectedResult.getNoOfHops(), actualResult.getNoOfHops());
			
		} catch (UserNotFoundException e) {
			assertEquals(null,e.getMessage());
		} catch (FollowException e) {
			assertEquals(null,e.getMessage());
		}
		
	}
	
}
