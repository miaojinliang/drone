package com.miao.test.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.miao.test.bean.Access;
import com.miao.test.bean.Drone;
import com.miao.test.service.AccessService;
import com.miao.test.service.DroneService;

@Controller
public class SettingController {
	
	@Autowired
	private DroneService droneService;
	
	@Autowired
	private AccessService accessService;
	
	@RequestMapping(value = "/setting")
	public String setting(HttpServletRequest request, HttpServletResponse response,ModelMap model){
		model.put("dronesList", droneService.getDrones());
		return "setting";
	}
	
	@RequestMapping(value = "/addDrone")
	@ResponseBody
	public Integer addDrone(Drone drone){
		droneService.insertDrone(drone);
		return 1;
	}
	
	@RequestMapping(value = "/deleteDrone")
	@ResponseBody
	public Integer deleteDrone(@RequestParam("id") Integer id){
		droneService.deleteDrone(id);
		return 1;
	}
	
	@RequestMapping(value = "/getDrones")
	@ResponseBody
	public List<Drone> getDrones(){
		return droneService.getDrones();
	}
	
	@RequestMapping(value = "/updateDrone")
	@ResponseBody
	public Integer updateDrone(Drone drone){
		droneService.updateDrone(drone);
		return 1;
	}
	
	
	@RequestMapping(value = "/addAccess")
	@ResponseBody
	public Integer addAccess(Access access){
		accessService.insertAccessForDrone(access);
		return 1;
	}
	
	@RequestMapping(value = "/deleteAccess")
	@ResponseBody
	public Integer deleteAccess(@RequestParam("id") Integer id){
		accessService.deleteAccess(id);
		return 1;
	}
	
	@RequestMapping(value = "/getAccesses")
	@ResponseBody
	public List<Access> getAccesses(@RequestParam("droneId") Integer droneId){
		return accessService.getAccessByDronId(droneId);
	}
	
	@RequestMapping(value = "/updateAccess")
	@ResponseBody
	public Integer updateAccess(Access access){
		accessService.updateAccess(access);
		return 1;
	}
	
}
