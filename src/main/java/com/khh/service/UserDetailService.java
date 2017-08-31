package com.khh.service;

import com.khh.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hyunhokim on 2017. 8. 27..
 */
public class UserDetailService implements UserDetailsService {
    @Autowired
    UserService userService;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        User user = userService.findByID(id);

        if (user != null) {
            List<GrantedAuthority> authorityList = new ArrayList<>();

            authorityList.add(new SimpleGrantedAuthority("ROLE_USER"));

            return new org.springframework.security.core.userdetails.User(
                    user.getId(),
                    user.getPassword(),
                    authorityList
            );
        }

        throw new UsernameNotFoundException("No User found with id " + id);
    }
}
