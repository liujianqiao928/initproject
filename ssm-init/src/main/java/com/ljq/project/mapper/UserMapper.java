package com.ljq.project.mapper;

import com.ljq.project.model.User;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 20552
* @description 针对表【user】的数据库操作Mapper
* @createDate 2022-06-27 09:40:08
* @Entity generator.domain.User
*/
@Mapper
public interface UserMapper  {
    public User getUserById(Integer id);

}




