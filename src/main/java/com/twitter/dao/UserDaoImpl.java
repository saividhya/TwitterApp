package com.twitter.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.twitter.entity.Message;
import com.twitter.entity.User;
import com.twitter.exception.UserNotFoundException;

@Repository
public class UserDaoImpl implements UserDao{
	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Override
	public List<User> getUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserById(String id) {
		// TODO Auto-generated method stub
		return null;
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
	public void addFollowing(User currentUser, User targetUser) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteFollowing(User currentUser, User targetUser) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User getUserByName(String name) {
		// TODO Auto-generated method stub
		return null;
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
			user.setId(rs.getInt("ID"));
			user.setHandle(rs.getString("HANDLE"));
			user.setName(rs.getString("NAME"));
			return user;
		}
	}

}
