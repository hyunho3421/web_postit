package com.khh.service;

import com.khh.domain.PostIt;

import java.util.List;

public interface PostItService {
    int create(PostIt postIt);
    void deleteById(int id);
    void update(PostIt postIt);
    List<PostIt> findAll();
    PostIt findById(int id);
}
