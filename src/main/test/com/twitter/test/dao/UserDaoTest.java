package com.twitter.test.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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

public class UserDaoTest {

	@Autowired
	UserDao userDao;
	
	//1. Test to get all users in the system
	@Test
	public void testGetUsers(){
		try {
			List<User> actualResult = userDao.getUsers();
			assertEquals(14,actualResult.size());
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//2. Test to get a user with valid id
	@Test
	public void testGetUserById(){
		User testUser = ExpectedResultBuilder.getValidUser();
		try {
			User actualResult = userDao.getUserById("12");
			assertEquals(testUser.getId(),actualResult.getId());
			assertEquals(testUser.getName(),actualResult.getName());
			assertEquals(testUser.getHandle(),actualResult.getHandle());
			
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	//3. Test for user with invalid id
	@Test
	public void testGetUserByIdInvalid(){
		try {
			User actualResult = userDao.getUserById("54"); 
		} catch (UserNotFoundException e) {
			assertEquals("User not found",e.getMessage());
		}
	}
	//4. Test for user with followers
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
			 
		}
	}
	
	//5. Test for user with no followers
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
	
	//6. Test for user with followers
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
			 
		}
	}
	
	//7. Test for user with existing relationship
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
			assertEquals(" User wolverine is already following the user phoenix",e.getMessage());
		}
	}
	
	//8. Test for unfollowing a relation which is not present
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
			assertEquals(" User phoenix does not follow user wolverine",e.getMessage());
		}
	}
	
	//9. Test to get a user with valid id
	@Test
	public void testGetUserByName(){
		User testUser = ExpectedResultBuilder.getValidUser();
		try {
			User actualResult = userDao.getUserByName("Jean Grey");
			assertEquals(testUser.getId(),actualResult.getId());
			assertEquals(testUser.getName(),actualResult.getName());
			assertEquals(testUser.getHandle(),actualResult.getHandle());
			
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//10. Test to get a user with valid id
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//11. Test to get a user with invalid handle
	@Test
	public void testGetUserByHandleInvalid(){
		try {
			User actualResult = userDao.getUserByHandle("testinvalid");
		} catch (UserNotFoundException e) {
			assertEquals("User not found",e.getMessage());
		}
	}

	//12. Test to get a user with invalid handle
	@Test
	public void testGetUserByNameInvalid(){
		try {
			User actualResult = userDao.getUserByName("testinvalid");
		} catch (UserNotFoundException e) {
			assertEquals("User not found",e.getMessage());
		}
	}

	//13. Test to get a user with invalid handle
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
			assertEquals("User not found",e.getMessage());
		}
	}
	
}
