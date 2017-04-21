package com.miao.test.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.miao.test.bean.Access;
import com.miao.test.bean.Drone;
import com.miao.test.bean.RaspClient;
import com.miao.test.service.AccessService;
import com.miao.test.service.DroneService;
import com.miao.test.service.RaspclientService;

@Controller
public class SettingController {
	
	@Autowired
	private DroneService droneService;
	
	@Autowired
	private AccessService accessService;
	
	@Autowired
	private RaspclientService raspService;
	
	@RequestMapping(value = "/setting")
	public String setting(HttpServletRequest request, HttpServletResponse response,ModelMap model){
		model.put("dronesList", droneService.getDrones());
		if(request.getSession().getAttribute("fromUrl")!=null){
			model.put("fromUrl",request.getSession().getAttribute("fromUrl"));
		}
		return "setting";
	}
	
	@RequestMapping(value = "/settingWithUrl",method=RequestMethod.POST)
	public String setting(HttpServletRequest request,@RequestParam("url") String url, HttpServletResponse response,ModelMap model){
		model.put("dronesList", droneService.getDrones());
		model.put("fromUrl", url);
		request.getSession().setAttribute("fromUrl", url);
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
		
		List<Access> droneAccess = accessService.getAccessByDronId(id);
		for(Access access : droneAccess){
			accessService.deleteAccess(access.getId());
		}
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
	
	@RequestMapping(value = "/rasp")
	public String getRasps(HttpServletRequest request, HttpServletResponse response,ModelMap model){
		model.put("raspclients", raspService.getRaspclients());
		return "rasp";
	}
	
	
	@RequestMapping(value = "/addRasp")
	public String addRasp(RaspClient rasp){
		raspService.insertRaspclient(rasp);
		return "redirect:rasp";
	}
	
	@RequestMapping(value = "/deleteRasp")
	public String deleteRasp(@RequestParam("id") Integer id){
		raspService.deleteRaspclient(id);
		return "redirect:rasp";
	}
}
