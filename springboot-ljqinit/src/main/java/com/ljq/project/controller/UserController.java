package com.ljq.project.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ljq.project.dto.response.ResultResponse;
import com.ljq.project.enums.TipEnum;
import com.ljq.project.exception.MyException;
import com.ljq.project.model.User;
import com.ljq.project.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "用户接口", tags = "用户接口")
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/getUser")
    public ResultResponse<String> getUser() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("*");

      String res= JSON.toJSONString(userService.list(queryWrapper));

        return new ResultResponse<>(TipEnum.SUCCESS.getCode(), TipEnum.SUCCESS.getMessage(), res);
    }


}
