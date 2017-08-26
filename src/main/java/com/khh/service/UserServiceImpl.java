package com.khh.service;

import com.khh.domain.User;
import com.khh.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by hyunhokim on 2017. 8. 27..
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;

    @Override
    public User findByID(String id) {
        return userRepo.findByID(id);
    }

    @Override
    public void create(User user) {
        userRepo.create(user);
    }
}
