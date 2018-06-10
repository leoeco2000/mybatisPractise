package com.mybatis.common.user.dao;

import com.mybatis.common.user.entity.User;

public interface UserDao {  
    User findUserById(String id);  
}  