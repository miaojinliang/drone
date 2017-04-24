package com.miao.test.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.miao.test.bean.Drone;
import com.miao.test.common.CommonCore;
import com.miao.test.driver.DroneDriver;
import com.miao.test.service.DroneService;

@Controller
public class MotorController {
	
	@RequestMapping(value = "/getMotors")
	@ResponseBody
	public List<DroneDriver> getMotors(){
		return CommonCore.motorDrivers;
	}
	
	
	@Autowired
	private DroneService droneService;
	
	
	
	@RequestMapping(value = "/motorMove")
	@ResponseBody
	public void motorMove(@RequestParam("droneId") Integer droneId,@RequestParam("moveType") String moveType) throws InterruptedException{
		System.out.println("motorMove"+"----droneId:"+droneId+"---"+moveType);
		
		Drone drone = droneService.getById(droneId);
		DroneDriver droneDriver = null;
		for(DroneDriver md : CommonCore.motorDrivers){
			if(md.getMotorNum()==droneId){
				droneDriver = md;
			}
		}
		
		switch (drone.getType()) {
		case 1:
			
			break;
		case 2:
			
			break;
		case 3:
	
			break;

		default:
			break;
		}
		
//		Integer direction=0;
//		switch (moveType) {
//		case "START":
//			direction = 0;
//			break;
//		case "BACK":
//			direction = 1;
//			break;
//		default:
//			break;
//		}	
//		for(DroneDriver md : CommonCore.motorDrivers){
//				if(md.getMotorNum()==droneId){
//					if(md.getType()==1||md.getType()==3){
//						md.setMotorRunningDirection(direction);
//						if(!md.getMotorRunning()){//说明在转动中
////							md.setCacheManager(cacheManager);
//							md.setMotorRunning(true);
////							md.moveThread();
//							md.motorStart();
//						}
//					}else if(md.getType()==2){
//						md.relayStart();
//					}
//					
//				}
//			}
	}
	
	
	
//	@RequestMapping(value = "/motorMove")
//	@ResponseBody
//	public void motorMove(@RequestParam("moterNum") Integer motorNum,@RequestParam("direction") Integer direction) throws InterruptedException{
//		
////		if(cacheManager.getCache("raspTest").get("run_"+motorNum)==null||!Boolean.parseBoolean(cacheManager.getCache("raspTest").get("run_"+motorNum).getObjectValue().toString())){
////			Element element = new Element("run_"+motorNum, true);  
////			cacheManager.getCache("raspTest").put(element);
//			for(DroneDriver md : CommonCore.motorDrivers){
//				if(md.getMotorNum()==motorNum){
//					md.setMotorRunningDirection(direction);
//					if(!md.getMotorRunning()){//说明在转动中
////						md.setCacheManager(cacheManager);
//						md.setMotorRunning(true);
////						md.moveThread();
//						md.motorStart();
//					}
//				}
//			}
////		}else{
////			for(MotorDriver md : CommonCore.motorDrivers){
////				if(md.getMotorNum()==motorNum){
////					md.setDirection(direction);
////				}
////			}
////		}
//	}
//	
//	
//	@RequestMapping(value = "/motorMoveFen")
//	@ResponseBody
//	public void motorMoveFen(@RequestParam("moterNum") Integer motorNum,@RequestParam("direction") Integer direction,@RequestParam("fen") Integer fen) throws InterruptedException{
//		
////		if(cacheManager.getCache("raspTest").get("run_"+motorNum)==null||!Boolean.parseBoolean(cacheManager.getCache("raspTest").get("run_"+motorNum).getObjectValue().toString())){
////			Element element = new Element("run_"+motorNum, true);  
////			cacheManager.getCache("raspTest").put(element);
//			for(DroneDriver md : CommonCore.motorDrivers){
//				if(md.getMotorNum()==motorNum){
//					md.setMotorRunningDirection(direction);
//					if(!md.getMotorRunning()){//说明在转动中
////						md.setCacheManager(cacheManager);
////						md.setRunning(true);
//						md.moveCycle(fen);
//					}
//				}
//			}
//	}
//	
//	@RequestMapping(value = "/setInterval")
//	@ResponseBody
//	public Integer setInterval(@RequestParam("motorNum") Integer motorNum,@RequestParam("intervalMth") String intervalMth){
//		Integer interval=5;
//		for(DroneDriver md : CommonCore.motorDrivers){
//			if(md.getMotorNum()==motorNum){
//				interval = md.getInterval();
//				if("UP".equals(intervalMth)){
//					interval = interval-1;
//				}else{
//					interval = interval+1;
//				}
//				md.setInterval(interval);
//				interval = md.getInterval();
////				md.setRunning(false);
//			}
//		}
//		return 10-interval;
//	}
//	
//	@RequestMapping(value = "/motorPuase")
//	@ResponseBody
//	public void motorPuase(@RequestParam("motorNum") Integer motorNum){
//		for(DroneDriver md : CommonCore.motorDrivers){
//			if(md.getMotorNum()==motorNum){
//				md.setMotorRunning(false);
////				Element element = new Element("run_"+motorNum, true);  
////				cacheManager.getCache("raspTest").put(element);
////				md.stop();
//			}
//		}
//	}
}
