package com.khh.service;

import com.khh.domain.User;
import com.khh.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by hyunhokim on 2017. 8. 27..
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Override
    public User findByID(String id) {
        return userRepo.findByID(id);
    }

    @Override
    public void create(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.create(user);
    }
}
