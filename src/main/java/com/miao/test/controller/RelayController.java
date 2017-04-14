package com.miao.test.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.miao.test.common.CommonCore;

@Controller
public class RelayController {
	
	@RequestMapping(value = "/relayBefore")
	@ResponseBody
	public Integer relayBefore(){
		CommonCore.relayDrivers.get(0).runBefore();
		return 1;
	}
	
	
	@RequestMapping(value = "/relayBack")
	@ResponseBody
	public Integer relayBack(){
		CommonCore.relayDrivers.get(0).runBack();
		return 1;
	}
	
	@RequestMapping(value = "/relayStop")
	@ResponseBody
	public Integer relayStop(){
		CommonCore.relayDrivers.get(0).stopAll();
		return 1;
	}
}
