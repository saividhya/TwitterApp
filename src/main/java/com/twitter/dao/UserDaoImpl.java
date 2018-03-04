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
		String sql = "select p.id,handle,name from people p ";
		SqlParameterSource namedParameter = new MapSqlParameterSource();
		try{
		 users = namedParameterJdbcTemplate.query(sql, namedParameter, new UserMapper());
		}catch(EmptyResultDataAccessException e){
			throw new UserNotFoundException("No users in the system");
		}
		return users;
	}

	@Override
	public User getUserById(String id) throws UserNotFoundException {
		String sql = "select p.id,handle,name from people p where p.id = :id ";
		SqlParameterSource namedParameter = new MapSqlParameterSource("id", id);
		User user = null;
		try{
		 user = namedParameterJdbcTemplate.queryForObject(sql, namedParameter, new UserMapper());
		}catch(EmptyResultDataAccessException e){
			throw new UserNotFoundException("User not found");
		}
		if(user==null)
			throw new UserNotFoundException("User not found");
		return user;
	}

	@Override
	public List<User> getFollowers(User u) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getFollowing(User u) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addFollowing(User currentUser, User targetUser) throws FollowException {
		
		
		//Check if the current user already follows the target user
		String checkQuery = "select count(1) from followers where person_id= :person_id and follower_person_id = :follower_person_id";
		SqlParameterSource namedParameter = new MapSqlParameterSource("person_id", targetUser.getId()).addValue("follower_person_id", currentUser.getId());
		Integer count = namedParameterJdbcTemplate.queryForObject(checkQuery, namedParameter, Integer.class);
		if(count > 0){
			throw new FollowException(" User "+ currentUser.getHandle() +" is already following the user "+ targetUser.getHandle());
		}
		
		String query = "insert into followers (person_id, follower_person_id) values (:person_id, :follower_person_id)";
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("person_id", targetUser.getId());
        paramMap.put("follower_person_id", currentUser.getId());
        try{
        	namedParameterJdbcTemplate.update(query, paramMap);  
        }catch(Exception e){
        	throw new FollowException("Cannot follow user " + targetUser.getHandle() );
        }
	}

	@Override
	public void deleteFollowing(User currentUser, User targetUser) throws UnFollowException {
		
		//Check if the current user follows the target user
		String checkQuery = "select count(1) from followers where person_id= :person_id and follower_person_id = :follower_person_id";
		SqlParameterSource namedParameter = new MapSqlParameterSource("person_id", targetUser.getId()).addValue("follower_person_id", currentUser.getId());
		Integer count = namedParameterJdbcTemplate.queryForObject(checkQuery, namedParameter, Integer.class);
		if(count == 0){
			throw new UnFollowException(" User "+ currentUser.getHandle() +" does not follow user "+ targetUser.getHandle());
		}
		
		String query = "DELETE FROM followers WHERE person_id= :person_id and follower_person_id = :follower_person_id";
		try{
			namedParameterJdbcTemplate.update(query, namedParameter);
        }catch(Exception e){
        	throw new UnFollowException("Cannot unfollow user " + targetUser.getHandle() );
        }
	}

	@Override
	public User getUserByName(String name) throws UserNotFoundException{
		String sql = "select p.id,handle,name from people p where p.name = :name ";
		SqlParameterSource namedParameter = new MapSqlParameterSource("name", name);
		User user = null;
		try{
		 user = namedParameterJdbcTemplate.queryForObject(sql, namedParameter, new UserMapper());
		}catch(EmptyResultDataAccessException e){
			throw new UserNotFoundException("User not found");
		}
		if(user==null)
			throw new UserNotFoundException("User not found");
		return user;
	}

	@Override
	public User getUserByHandle(String handle) throws UserNotFoundException{
		String sql = "select p.id,handle,name from people p where p.handle = :handle ";
		SqlParameterSource namedParameter = new MapSqlParameterSource("handle", handle);
		User user = null;
		try{
		 user = namedParameterJdbcTemplate.queryForObject(sql, namedParameter, new UserMapper());
		}catch(EmptyResultDataAccessException e){
			throw new UserNotFoundException("User not found");
		}
		if(user==null)
			throw new UserNotFoundException("User not found");
		return user;
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

}
