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
	
	
	@RequestMapping(value = "/droneModify")
	public String droneModify(HttpServletRequest request, HttpServletResponse response,ModelMap model,@RequestParam("id") Integer id){
		Drone drone = droneService.getById(id);
		model.put("drone", drone);
		return "droneModify";
	}
	
	@RequestMapping(value = "/addDrone")
	@ResponseBody
	public Integer addDrone(Drone drone){
		droneService.insertDrone(drone);
		return 1;
	}
	
	@RequestMapping(value = "/deleteDrone")
	public String deleteDrone(@RequestParam("id") Integer id){
		droneService.deleteDrone(id);
		return "redirect:setting";
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
	public String addAccess(Access access){
		accessService.insertAccessForDrone(access);
		return "redirect:getAccesses?droneId="+access.getDroneId();
	}
	
	@RequestMapping(value = "/deleteAccess")
	public String deleteAccess(@RequestParam("id") Integer id,@RequestParam("droneId") Integer droneId){
		accessService.deleteAccess(id);
		return "redirect:getAccesses?droneId="+droneId;
	}
	
	@RequestMapping(value = "/getAccesses")
	public String getAccesses(HttpServletRequest request, HttpServletResponse response,@RequestParam("droneId") Integer droneId,ModelMap model){
		List<Access> access = accessService.getAccessByDronId(droneId);
		Drone drone = droneService.getById(droneId);
		model.put("drone", drone);
		model.put("accessList", access);
		return "access";
	}
	
	@RequestMapping(value = "/updateAccess")
	@ResponseBody
	public Integer updateAccess(Access access){
		accessService.updateAccess(access);
		return 1;
	}
	
}
