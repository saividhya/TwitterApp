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
	public List<Message> getMyFeeds(User u, String search) throws UserNotFoundException {
		String sql = "select m.id,person_id,handle,name,content  from messages m,people p "
				    + " where p.id = m.person_id and m.person_id = :id and content like :search ";
		if(u==null || u.getId()==0){
			throw new UserNotFoundException("User not found");
		}
		String searchString = "%" + search + "%";
		SqlParameterSource namedParameter = new MapSqlParameterSource("id", u.getId()).addValue("search", searchString);
		List<Message> messages = namedParameterJdbcTemplate.query(sql, namedParameter, new MessageMapper());
		return messages;	
	}

	@Override
	public List<Message> getFollowingFeeds(User u, String search) throws UserNotFoundException {
		String sql = "select m.id,person_id,handle,name,content from messages m,people p "
					+ " where m.person_id in (select person_id from followers where follower_person_id = :id) "
					+ " and m.person_id = p.id and content like :search";
		if(u==null || u.getId()==0){
			throw new UserNotFoundException("User not found");
		}
		String searchString = "%" + search + "%";
		SqlParameterSource namedParameter = new MapSqlParameterSource("id", u.getId()).addValue("search", searchString);
		List<Message> messages = namedParameterJdbcTemplate.query(sql, namedParameter, new MessageMapper());
		return messages;
	}
	
	private static final class MessageMapper implements RowMapper<Message>{
		public Message mapRow(ResultSet rs, int rowNum) throws SQLException {
			Message message = new Message();
			message.setId(rs.getInt("ID"));
			message.setContent(rs.getString("CONTENT"));
			
			User user = new User();
			user.setId(rs.getInt("PERSON_ID"));
			user.setHandle(rs.getString("HANDLE"));
			user.setName(rs.getString("NAME"));
			
			message.setUser(user);
			return message;
		}
	}

}
