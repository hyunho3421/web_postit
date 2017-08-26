package com.khh.service;

import com.khh.domain.User;
import org.springframework.stereotype.Service;

/**
 * Created by hyunhokim on 2017. 8. 27..
 */
public interface UserService {
    User findByID(String id);
    void create(User user);
}
