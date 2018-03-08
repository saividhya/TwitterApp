package com.twitter.dao;

public class SQLQueries {

	public static final String AUTHENTICATION = "select p.id,handle,name from people p where p.username = :name  and p.password = :password"; 
	
	public static final String MY_FEEDS = "select m.id,person_id,handle,name,content from messages m,"
										+ " people p where p.id = m.person_id and m.person_id = :id  order by m.id";
	
	public static final String FOLLOWING_FEEDS = "select mid id,pid person_id,handle,name,content  from  "
			+ " ( select m.id mid ,p.id  pid ,handle ,name ,content , follower_person_id from messages m,people p,followers f "
			+ " where m.person_id = f.person_id and m.person_id = p.id and p.id = f.person_id ) "
			+ " where follower_person_id = :id order by mid"; 
												
	public static final String MYFEEDS_WITH_SEARCH =  "select m.id,person_id,handle,name,content  from messages m,people p "
													+ " where p.id = m.person_id and m.person_id = :id and content like :search  order by m.id";
	
	
	public static final String FOLLOWINGFEEDS_WITH_SEARCH = "select mid id,pid person_id,handle,name,content  from  "
			+ " ( select m.id mid ,p.id  pid ,handle ,name ,content , follower_person_id from messages m,people p,followers f "
			+ " where m.person_id = f.person_id and m.person_id = p.id and p.id = f.person_id ) "
			+ " where follower_person_id = :id and content like :search order by mid"; 
	
	public static final String ALL_USERS =  "select p.id,handle,name from people p ";
	
	public static final String USERBY_ID =  "select p.id,handle,name from people p where p.id = :id ";
	
	public static final String FOLLOWERS =  "select p.id, handle, name  from followers f,people p  where person_id = :id and p.id = follower_person_id ";
	
	public static final String FOLLOWING = "select p.id, handle, name  from followers f,people p  where follower_person_id = :id and p.id = person_id ";
	
	public static final String CHECK_COUNT = "select count(1) from followers where person_id= :person_id and follower_person_id = :follower_person_id";
	
	public static final String FOLLOW = "insert into followers (person_id, follower_person_id) values (:person_id, :follower_person_id)";
	
	public static final String UNFOLLOW =  "delete from followers where person_id= :person_id and follower_person_id = :follower_person_id";
	
	public static final String USER_BY_NAME = "select p.id,handle,name from people p where p.name = :name ";
	
	public static final String USER_BY_HANDLE = "select p.id,handle,name from people p where p.handle = :handle ";
	
	public static final String USER_BY_USERNAME = "select p.id,handle,name from people p where p.username = :name ";
	
	public static final String POPULAR_USERS = "select c.person_id user_id,p.handle user_handle,p.name user_name,"
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
