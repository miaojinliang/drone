package com.miao.test.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.miao.test.bean.Drone;
import com.miao.test.bean.RaspClient;
import com.miao.test.common.CommonCore;
import com.miao.test.driver.DroneDriver;
import com.miao.test.service.RaspclientService;
import com.miao.test.util.HttpUtil;

/**
 * 
 * @author Miao jinliang
 *
 */
@Controller
public class DroneController {
	
	@Autowired
	private RaspclientService raspService;
	
	@RequestMapping(value = "/index")
	public String toIndex(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		List<Drone> allDrones = new ArrayList<Drone>();
		try{
			List<RaspClient> raspClients = raspService.getRaspclients();
			for(RaspClient rasp : raspClients){
				String url = "http://"+rasp.getIp()+":"+rasp.getPort()+"/"+(rasp.getAppName()==null?"":rasp.getAppName())+"getDrones";
					System.out.println(url);
				String body = HttpUtil.doGet(url, null);
					List<Drone> raspDrones = JSON.parseArray(body, Drone.class);
					for(Drone drone : raspDrones){
						drone.setRaspClient(rasp);
					}
					allDrones.addAll(raspDrones);
			}
		}catch(Throwable e){
			e.printStackTrace();
		}
		model.put("allDrones", allDrones);
		return "index";
	}
	
	
	@RequestMapping(value = "/droneMove")
	@ResponseBody
	public void droneMove(@RequestParam("droneId") Integer droneId,@RequestParam("raspId") Integer raspId,@RequestParam("moveType") String moveType) throws InterruptedException{
		try {
			RaspClient rasp = raspService.getRaspById(raspId);
			String raspAddr = "http://"+rasp.getIp()+":"+rasp.getPort()+"/"+(rasp.getAppName()==null?"":rasp.getAppName())+"/motorMove";
			System.out.println(raspAddr);
			Map<String, Object>  params = new HashMap<String, Object>();
			params.put("droneId",droneId);
			params.put("moveType", moveType);
			HttpUtil.doPost(raspAddr, params);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/rotateMove")
	@ResponseBody
	public void rotateMove(@RequestParam("droneId") Integer droneId,@RequestParam("raspId") Integer raspId,@RequestParam("moveType") String moveType) throws InterruptedException{
		try {
			RaspClient rasp = raspService.getRaspById(raspId);
			String raspAddr = "http://"+rasp.getIp()+":"+rasp.getPort()+"/"+(rasp.getAppName()==null?"":rasp.getAppName())+"/motorRotate";
			Map<String, Object>  params = new HashMap<String, Object>();
			params.put("droneId",droneId);
			params.put("moveType", moveType);
			HttpUtil.doPost(raspAddr, params);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/changeInterval")
	@ResponseBody
	public void changeInterval(@RequestParam("droneId") Integer droneId,@RequestParam("raspId") Integer raspId,@RequestParam("value") Integer value) throws InterruptedException{
		try {
			RaspClient rasp = raspService.getRaspById(raspId);
			String raspAddr = "http://"+rasp.getIp()+":"+rasp.getPort()+"/"+(rasp.getAppName()==null?"":rasp.getAppName())+"/motorChangeInterval";
			Map<String, Object>  params = new HashMap<String, Object>();
			params.put("droneId",droneId);
			params.put("value", value);
			HttpUtil.doPost(raspAddr, params);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
}
