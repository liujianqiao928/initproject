package com.ljq.project;

import com.ljq.project.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserTest {
    @Autowired
    private UserService userService;
    @Test
    public void test() {
        System.out.println(userService.getUser());
    }
}
