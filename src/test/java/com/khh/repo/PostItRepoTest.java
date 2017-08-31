package com.khh.repo;

import com.khh.domain.PostIt;
import com.khh.domain.User;
import javafx.geometry.Pos;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by hyunhokim on 2017. 8. 26..
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/**/**.xml")
public class PostItRepoTest {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    PostItRepo postItRepo;

    User user;

    PostIt postIt;

    @Before
    public void setUp() {
        user = new User();
        user.setId("haha9006");
        user.setPassword("3421");
        user.setName("hyunho");
        user.setEmail("hyunho@dayside.co.kr");

        postIt = new PostIt();
        postIt.setUser_id(user.getId());
        postIt.setContent("post-it content test");
        postIt.setH_color("#FFB700");
        postIt.setC_color("#FFD700");
        postIt.setPos_x(50);
        postIt.setPos_y(100);
        postIt.setWidth(150);
        postIt.setHeight(150);
        postIt.setZ_idx(15);
    }

    @Test
    public void createAndFindPostItTest() {
        init();
        createPostit();
        assertThat(1, is(postItRepo.count(user.getId())));

        // 첫 번째 포스트잇 비교
        comparePostIt(1);
    }

    @Test
    public void updatePostItTest() {
        init();
        createPostit();
        comparePostIt(1);

        // 포스트잇 수정
        postIt.setId(1);
        postIt.setContent("updated post-it content");
        postIt.setH_color("#5CB1E5");
        postIt.setC_color("#5CD1E5");
        postIt.setPos_x(20);
        postIt.setPos_y(10);
        postIt.setWidth(230);
        postIt.setHeight(330);
        postIt.setZ_idx(29);

        postItRepo.update(postIt);

        // 수정된 포스트잇이랑 비교
        comparePostIt(1);
    }

    @Test
    public void deletePostitTest() {
        init();
        createPostit();
        assertThat(1, is(postItRepo.count(user.getId())));

        postItRepo.deleteById(1);
        assertThat(0, is(postItRepo.count(user.getId())));
    }

    public void init() {
        userRepo.delete();
        assertThat(0, is(userRepo.count()));
        userRepo.create(user);

        postItRepo.deleteAll();
        postItRepo.initAutoIncrement();
        assertThat(0, is(postItRepo.count()));
    }

    public void createPostit() {
        postItRepo.create(postIt);
        assertThat(1, is(postItRepo.count()));
    }

    public void comparePostIt(int id) {
        PostIt findPostIt = postItRepo.findById(id);

        assertThat(postIt.getUser_id(), is(findPostIt.getUser_id()));
        assertThat(postIt.getContent(), is(findPostIt.getContent()));
        assertThat(postIt.getH_color(), is(findPostIt.getH_color()));
        assertThat(postIt.getC_color(), is(findPostIt.getC_color()));
        assertThat(postIt.getPos_x(), is(findPostIt.getPos_x()));
        assertThat(postIt.getPos_y(), is(findPostIt.getPos_y()));
        assertThat(postIt.getWidth(), is(findPostIt.getWidth()));
        assertThat(postIt.getHeight(), is(findPostIt.getHeight()));
        assertThat(postIt.getZ_idx(), is(findPostIt.getZ_idx()));
    }
}
