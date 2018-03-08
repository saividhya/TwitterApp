package com.twitter.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.twitter.dto.PopularUsersDTO;
import com.twitter.entity.User;
import com.twitter.exception.FollowException;
import com.twitter.exception.UnFollowException;
import com.twitter.exception.UserNotFoundException;

@Repository
public class UserDaoImpl implements UserDao{
	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Override
	public List<User> getUsers() throws UserNotFoundException {
		List<User> users = null;
		SqlParameterSource namedParameter = new MapSqlParameterSource();
		try{
		 users = namedParameterJdbcTemplate.query(SQLQueries.ALL_USERS, namedParameter, new UserMapper());
		}catch(EmptyResultDataAccessException e){
			throw new UserNotFoundException("No users in the system");
		}
		return users;
	}

	@Override
	public User getUserById(String id) throws UserNotFoundException {
		SqlParameterSource namedParameter = new MapSqlParameterSource("id", id);
		User user = null;
		try{
		 user = namedParameterJdbcTemplate.queryForObject(SQLQueries.USERBY_ID, namedParameter, new UserMapper());
		}catch(EmptyResultDataAccessException e){
			throw new UserNotFoundException("User not found");
		}
		if(user==null)
			throw new UserNotFoundException("User not found");
		return user;
	}

	@Override
	public List<User> getFollowers(User user) throws FollowException {
		List<User> users = null;
		SqlParameterSource namedParameter = new MapSqlParameterSource("id", user.getId());
		try{
		 users = namedParameterJdbcTemplate.query(SQLQueries.FOLLOWERS, namedParameter, new UserMapper());
		}catch(EmptyResultDataAccessException e){
			throw new FollowException("There are no followers for this user");
		}
		return users;
	}

	@Override
	public List<User> getFollowing(User user) throws FollowException {
		List<User> users = null;
		SqlParameterSource namedParameter = new MapSqlParameterSource("id", user.getId());
		try{
		 users = namedParameterJdbcTemplate.query(SQLQueries.FOLLOWING, namedParameter, new UserMapper());
		}catch(EmptyResultDataAccessException e){
			throw new FollowException("The user does not follow anyone");
		}
		return users;
	}

	@Override
	public void addFollowing(User currentUser, User targetUser) throws FollowException {
		
		
		//Check if the current user already follows the target user
		if(checkFollowing(currentUser,targetUser)){
			throw new FollowException("User "+ currentUser.getHandle() +" is already following the user "+ targetUser.getHandle());
		}
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("person_id", targetUser.getId());
        paramMap.put("follower_person_id", currentUser.getId());
        try{
        	namedParameterJdbcTemplate.update(SQLQueries.FOLLOW, paramMap);  
        }catch(Exception e){
        	throw new FollowException("Cannot follow user " + targetUser.getHandle() );
        }
	}

	@Override
	public void deleteFollowing(User currentUser, User targetUser) throws UnFollowException {
		
		//Check if the current user follows the target user
		SqlParameterSource namedParameter = new MapSqlParameterSource("person_id", targetUser.getId()).addValue("follower_person_id", currentUser.getId());
		if(!checkFollowing(currentUser,targetUser)){
			throw new UnFollowException("User "+ currentUser.getHandle() +" does not follow user "+ targetUser.getHandle());
		}
		
		try{
			namedParameterJdbcTemplate.update(SQLQueries.UNFOLLOW, namedParameter);
        }catch(Exception e){
        	throw new UnFollowException("Cannot unfollow user " + targetUser.getHandle() );
        }
	}

	@Override
	public User getUserByName(String name) throws UserNotFoundException{
		SqlParameterSource namedParameter = new MapSqlParameterSource("name", name);
		User user = null;
		try{
		 user = namedParameterJdbcTemplate.queryForObject(SQLQueries.USER_BY_NAME, namedParameter, new UserMapper());
		}catch(EmptyResultDataAccessException e){
			throw new UserNotFoundException("User not found");
		}
		if(user==null)
			throw new UserNotFoundException("User not found");
		return user;
	}

	@Override
	public User getUserByHandle(String handle) throws UserNotFoundException{
		SqlParameterSource namedParameter = new MapSqlParameterSource("handle", handle);
		User user = null;
		try{
		 user = namedParameterJdbcTemplate.queryForObject(SQLQueries.USER_BY_HANDLE, namedParameter, new UserMapper());
		}catch(EmptyResultDataAccessException e){
			throw new UserNotFoundException("User not found");
		}
		if(user==null)
			throw new UserNotFoundException("User not found");
		return user;
	}
	
	@Override
	public User getUserByUsername(String name) throws UserNotFoundException{
		SqlParameterSource namedParameter = new MapSqlParameterSource("name", name);
		User user = null;
		try{
		 user = namedParameterJdbcTemplate.queryForObject(SQLQueries.USER_BY_USERNAME, namedParameter, new UserMapper());
		}catch(EmptyResultDataAccessException e){
			throw new UserNotFoundException("User not found");
		}
		if(user==null)
			throw new UserNotFoundException("User not found");
		return user;
	}
	
	@Override
	public List<PopularUsersDAO> getPopularUsers() throws UserNotFoundException {
		SqlParameterSource namedParameter = new MapSqlParameterSource( );
		List<PopularUsersDAO> popualarUsers = null;
		try{
			popualarUsers =  namedParameterJdbcTemplate.query(SQLQueries.POPULAR_USERS, namedParameter, new PopularUserMapper());
		}catch(EmptyResultDataAccessException e){
			throw new UserNotFoundException("No users in the system");
		}
		if(popualarUsers==null)
			throw new UserNotFoundException("No users in the system");
		return popualarUsers;
		 
	}
	
	@Override
	public boolean checkFollowing(User currentUser, User targetUser) {
		SqlParameterSource namedParameter = new MapSqlParameterSource("person_id", targetUser.getId()).addValue("follower_person_id", currentUser.getId());
		Integer count = namedParameterJdbcTemplate.queryForObject(SQLQueries.CHECK_COUNT, namedParameter, Integer.class);
		if(count > 0){
			return true;
		}else{
			return false;
		}
	}

	
	private static final class UserMapper implements RowMapper<User>{
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setId(rs.getInt("id"));
			user.setHandle(rs.getString("handle"));
			user.setName(rs.getString("name"));
			return user;
		}
	}
	
	private static final class PopularUserMapper implements RowMapper<PopularUsersDAO>{
		public PopularUsersDAO mapRow(ResultSet rs, int rowNum) throws SQLException {
			PopularUsersDAO popularUsers = new PopularUsersDAO();
			
			User user = new User();
			user.setId(rs.getInt("user_id"));
			user.setHandle(rs.getString("user_handle"));
			user.setName(rs.getString("user_name"));
			
			User popularUser = new User();
			popularUser.setId(rs.getInt("popular_user_id"));
			popularUser.setHandle(rs.getString("popular_user_handle"));
			popularUser.setName(rs.getString("popular_user_name"));
			
			popularUsers.setUser(user);
			popularUsers.setPopularUser(popularUser);
			popularUsers.setFollowersCount(rs.getInt("followers_count"));

			return popularUsers;
		}
	}

	
	

}
