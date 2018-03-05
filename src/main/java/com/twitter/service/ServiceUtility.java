package com.twitter.service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.twitter.dao.UserDao;
import com.twitter.entity.User;
import com.twitter.exception.FollowException;

public class ServiceUtility {
	
	 
	public int getShortestDistance(User currentUser, User targetUser,UserDao userDao) throws FollowException {
		if(currentUser.getId() == targetUser.getId()){
			return 0;
		}
		Queue<User> queue = new LinkedList<User>();
		queue.add(currentUser);
		HashMap<Integer,Integer> visited = new HashMap<Integer,Integer>();
		visited.put(currentUser.getId(),1);
		while(!queue.isEmpty()){
			User temp = queue.poll();
			List<User> following = userDao.getFollowing(temp);
			if(following != null){
				for(User u: following){
					if(u.getId()==targetUser.getId()) 
						return visited.get(temp.getId());
					if(!visited.containsKey(u.getId())){
						visited.put(u.getId(),visited.get(temp.getId())+1);
						queue.add(u);
					}
				}
			}
		}
		return 0;
	}
	
}
