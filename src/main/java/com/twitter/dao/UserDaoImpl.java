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
		final String SQL = "select p.id,handle,name from people p ";
		SqlParameterSource namedParameter = new MapSqlParameterSource();
		try{
		 users = namedParameterJdbcTemplate.query(SQL, namedParameter, new UserMapper());
		}catch(EmptyResultDataAccessException e){
			throw new UserNotFoundException("No users in the system");
		}
		return users;
	}

	@Override
	public User getUserById(String id) throws UserNotFoundException {
		final String SQL = "select p.id,handle,name from people p where p.id = :id ";
		SqlParameterSource namedParameter = new MapSqlParameterSource("id", id);
		User user = null;
		try{
		 user = namedParameterJdbcTemplate.queryForObject(SQL, namedParameter, new UserMapper());
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
		final String SQL = "select p.id, handle, name  from followers f,people p  where person_id = :id and p.id = follower_person_id ";
		SqlParameterSource namedParameter = new MapSqlParameterSource("id", user.getId());
		try{
		 users = namedParameterJdbcTemplate.query(SQL, namedParameter, new UserMapper());
		}catch(EmptyResultDataAccessException e){
			throw new FollowException("There are no followers for this user");
		}
		return users;
	}

	@Override
	public List<User> getFollowing(User user) throws FollowException {
		List<User> users = null;
		final String SQL = "select p.id, handle, name  from followers f,people p  where follower_person_id = :id and p.id = person_id ";
		SqlParameterSource namedParameter = new MapSqlParameterSource("id", user.getId());
		try{
		 users = namedParameterJdbcTemplate.query(SQL, namedParameter, new UserMapper());
		}catch(EmptyResultDataAccessException e){
			throw new FollowException("The user does not follow anyone");
		}
		return users;
	}

	@Override
	public void addFollowing(User currentUser, User targetUser) throws FollowException {
		
		
		//Check if the current user already follows the target user
		final String CHECKQUERY = "select count(1) from followers where person_id= :person_id and follower_person_id = :follower_person_id";
		SqlParameterSource namedParameter = new MapSqlParameterSource("person_id", targetUser.getId()).addValue("follower_person_id", currentUser.getId());
		Integer count = namedParameterJdbcTemplate.queryForObject(CHECKQUERY, namedParameter, Integer.class);
		if(count > 0){
			throw new FollowException(" User "+ currentUser.getHandle() +" is already following the user "+ targetUser.getHandle());
		}
		
		final String SQL = "insert into followers (person_id, follower_person_id) values (:person_id, :follower_person_id)";
		Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("person_id", targetUser.getId());
        paramMap.put("follower_person_id", currentUser.getId());
        try{
        	namedParameterJdbcTemplate.update(SQL, paramMap);  
        }catch(Exception e){
        	throw new FollowException("Cannot follow user " + targetUser.getHandle() );
        }
	}

	@Override
	public void deleteFollowing(User currentUser, User targetUser) throws UnFollowException {
		
		//Check if the current user follows the target user
		final String CHECKQUERY = "select count(1) from followers where person_id= :person_id and follower_person_id = :follower_person_id";
		SqlParameterSource namedParameter = new MapSqlParameterSource("person_id", targetUser.getId()).addValue("follower_person_id", currentUser.getId());
		Integer count = namedParameterJdbcTemplate.queryForObject(CHECKQUERY, namedParameter, Integer.class);
		if(count == 0){
			throw new UnFollowException(" User "+ currentUser.getHandle() +" does not follow user "+ targetUser.getHandle());
		}
		
		final String SQL = "DELETE FROM followers WHERE person_id= :person_id and follower_person_id = :follower_person_id";
		try{
			namedParameterJdbcTemplate.update(SQL, namedParameter);
        }catch(Exception e){
        	throw new UnFollowException("Cannot unfollow user " + targetUser.getHandle() );
        }
	}

	@Override
	public User getUserByName(String name) throws UserNotFoundException{
		final String SQL = "select p.id,handle,name from people p where p.name = :name ";
		SqlParameterSource namedParameter = new MapSqlParameterSource("name", name);
		User user = null;
		try{
		 user = namedParameterJdbcTemplate.queryForObject(SQL, namedParameter, new UserMapper());
		}catch(EmptyResultDataAccessException e){
			throw new UserNotFoundException("User not found");
		}
		if(user==null)
			throw new UserNotFoundException("User not found");
		return user;
	}

	@Override
	public User getUserByHandle(String handle) throws UserNotFoundException{
		final String SQL = "select p.id,handle,name from people p where p.handle = :handle ";
		SqlParameterSource namedParameter = new MapSqlParameterSource("handle", handle);
		User user = null;
		try{
		 user = namedParameterJdbcTemplate.queryForObject(SQL, namedParameter, new UserMapper());
		}catch(EmptyResultDataAccessException e){
			throw new UserNotFoundException("User not found");
		}
		if(user==null)
			throw new UserNotFoundException("User not found");
		return user;
	}
	
	@Override
	public List<PopularUsersDTO> getPopularUsers() throws UserNotFoundException {
		final String SQL = "select c.person_id userid,p.handle userhandle,p.name username,"
				+ " d.follower_person_id popularuserid,f.handle popularuserhandle,f.name popularusername,c.count followerscount "
				+ " from (select a.person_id  ,max(followerscount) count from followers a, "
				+ " (select  person_id, count(1) followerscount from followers group by person_id ) b "
				+ " where a.follower_person_id = b.person_id group by a.person_id)  c, "
				+ " (select a.person_id,a.follower_person_id,followerscount  from followers a, "
				+ " (select  person_id, count(1) followerscount from followers group by person_id ) b "
				+ " where a.follower_person_id = b.person_id group by a.person_id,a.follower_person_id order by a.person_id,a.follower_person_id) d, "
				+ " people p,people f where c.person_id = d.person_id and c.count = d.followerscount "
				+ " and p.id = c.person_id and f.id = d.follower_person_id";
		SqlParameterSource namedParameter = new MapSqlParameterSource( );
		List<PopularUsersDTO> popualarUsers = null;
		try{
			popualarUsers =  namedParameterJdbcTemplate.query(SQL, namedParameter, new PopularUserMapper());
		}catch(EmptyResultDataAccessException e){
			throw new UserNotFoundException("No users in the system");
		}
		if(popualarUsers==null)
			throw new UserNotFoundException("No users in the system");
		return popualarUsers;
		 
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
	
	private static final class PopularUserMapper implements RowMapper<PopularUsersDTO>{
		public PopularUsersDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
			PopularUsersDTO popularUsers = new PopularUsersDTO();
			
			User user = new User();
			user.setId(rs.getInt("userid"));
			user.setHandle(rs.getString("userhandle"));
			user.setName(rs.getString("username"));
			
			User popularUser = new User();
			popularUser.setId(rs.getInt("popularuserid"));
			popularUser.setHandle(rs.getString("popularuserhandle"));
			popularUser.setName(rs.getString("popularusername"));
			
			popularUsers.setUser(user);
			popularUsers.setPopularUser(popularUser);
			popularUsers.setFollowersCount(rs.getInt("followerscount"));

			return popularUsers;
		}
	}

	

}
