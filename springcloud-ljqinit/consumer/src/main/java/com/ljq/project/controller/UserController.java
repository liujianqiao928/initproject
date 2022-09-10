package com.ljq.project.controller;

import com.ljq.project.feign.UserFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserFeign userFeign;
    @RequestMapping("/getUser")
    public String getUser() {
        return userFeign.getUser();
    }
}
