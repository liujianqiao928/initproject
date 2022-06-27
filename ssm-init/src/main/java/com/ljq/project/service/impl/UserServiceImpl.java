package com.ljq.project.service.impl;


import com.ljq.project.mapper.UserMapper;
import com.ljq.project.model.User;
import com.ljq.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author 20552
* @description 针对表【user】的数据库操作Service实现
* @createDate 2022-06-27 09:40:08
*/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public User getUserById(Integer id) {
        return userMapper.getUserById(id);

    }
}




