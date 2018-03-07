package com.twitter.test.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.twitter.ChallengeApplication;
import com.twitter.dao.UserDao;
import com.twitter.dto.PopularUsersDTO;
import com.twitter.entity.User;
import com.twitter.exception.FollowException;
import com.twitter.exception.UnFollowException;
import com.twitter.exception.UserNotFoundException;
import com.twitter.test.ExpectedResultBuilder;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ChallengeApplication.class)
@ComponentScan
@WebAppConfiguration
public class UserDaoTest {

	@Autowired
	UserDao userDao;
	
	//Test to get all users in the system
	@Test
	public void testGetUsers(){
		try {
			List<User> actualResult = userDao.getUsers();
			assertEquals(14,actualResult.size());
		} catch (UserNotFoundException e) {
			assertEquals(null,e.getMessage());
		}
	}
	
	//Test to get a user with valid id
	@Test
	public void testGetUserById(){
		User testUser = ExpectedResultBuilder.getValidUser();
		try {
			User actualResult = userDao.getUserById("12");
			assertEquals(testUser.getId(),actualResult.getId());
			assertEquals(testUser.getName(),actualResult.getName());
			assertEquals(testUser.getHandle(),actualResult.getHandle());
			
		} catch (UserNotFoundException e) {
			assertEquals(null,e.getMessage());
		}
	}
		
	//Test for user with invalid id
	@Test
	public void testGetUserByIdInvalid(){
		try {
			User actualResult = userDao.getUserById("54"); 
		} catch (UserNotFoundException e) {
			assertEquals("User not found",e.getMessage());
		}
	}
	//Test for user with followers
	@Test
	public void testGetFollowersValid(){
		User testUser = new User();
		testUser.setId(12);
		testUser.setHandle("phoenix");
		testUser.setName("Jean Grey");
		
		try {
			List<User> actualResult = userDao.getFollowers(testUser); 
			List<User> expectedResult = ExpectedResultBuilder.getFollowersValid();
			assertEquals(expectedResult.size(),actualResult.size());
			for(int i=0;i < actualResult.size();i++){
				assertEquals(expectedResult.get(i).getId(), actualResult.get(i).getId());
				assertEquals(expectedResult.get(i).getHandle(), actualResult.get(i).getHandle());
				assertEquals(expectedResult.get(i).getName(), actualResult.get(i).getName());
			}
			
		} catch (FollowException e) {
			assertEquals(null,e.getMessage());
		}
	}
	
	//Test for user with no followers
	@Test
	public void testGetFollowersInvalid(){
		User testUser = new User();
		testUser.setId(13);
		testUser.setHandle("wolverine");
		testUser.setName("Logan");
		try {
			List<User> actualResult = userDao.getFollowers(testUser); 
		} catch (FollowException e) {
			assertEquals("There are no followers for this user",e.getMessage());
		}
	}
	
	//Test for user who follows other users
	@Test
	public void testGetFollowingValid(){
		User testUser = new User();
		testUser.setId(13);
		testUser.setHandle("wolverine");
		testUser.setName("Logan");
		try {
			List<User> actualResult = userDao.getFollowing(testUser); 
			List<User> expectedResult = ExpectedResultBuilder.getFollowingValid();
			assertEquals(expectedResult.size(),actualResult.size());
			for(int i=0;i < actualResult.size();i++){
				assertEquals(expectedResult.get(i).getId(), actualResult.get(i).getId());
				assertEquals(expectedResult.get(i).getHandle(), actualResult.get(i).getHandle());
				assertEquals(expectedResult.get(i).getName(), actualResult.get(i).getName());
			}
			
		} catch (FollowException e) {
			assertEquals(null,e.getMessage());
		}
	}
	
	//Test for user who doesn't follow other users
	@Test
	public void testGetFollowingInValid(){
		User testUser = new User();
		testUser.setId(14);
		testUser.setHandle("deathstroke");
		testUser.setName("Slade Wilson");
		try {
			List<User> actualResult = userDao.getFollowing(testUser);  
			
		} catch (FollowException e) {
			assertEquals("The user does not follow anyone",e.getMessage());
		}
	}
	
	  //Test for user with existing relationship
		@Test
		public void testCheckFollowingValid(){
			User testUser1 = new User();
			testUser1.setId(12);
			testUser1.setHandle("phoenix");
			testUser1.setName("Jean Grey");
			
			User testUser2 = new User();
			testUser2.setId(13);
			testUser2.setHandle("wolverine");
			testUser2.setName("Logan");
			assertEquals(true,userDao.checkFollowing(testUser2, testUser1)); 
			
		}
		
