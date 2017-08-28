package com.khh.repo;

import com.khh.domain.PostIt;

import java.util.List;

public interface PostItRepo {
    int create(PostIt postIt);
    PostIt findById(int id);
    List<PostIt> findAll(String user_id);
    void update(PostIt postIt);
    void deleteById(int id);
    void deleteAll();
    int count(String user_id);
    int count();
    void initAutoIncrement();
}
