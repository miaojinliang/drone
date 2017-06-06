package com.miao.test.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.miao.test.bean.Drone;
import com.miao.test.bean.RaspClient;
import com.miao.test.service.AutoPlanService;
import com.miao.test.service.RaspclientService;
import com.miao.test.util.HttpUtil;

/**
 * 
 * @author MiaoJinliang
 * 自动计划设置控制类
 *
 */
@Controller
public class AutoPlanController {
	@Autowired
	private AutoPlanService autoPlanService;
	
	@Autowired
	private RaspclientService raspService;
	
	/**
	 * 跳转到自动控制
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/auto")
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
		return "auto";
	}
}
