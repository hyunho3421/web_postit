package com.khh.repo;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.khh.domain.User;

/**
 *	Created by hyunhokim on 2017. 8. 25..
 */
@Repository
public class UserRepoImpl implements UserRepo{

    @Autowired
    private SqlSession sqlSession;

    private static String namespace = "com.khh.mapper.UserMapper";

	
	@Override
	public void create(User user) {
		sqlSession.insert(namespace + ".create", user);
	}

	@Override
	public void update() {
		// TODO: 추후 구현
	}

	@Override
	public void delete(String id) {
		// TODO: 추후 구현
	}
	
	@Override
	public void delete() {
		sqlSession.delete(namespace + ".deleteAll");
	}

	@Override
	public User findByID(String id) {
		return sqlSession.selectOne(namespace + ".findByID", id);
	}

	@Override
	public List<User> findAll() {
		return sqlSession.selectList(namespace + ".findAll");
	}

	@Override
	public int count() {
		return sqlSession.selectOne(namespace + ".count");
	}
	
}
