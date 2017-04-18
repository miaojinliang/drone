package com.miao.test.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.miao.test.bean.User;

public interface UserMapper {
	List<User> getUser(@Param("username") String username,@Param("password") String password);
}
