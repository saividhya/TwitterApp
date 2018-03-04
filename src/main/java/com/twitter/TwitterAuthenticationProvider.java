package com.twitter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.twitter.entity.User;

@Component
public class TwitterAuthenticationProvider implements AuthenticationProvider{
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        User user = authenticateUser(name, password);
        if (user!=null) {
            return new UsernamePasswordAuthenticationToken(name, password, new ArrayList<>());
        } else {
        	throw new BadCredentialsException("Username incorrect");
        }
	}

	private User authenticateUser(String name, String password) throws AuthenticationException {
		String sql = "select p.id,handle,name from people p where p.handle = :name";
        SqlParameterSource namedParameter = new MapSqlParameterSource("name", name);
        User user = null;
        try{
           user = namedParameterJdbcTemplate.queryForObject(sql, namedParameter, new UserMapper());
        }catch(EmptyResultDataAccessException e){
        	throw new BadCredentialsException("Username incorrect");
        }
		return user;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
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
