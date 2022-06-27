package com.ljq.project.service;


import com.ljq.project.model.User;

/**
* @author 20552
* @description 针对表【user】的数据库操作Mapper
* @createDate 2022-06-27 09:40:08
* @Entity generator.domain.User
*/
public interface UserService  {
    public User getUserById(Integer id);
}




