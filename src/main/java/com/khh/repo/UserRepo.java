package com.khh.repo;

import java.util.List;

import com.khh.domain.User;

/**
 * Created by hyunhokim on 2017. 8. 25..
 */
public interface UserRepo {
	void create(User user);
	void update();
	void delete(String id);
	void delete();
	User find(String id);
	List<User> findAll();
	int count(); 
}
