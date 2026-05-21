package com.udacity.jwdnd.course1.cloudstorage.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.*;

import com.udacity.jwdnd.course1.cloudstorage.*;
import com.udacity.jwdnd.course1.cloudstorage.model.User;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM USERS WHERE username = #{username}")
    User getUser(String username);

    @Insert("INSERT INTO USERS (username, salt, password, firstname, lastname) VALUES (#{username}, #{salt}, #{password}, #{firstname}, #{lastname})")
    int insert(User user);
}

