package com.twitter.dao;

public class SQLQueries {

	public static final String GETMYFEEDS = "select m.id,person_id,handle,name,content from messages m,"
										+ " people p where p.id = m.person_id and m.person_id = :id  order by m.id";
	
	public static final String GETFOLLOWINGFEEDS = "select m.id,person_id,handle,name,content from messages m,people p "
												   + " where m.person_id in (select person_id from followers where follower_person_id = :id) "
												   + " and m.person_id = p.id order by m.id  ";
												
	public static final String GETMYFEEDSWITHSEARCH =  "select m.id,person_id,handle,name,content  from messages m,people p "
													+ " where p.id = m.person_id and m.person_id = :id and content like :search  order by m.id";
	
	
	public static final String GETFOLLOWINGFEEDSWITHSEARCH = "select m.id,person_id,handle,name,content from messages m,people p "
															+ " where m.person_id in (select person_id from followers where follower_person_id = :id) "
															+ " and m.person_id = p.id and content like :search  order by m.id";
	
	public static final String GETUSERS =  "select p.id,handle,name from people p ";
	
	public static final String GETUSERBYID =  "select p.id,handle,name from people p where p.id = :id ";
	
	public static final String GETFOLLOWERS =  "select p.id, handle, name  from followers f,people p  where person_id = :id and p.id = follower_person_id ";
	
	public static final String GETFOLLOWING = "select p.id, handle, name  from followers f,people p  where follower_person_id = :id and p.id = person_id ";
	
	public static final String CHECKCOUNT = "select count(1) from followers where person_id= :person_id and follower_person_id = :follower_person_id";
	
	public static final String ADDFOLLOWING = "insert into followers (person_id, follower_person_id) values (:person_id, :follower_person_id)";
	
	public static final String DELETEFOLLOWING =  "delete from followers where person_id= :person_id and follower_person_id = :follower_person_id";
	
	public static final String GETUSERBYNAME = "select p.id,handle,name from people p where p.name = :name ";
	
	public static final String GETUSERBYHANDLE = "select p.id,handle,name from people p where p.handle = :handle ";
	
	public static final String GETPOPULARUSERS = "select c.person_id user_id,p.handle user_handle,p.name user_name,"
												+ " d.follower_person_id popular_user_id,f.handle popular_user_handle,f.name popular_user_name,c.count followers_count "
												+ " from (select a.person_id  ,max(followerscount) count from followers a, "
												+ " (select  person_id, count(1) followerscount from followers group by person_id ) b "
												+ " where a.follower_person_id = b.person_id group by a.person_id)  c, "
												+ " (select a.person_id,a.follower_person_id,followerscount  from followers a, "
												+ " (select  person_id, count(1) followerscount from followers group by person_id ) b "
												+ " where a.follower_person_id = b.person_id group by a.person_id,a.follower_person_id "
												+ " order by a.person_id,a.follower_person_id) d, "
												+ " people p,people f where c.person_id = d.person_id and c.count = d.followerscount "
												+ " and p.id = c.person_id and f.id = d.follower_person_id";
	
}