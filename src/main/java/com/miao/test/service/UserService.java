package com.miao.test.service;

import java.util.List;

import com.miao.test.bean.User;

public interface UserService {
	List<User> getUser(String username,String password);
}
