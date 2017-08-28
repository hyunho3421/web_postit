package com.khh.repo;

import com.khh.domain.PostIt;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PostItRepoImpl implements PostItRepo {

    @Autowired
    private SqlSession sqlSession;

    private static String namespace = "com.khh.mapper.PostItMapper";

    @Override
    public int create(PostIt postIt) {
        sqlSession.insert(namespace + ".create", postIt);

        return postIt.getId();
    }

    @Override
    public PostIt findById(int id) {
        return sqlSession.selectOne(namespace + ".findById", id);
    }

    @Override
    public List<PostIt> findAll(String user_id) {
        return sqlSession.selectList(namespace + ".findAll", user_id);
    }

    @Override
    public void update(PostIt postIt) {
        sqlSession.update(namespace + ".update", postIt);
    }

    @Override
    public void deleteById(int id) {
        sqlSession.delete(namespace + ".deleteById", id);
    }

    @Override
    public void deleteAll() {
        sqlSession.delete(namespace + ".deleteAll");
    }

    @Override
    public int count() {
        return sqlSession.selectOne(namespace + ".count");
    }

    @Override
    public int count(String user_id) {
        return sqlSession.selectOne(namespace + ".countUserPostIt", user_id);
    }

    @Override
    public void initAutoIncrement() {
        sqlSession.selectOne(namespace + ".initAutoIncrement");
    }
}
