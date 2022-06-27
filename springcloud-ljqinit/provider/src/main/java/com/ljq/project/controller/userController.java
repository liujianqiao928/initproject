package com.ljq.project.controller;

import com.ljq.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class userController {
    @Autowired
    private UserService userService;
    @RequestMapping("/getUser")
    public String getUser() {
        return userService.getUser();
    }
}
