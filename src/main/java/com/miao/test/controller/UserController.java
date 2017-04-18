package com.miao.test.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.miao.test.service.UserService;


@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/checkUser")
	@ResponseBody
	public Integer checkUser(@RequestParam("username")  String username,@RequestParam("password") String password){
		return userService.getUser(username, password).size();
	}
	@RequestMapping(value = "/index")
	public String toIndex(){
		return "index";
	}
}
