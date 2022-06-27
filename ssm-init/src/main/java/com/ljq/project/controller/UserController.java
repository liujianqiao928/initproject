package com.ljq.project.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ljq.project.dto.response.ResultResponse;
import com.ljq.project.enums.TipEnum;
import com.ljq.project.exception.MyException;
import com.ljq.project.model.User;
import com.ljq.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/getuser.do")
    public void getUser() {
        ResultResponse<User> userResultResponse = new ResultResponse<>(TipEnum.SUCCESS.getCode(), TipEnum.SUCCESS.getMessage(), userService.getUserById(1));
        System.out.println(userResultResponse.getData());
    }


}
