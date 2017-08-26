package com.khh.repo;

import com.khh.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by hyunhokim on 2017. 8. 26..
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/**/**.xml")
public class UserRepoTest {
    @Autowired
    UserRepo userRepo;

    User user;

    @Before
    public void setUp() {
        user = new User();
        user.setId("haha9006");
        user.setPassword("3421");
        user.setName("hyunho");
        user.setEmail("hyunho@dayside.co.kr");
    }

    @Test
    public void createAndFindUserTest() {
        init();

        userRepo.create(user);
        assertThat(1, is(userRepo.count()));

        compareUser(user.getId());
    }

    public void init() {
        userRepo.delete();
        assertThat(0, is(userRepo.count()));
    }

    public void compareUser(String id) {
        User findUser = userRepo.find(id);

        assertThat(user.getId(), is(findUser.getId()));
        assertThat(user.getPassword(), is(findUser.getPassword()));
        assertThat(user.getName(), is(findUser.getName()));
        assertThat(user.getEmail(), is(findUser.getEmail()));
    }
}
