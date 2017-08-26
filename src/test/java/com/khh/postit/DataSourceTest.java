package com.khh.postit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * Created by hyunhokim on 2017. 8. 25..
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class DataSourceTest {
    @Autowired
    private DataSource ds;

    @Test
    public void testConnection() throws Exception {
        try(Connection conn = ds.getConnection()) {
            System.out.println(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
