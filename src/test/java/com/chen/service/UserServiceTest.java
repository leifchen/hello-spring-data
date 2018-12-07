package com.chen.service;

import com.chen.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * UserServiceTest
 *
 * @Author LeifChen
 * @Date 2018-12-07
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void saveAll() {
        List<User> users = new ArrayList<>();
        User user;
        for (int i = 0; i < 50; i++) {
            user = new User();
            user.setName("test" + i);
            user.setAge(i);
            users.add(user);
        }
        userService.saveAll(users);
    }

    @Test
    public void updateById() {
        userService.updateById(3, 28);
    }

    @Test
    public void deleteById() {
        userService.deleteById(4);
    }
}