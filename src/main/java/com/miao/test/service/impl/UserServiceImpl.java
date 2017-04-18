package com.miao.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miao.test.bean.User;
import com.miao.test.mapper.UserMapper;
import com.miao.test.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	
	@Override
	public List<User> getUser(String username, String password) {
		return userMapper.getUser(username, password);
	}

}