		//Test to check for a no existing relationship
		@Test
		public void testCheckFollowingInvalid(){
			User user11 = new User();
			user11.setId(11);
			user11.setName("Barry Allen");
			user11.setHandle("flash");
			
			User user1 = new User();
			user1.setId(1);
			user1.setName("Bruce Wayne");
			user1.setHandle("batman");
			assertEquals(false,userDao.checkFollowing(user1, user11));
		}
		
	
	//Test for user with existing relationship
	@Test
	public void testAddFollowingInvalid(){
		User testUser1 = new User();
		testUser1.setId(12);
		testUser1.setHandle("phoenix");
		testUser1.setName("Jean Grey");
		
		User testUser2 = new User();
		testUser2.setId(13);
		testUser2.setHandle("wolverine");
		testUser2.setName("Logan");
		
		try {
			userDao.addFollowing(testUser2,testUser1); 
		} catch (FollowException e) { 
			assertEquals("User wolverine is already following the user phoenix",e.getMessage());
		}
	}
	
	//Test to add new relationship
	@Test
	public void testAddFollowingValid(){
		User user11 = new User();
		user11.setId(11);
		user11.setName("Barry Allen");
		user11.setHandle("flash");
		
		User user1 = new User();
		user1.setId(1);
		user1.setName("Bruce Wayne");
		user1.setHandle("batman");
		
		try {
			userDao.addFollowing(user1,user11); 
			assertEquals(true,userDao.checkFollowing(user1, user11));
		} catch (FollowException e) { 
			assertEquals(null,e.getMessage());
		}
	}
	
	//Test for unfollow for valid relationship
	@Test
	public void testDeleteFollowingValid(){
		User user5 = new User();
		user5.setId(5);
		user5.setName("Alfred Pennyworth");
		user5.setHandle("alfred");
		
		User user1 = new User();
		user1.setId(1);
		user1.setName("Bruce Wayne");
		user1.setHandle("batman");
		
		try {
			userDao.deleteFollowing(user1,user5);
			assertEquals(false,userDao.checkFollowing(user1, user5));
		} catch (UnFollowException e) {
			assertEquals(null,e.getMessage());
		}
	}
	
	//Test for unfollowing a relation which is not present
	@Test
	public void testDeleteFollowingInvalid(){
		User testUser1 = new User();
		testUser1.setId(12);
		testUser1.setHandle("phoenix");
		testUser1.setName("Jean Grey");
		
		User testUser2 = new User();
		testUser2.setId(13);
		testUser2.setHandle("wolverine");
		testUser2.setName("Logan");
		
		try {
			userDao.deleteFollowing(testUser1,testUser2); 
		} catch (UnFollowException e) {
			assertEquals("User phoenix does not follow user wolverine",e.getMessage());
		}
	}
	
	
	
	//Test to get a user with valid name
	@Test
	public void testGetUserByName(){
		User testUser = ExpectedResultBuilder.getValidUser();
		try {
			User actualResult = userDao.getUserByName("Jean Grey");
			assertEquals(testUser.getId(),actualResult.getId());
			assertEquals(testUser.getName(),actualResult.getName());
			assertEquals(testUser.getHandle(),actualResult.getHandle());
			
		} catch (UserNotFoundException e) {
			assertEquals(null,e.getMessage());
		}
	}

	//Test to get a user with invalid name
	@Test
	public void testGetUserByNameInvalid(){
		try {
			User actualResult = userDao.getUserByName("testinvalid");
		} catch (UserNotFoundException e) {
			assertEquals("User not found",e.getMessage());
		}
	}

	//Test to get a user with valid handle
	@Test
	public void testGetUserByHandle(){
		User testUser = ExpectedResultBuilder.getValidUser();
		try {
			User actualResult = userDao.getUserByHandle("phoenix");
			System.out.println(actualResult.getName());
			assertEquals(testUser.getId(),actualResult.getId());
			assertEquals(testUser.getName(),actualResult.getName());
			assertEquals(testUser.getHandle(),actualResult.getHandle());
		} catch (UserNotFoundException e) {
			assertEquals(null,e.getMessage());
		}
	}

	//Test to get a user with invalid handle
	@Test
	public void testGetUserByHandleInvalid(){
		try {
			User actualResult = userDao.getUserByHandle("testinvalid");
		} catch (UserNotFoundException e) {
			assertEquals("User not found",e.getMessage());
		}
	}

	// Test to get a user with invalid handle
	@Test
	public void testGetPopularUsers(){
		try {
			List<PopularUsersDTO> actualResult = userDao.getPopularUsers();
			List<PopularUsersDTO> expectedResult = ExpectedResultBuilder.getPopularUsers();
			
			assertEquals(expectedResult.size(),actualResult.size());
			for(int i=0;i < actualResult.size();i++){
				assertEquals(expectedResult.get(i).getUser().getId(), actualResult.get(i).getUser().getId());
				assertEquals(expectedResult.get(i).getUser().getHandle(), actualResult.get(i).getUser().getHandle());
				assertEquals(expectedResult.get(i).getUser().getName(), actualResult.get(i).getUser().getName());
			}
			
		} catch (UserNotFoundException e) {
			assertEquals(null,e.getMessage());
		}
	}
	
}
