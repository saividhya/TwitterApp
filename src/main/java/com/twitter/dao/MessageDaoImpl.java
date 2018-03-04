package com.twitter.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.twitter.entity.Message;
import com.twitter.entity.User;
import com.twitter.exception.UserNotFoundException;

@Repository
public class MessageDaoImpl implements MessageDao{
	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	

	@Override
	public List<Message> getMyFeeds(User u) throws UserNotFoundException {
		String sql = "select m.id,person_id,handle,name,content from messages m,"
				   + " people p where p.id = m.person_id and m.person_id = :id";
		if(u==null || u.getId()==0){
			throw new UserNotFoundException("User not found");
		}
		SqlParameterSource namedParameter = new MapSqlParameterSource("id", u.getId());
		List<Message> messages = namedParameterJdbcTemplate.query(sql, namedParameter, new MessageMapper());
		return messages;	
	}
	
	@Override
	public List<Message> getFollowingFeeds(User u) throws UserNotFoundException{
		String sql = "select m.id,person_id,handle,name,content from messages m,people p "
				   + " where m.person_id in (select person_id from followers where follower_person_id = :id) "
				   + " and m.person_id = p.id;";
		if(u==null || u.getId()==0){
			throw new UserNotFoundException("User not found");
		}
		SqlParameterSource namedParameter = new MapSqlParameterSource("id", u.getId());
		List<Message> messages = namedParameterJdbcTemplate.query(sql, namedParameter, new MessageMapper());
		return messages;
	}

	@Override
	public List<Message> getMyFeeds(User user, String search) throws UserNotFoundException {
		String sql = "select m.id,person_id,handle,name,content  from messages m,people p "
				    + " where p.id = m.person_id and m.person_id = :id and content like :search ";
		if(user==null || user.getId()==0){
			throw new UserNotFoundException("User not found");
		}
		String searchString = "";
		List<Message> messages = null;
		if(search != null){
			searchString = "%" + search + "%";
			SqlParameterSource namedParameter = new MapSqlParameterSource("id", user.getId()).addValue("search", searchString);
			messages = namedParameterJdbcTemplate.query(sql, namedParameter, new MessageMapper());
		}else{
			messages = getMyFeeds(user);
		}
		
		return messages;	
	}

	@Override
	public List<Message> getFollowingFeeds(User user, String search) throws UserNotFoundException {
		String sql = "select m.id,person_id,handle,name,content from messages m,people p "
					+ " where m.person_id in (select person_id from followers where follower_person_id = :id) "
					+ " and m.person_id = p.id and content like :search";
		if(user==null || user.getId()==0){
			throw new UserNotFoundException("User not found");
		}
		String searchString = "";
		List<Message> messages = null;
		if(search != null){
			searchString = "%" + search + "%";
			SqlParameterSource namedParameter = new MapSqlParameterSource("id", user.getId()).addValue("search", searchString);
			messages = namedParameterJdbcTemplate.query(sql, namedParameter, new MessageMapper());
		}else{
			messages = getFollowingFeeds(user);
		}
		return messages;
	}
	
	private static final class MessageMapper implements RowMapper<Message>{
		public Message mapRow(ResultSet rs, int rowNum) throws SQLException {
			Message message = new Message();
			message.setId(rs.getInt("id"));
			message.setContent(rs.getString("content"));
			
			User user = new User();
			user.setId(rs.getInt("person_id"));
			user.setHandle(rs.getString("handle"));
			user.setName(rs.getString("name"));
			
			message.setUser(user);
			return message;
		}
	}

}
