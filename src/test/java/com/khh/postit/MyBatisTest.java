package com.khh.postit;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by hyunhokim on 2017. 8. 25..
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class MyBatisTest {

    @Autowired
    private SqlSessionFactory sqlFactory;

    @Test
    public void testFactory() {
        try(SqlSession session = sqlFactory.openSession()) {
            System.out.println(session);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}