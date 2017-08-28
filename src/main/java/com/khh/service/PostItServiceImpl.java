package com.khh.service;

import com.khh.domain.PostIt;
import com.khh.repo.PostItRepo;
import com.khh.util.commonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostItServiceImpl implements PostItService {
    @Autowired
    private PostItRepo postItRepo;

    @Override
    public int create(PostIt postIt) {
        postIt.setUser_id(commonUtils.getLoginId());
        return postItRepo.create(postIt);
    }

    @Override
    public void deleteById(int id) {
        postItRepo.deleteById(id);
    }

    @Override
    public void update(PostIt postIt) {
        postIt.setUser_id(commonUtils.getLoginId());
        postItRepo.update(postIt);
    }

    @Override
    public List<PostIt> findAll() {
        return postItRepo.findAll(commonUtils.getLoginId());
    }

    @Override
    public PostIt findById(int id) {
        return postItRepo.findById(id);
    }
}
